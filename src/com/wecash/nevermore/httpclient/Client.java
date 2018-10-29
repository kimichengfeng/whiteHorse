package com.wecash.nevermore.httpclient;

import com.google.common.base.Charsets;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Client {
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private static final int SOCKETTIMEOUT = 10000;
    private static final int CONNECTIONTIMEOUT = 10000;
    private static final int CONNREQUESTTIMEOUT = 10000;
    //private static final int SOCKETSOTIMEOUT = 20000;
    private CloseableHttpClient browserHttpClient = null;
    private RequestConfig requestConfig = null;
    private BasicCookieStore cookieStore = null;
    public static List<Header> baseHeader = ((Headers) () -> {
        List<Header> basicHeaders = Lists.newArrayList();
        basicHeaders.add(new BasicHeader("Connection", "keep-alive"));
        basicHeaders.add(new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"));
        basicHeaders.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36"));
        basicHeaders.add(new BasicHeader("Accept-Encoding", "gzip, deflate, sdch"));
        basicHeaders.add(new BasicHeader("Accept-Language", "en-GB,en;q=0.8,en-US;q=0.6,zh-TW;q=0.4,zh;q=0.2"));
//        basicHeaders.add(new BasicHeader("Content-Type", "application/json;charset=UTF-8"));
        basicHeaders.add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"));
        return basicHeaders;
    }).getHeader();


    public static Client localClient() {
        logger.info("获取一个本地client");
        return new Client("", 80, null);
    }

    public static Client clientWithTimeOut(int timeOut) {
        logger.info("获取一个本地带超时时间的client，超时时间：{}", timeOut);
        return new Client(timeOut);
    }

    public static Client localClientWithBasicAuth(String username, String password) {
        logger.info("获取需要basicAuth认证的本地client");
        return new Client("", 80, null, username, password);
    }

    public Client(int timeOut) {
        this.cookieStore = new BasicCookieStore();
        RequestConfig.Builder builder = RequestConfig.custom().setSocketTimeout(timeOut).setConnectionRequestTimeout(timeOut).setConnectTimeout(timeOut).setCookieSpec("compatibility");
        this.requestConfig = builder.build();
        this.browserHttpClient = this.createHttpsClient(null, null);
    }

    //如果获取不到proxyIP，请参考下，可能是白名单之类的问题 https://git.wecash.net/league/proxy-pool
    public static Client proxy() {
        logger.info("获取一个代理IP：client");
        return proxyClient(ProxyUtil.getProxy());
    }

    public static Client proxyClient(ProxyIp proxyIp) {
        logger.info("获取一个代理client，代理ip为:{}", proxyIp);
        return new Client(proxyIp.getHost(), proxyIp.getPort(), null);
    }

    public Client(String ip, int port, List<Cookie> cookies) {
        this.cookieStore = new BasicCookieStore();
        RequestConfig.Builder builder = RequestConfig.custom().setSocketTimeout(SOCKETTIMEOUT).setConnectionRequestTimeout(CONNREQUESTTIMEOUT).setConnectTimeout(CONNECTIONTIMEOUT).setCookieSpec("compatibility");
        if (cookies != null && cookies.size() > 0) {
            this.cookieStore.addCookies(Iterables.toArray(cookies, Cookie.class));
        }
        if (StringUtils.isNotBlank(ip)) {
            HttpHost proxy = new HttpHost(ip, port);
            this.requestConfig = builder.setProxy(proxy).build();
        } else {
            this.requestConfig = builder.build();
        }
        this.browserHttpClient = this.createHttpsClient(null, null);
    }

    public Client(String ip, int port, List<Cookie> cookies, String basicAuthUserName, String basicAuthPassword) {
        this.cookieStore = new BasicCookieStore();
        RequestConfig.Builder builder = RequestConfig.custom().setSocketTimeout(SOCKETTIMEOUT)
                .setConnectionRequestTimeout(CONNREQUESTTIMEOUT).setConnectTimeout(CONNECTIONTIMEOUT)
                .setCookieSpec("compatibility");
        if (StringUtils.isNotEmpty(basicAuthUserName) && StringUtils.isNotEmpty(basicAuthPassword)) {
            builder = builder.setAuthenticationEnabled(true);
        }
        if (cookies != null && cookies.size() > 0) {
            this.cookieStore.addCookies(Iterables.toArray(cookies, Cookie.class));
        }
        if (StringUtils.isNotBlank(ip)) {
            HttpHost proxy = new HttpHost(ip, port);
            this.requestConfig = builder.setProxy(proxy).build();
        } else {
            this.requestConfig = builder.build();
        }
        this.browserHttpClient = this.createHttpsClient(basicAuthUserName, basicAuthPassword);
    }

    public BasicCookieStore getCookieStore() {
        return this.cookieStore;
    }

    public void setCookieStore(BasicCookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }

    public String get(String url, List<Header> headers) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.requestConfig);
        if (headers != null && headers.size() > 0) {
            httpGet.setHeaders(Iterables.toArray(headers, Header.class));
        }
        try {
            //解决cookie问题
            CookieSpecFactory csf = params -> new BrowserCompatSpec() {
                public void validate(Cookie cookie, CookieOrigin origin)
                        throws MalformedCookieException {
                }
            };

            CloseableHttpResponse e = this.browserHttpClient.execute(httpGet);
            logger.info("httpclient请求返回码:{}", e.getStatusLine().getStatusCode());
            return EntityUtils.toString(e.getEntity(), Charsets.UTF_8.toString());
        } catch (Exception var16) {
            logger.error("请求发生异常", var16);
            return "HttpClient请求发生异常";
        } finally {
            httpGet.releaseConnection();
        }
    }

    public String get(String url) throws IOException {
        return this.get(url, baseHeader);
    }

    public String get(String url, IParam param, List<Header> headers) throws IOException {
        return get(url + param.toGet(), headers);
    }

    public String post(String url, Map<String, String> parameter) throws IOException {
        return post(url, parameter, baseHeader);
    }

    public String post(String url, Map<String, String> parameter, List<Header> headers) throws IOException {
        HttpPost httpPost = null;
        String var5;
        try {

            //解决cookie问题
            CookieSpecFactory csf = params -> new BrowserCompatSpec() {
                public void validate(Cookie cookie, CookieOrigin origin)
                        throws MalformedCookieException {
                }
            };


            httpPost = new HttpPost(url);
            httpPost.setConfig(this.requestConfig);
            httpPost.setEntity(this.mapToHttpEntity(parameter));
            httpPost.setHeaders(Iterables.toArray(headers, Header.class));
            CloseableHttpResponse httpResp = this.browserHttpClient.execute(httpPost);
            logger.info("statusCode:{}", httpResp.getStatusLine().getStatusCode());
            var5 = EntityUtils.toString(httpResp.getEntity(), Charsets.UTF_8.toString());
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return var5;
    }

    /**
     * 请求参数包含文件
     */
    public String post(String url, Map<String, String> parameter, byte[] bytes, List<Header> headers) throws IOException {
        HttpPost httpPost = null;
        String var5;
        try {
            httpPost = new HttpPost(url);
            httpPost.setConfig(this.requestConfig);
            httpPost.setEntity(this.paramsToHttpEntity(parameter, bytes));
            httpPost.setHeaders(Iterables.toArray(headers, Header.class));
            CloseableHttpResponse httpResp = this.browserHttpClient.execute(httpPost);
            logger.info("statusCode:{}", httpResp.getStatusLine().getStatusCode());
            var5 = EntityUtils.toString(httpResp.getEntity(), Charsets.UTF_8.toString());
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return var5;
    }

    public String post(String url, IParam param, List<Header> headers) throws IOException {
        return post(url, param.toPost(), headers);
    }

    public String post(String url, IParam param) throws IOException {
        return post(url, param.toPost(), baseHeader);
    }

    public String postContent(String url, String content, List<Header> headers) throws IOException {
        HttpPost httpPost = null;
        String var7;
        try {
            httpPost = new HttpPost(url);
            httpPost.setConfig(this.requestConfig);
            httpPost.setEntity(new StringEntity(content, "UTF-8"));
            httpPost.setHeaders(Iterables.toArray(headers, Header.class));
            CloseableHttpResponse httpResp = this.browserHttpClient.execute(httpPost);
            logger.info("statusCode:{}", httpResp.getStatusLine().getStatusCode());
            var7 = EntityUtils.toString(httpResp.getEntity(), Charsets.UTF_8.toString());
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
        return var7;
    }

    public String postContent(String url, String content) throws IOException {
        return postContent(url, content, baseHeader);
    }

    public String postContent(String url, IParam param) throws IOException {
        return postContent(url, param.toContent(), baseHeader);
    }

    public String postContent(String url, IParam param, List<Header> headers) throws IOException {
        return postContent(url, param.toContent(), headers);
    }

    public String delete(String url) throws IOException {
        HttpDelete httpDelete = new HttpDelete(url);
        String var5;
        try {
            httpDelete.setConfig(this.requestConfig);
            httpDelete.setHeaders(Iterables.toArray(baseHeader, Header.class));
            CloseableHttpResponse httpResp = this.browserHttpClient.execute(httpDelete);
            logger.info("statusCode:{}", httpResp.getStatusLine().getStatusCode());
            var5 = EntityUtils.toString(httpResp.getEntity(), Charsets.UTF_8.toString());
        } finally {
            httpDelete.releaseConnection();
        }
        return var5;
    }

    /**
     * 这个方法用来直接接受成字节流，处理图片之类的
     */
    public byte[] getRespEntity(String url, List<Header> headers) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(this.requestConfig);
        if (headers != null && headers.size() > 0) {
            httpGet.setHeaders(Iterables.toArray(headers, Header.class));
        }

        byte[] var5;
        try {
            CloseableHttpResponse httpResponse = this.browserHttpClient.execute(httpGet);
            var5 = EntityUtils.toByteArray(httpResponse.getEntity());
        } finally {
            httpGet.releaseConnection();
        }

        return var5;
    }

    private HttpEntity mapToHttpEntity(Map<String, String> params) throws UnsupportedEncodingException {
        if (params != null && !params.isEmpty()) {
            ArrayList<BasicNameValuePair> nvPairs = Lists.newArrayList();
            nvPairs.addAll(params.keySet().stream().map(key -> new BasicNameValuePair(StringUtils.trimToEmpty(key), StringUtils.trimToEmpty(params.get(key)))).collect(Collectors.toList()));
            return new UrlEncodedFormEntity(nvPairs, Charsets.UTF_8.toString());
        } else {
            return null;
        }
    }

    private HttpEntity paramsToHttpEntity(Map<String, String> params, byte[] bytes) throws UnsupportedEncodingException {
        if (params != null && !params.isEmpty()) {
            //创建要发送的实体，就是key-value的这种结构，借助于这个类，可以实现文件和参数同时上传。
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
            Iterator iterator = params.keySet().iterator();
            // 发送的数据
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                multipartEntityBuilder.addTextBody(StringUtils.trimToEmpty(key), StringUtils.trimToEmpty((String) params.get(key)), ContentType.create("text/plain", Charset.forName("UTF-8")));
            }
            // 发送的文件流
            if (bytes.length != 0) {
                multipartEntityBuilder.addBinaryBody("image", bytes, ContentType.DEFAULT_BINARY, "anyStr");
            }
            return multipartEntityBuilder.build();
        } else {
            return null;
        }
    }

    private CloseableHttpClient createHttpsClient(String username, String password) {
        X509TrustManager x509mgr = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] xcs, String string) {
            }

            public void checkServerTrusted(X509Certificate[] xcs, String string) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        SSLContext sslContext = null;

        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init((KeyManager[]) null, new TrustManager[]{x509mgr}, (SecureRandom) null);
        } catch (NoSuchAlgorithmException var5) {
            logger.error("https createHttpsClient NoSuchAlgorithmException:{}", var5);
        } catch (KeyManagementException var6) {
            logger.error("https createHttpsClient KeyManagementException:{}", var6);
        }

        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        SocketConfig socketConfig = SocketConfig.custom().setSoKeepAlive(true).setSoLinger(-1).setSoReuseAddress(false).setSoTimeout('\uea60').setTcpNoDelay(true).build();
        CredentialsProvider credsProvider = null;
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        }
        return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).setDefaultCookieStore(this.cookieStore)
                .setMaxConnTotal(400).setMaxConnPerRoute(50).setDefaultSocketConfig(socketConfig)
                .setRedirectStrategy(new LaxRedirectStrategy()).setDefaultCredentialsProvider(credsProvider).build();
    }

    public static List<BasicHeader> getJsonBasicHeaders() {
        return Lists.newArrayList(
                new BasicHeader("Connection", "keep-alive"),
                new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"),
                new BasicHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36"),
                new BasicHeader("Accept-Encoding", "gzip, deflate, sdch"),
                new BasicHeader("Accept-Language", "en-GB,en;q=0.8,en-US;q=0.6,zh-TW;q=0.4,zh;q=0.2"),
                new BasicHeader("Content-Type", "application/json;charset=UTF-8")
        );
    }

    public static List<Header> getJsonHeaders() {
        return Lists.newArrayList(
                new BasicHeader("Connection", "keep-alive"),
                new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"),
                new BasicHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36"),
                new BasicHeader("Accept-Encoding", "gzip, deflate, sdch"),
                new BasicHeader("Accept-Language", "en-GB,en;q=0.8,en-US;q=0.6,zh-TW;q=0.4,zh;q=0.2"),
                new BasicHeader("Content-Type", "application/json;charset=UTF-8")
        );
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Client.localClient().get("https://www.baidu.com"));
    }


}

