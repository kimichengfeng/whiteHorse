//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wecash.nevermore.http;

import com.alibaba.fastjson.JSONObject;
import com.wecash.nevermore.concurrent.ManagedThreadPool;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpUtil {
    private static final String CONTENT_CHARSET = "UTF-8";
    private static Logger log= LoggerFactory.getLogger(ManagedThreadPool.class);

    public HttpUtil() {
    }

    public static String postResponseBody(String url, NameValuePair[] data) {
        String responseBody = null;
        PostMethod postMethod = null;

        try {
            HttpClient var11 = new HttpClient();
            postMethod = new PostMethod(url);
            postMethod.setRequestBody(data);
            postMethod.getParams().setParameter("http.protocol.content-charset", "UTF-8");
            int statusCode = var11.executeMethod(postMethod);
            if(statusCode == 200) {
                responseBody = postMethod.getResponseBodyAsString();
            } else {
                responseBody = statusCode + ";" + postMethod.getStatusText() + ";" + postMethod.getResponseBodyAsString();
            }
        } catch (HttpException var10) {
            var10.printStackTrace();
        } catch (IOException var111) {
            System.out.println("请求失败");
            var111.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }

        return responseBody;
    }

    public static String postResponseBodyMap(String url, Map<String, Object> map) {
        String responseBody = null;
        PostMethod postMethod = null;

        try {
            HttpClient var17 = new HttpClient();
            postMethod = new PostMethod(url);
            ArrayList lists = new ArrayList();
            Iterator ds = map.entrySet().iterator();

            while(ds.hasNext()) {
                Entry ds1 = (Entry)ds.next();
                lists.add(new NameValuePair((String)ds1.getKey(), (String)ds1.getValue()));
            }

            NameValuePair[] ds11 = new NameValuePair[lists.size()];
            lists.toArray(ds11);
            postMethod.setRequestBody(ds11);
            postMethod.getParams().setParameter("http.protocol.content-charset", "UTF-8");
            int statusCode1 = var17.executeMethod(postMethod);
            if(statusCode1 == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream()));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";

                while((str = reader.readLine()) != null) {
                    stringBuffer.append(str);
                }

                responseBody = stringBuffer.toString();
            } else {
                responseBody = statusCode1 + ";" + postMethod.getStatusText() + ";" + postMethod.getResponseBodyAsString();
            }
        } catch (HttpException var16) {
            var16.printStackTrace();
        } catch (IOException var171) {
            System.out.println("请求失败");
            var171.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }

        return responseBody;
    }

    public static String postResponseBody(String url, Map<String, String> headerMap, NameValuePair[] data) {
        String responseBody = null;
        PostMethod postMethod = null;

        try {
            HttpClient var16 = new HttpClient();
            postMethod = new PostMethod(url);
            Iterator statusCode = headerMap.entrySet().iterator();

            while(statusCode.hasNext()) {
                Entry statusCode1 = (Entry)statusCode.next();
                postMethod.setRequestHeader(statusCode1.getKey() + "", statusCode1.getValue() + "");
            }

            postMethod.setRequestBody(data);
            postMethod.getParams().setParameter("http.protocol.content-charset", "gb2312");
            int statusCode11 = var16.executeMethod(postMethod);
            if(statusCode11 == 200) {
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream()));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";

                while((str = reader1.readLine()) != null) {
                    stringBuffer.append(str);
                }

                responseBody = stringBuffer.toString();
            } else {
                responseBody = statusCode11 + ";" + postMethod.getStatusText() + ";" + postMethod.getResponseBodyAsString();
            }
        } catch (HttpException var15) {
            var15.printStackTrace();
        } catch (IOException var161) {
            System.out.println("请求失败");
            var161.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }

        return responseBody;
    }

    public static String postResponseBody(String url, String data) {
        String responseBody = null;
        PostMethod postMethod = null;

        try {
            HttpClient var14 = new HttpClient();
            postMethod = new PostMethod(url);
            postMethod.setRequestBody(data);
            postMethod.getParams().setParameter("http.protocol.content-charset", "UTF-8");
            int statusCode = var14.executeMethod(postMethod);
            if(statusCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream()));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";

                while((str = reader.readLine()) != null) {
                    stringBuffer.append(str);
                }

                responseBody = stringBuffer.toString();
            } else {
                responseBody = statusCode + ";" + postMethod.getStatusText();
            }
        } catch (HttpException var13) {
            var13.printStackTrace();
        } catch (IOException var141) {
            System.out.println("请求失败");
            var141.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }

        return responseBody;
    }

    public static String postResponseBody(String url, Map<String, String> headerMap, String data) {
        String responseBody = null;
        PostMethod postMethod = null;

        try {
            HttpClient var16 = new HttpClient();
            postMethod = new PostMethod(url);
            Iterator statusCode = headerMap.entrySet().iterator();

            while(statusCode.hasNext()) {
                Entry statusCode1 = (Entry)statusCode.next();
                postMethod.setRequestHeader(statusCode1.getKey() + "", statusCode1.getValue() + "");
            }

            postMethod.setRequestBody(data);
            postMethod.getParams().setParameter("http.protocol.content-charset", "UTF-8");
            int statusCode11 = var16.executeMethod(postMethod);
            if(statusCode11 == 200) {
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream()));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";

                while((str = reader1.readLine()) != null) {
                    stringBuffer.append(str);
                }

                responseBody = stringBuffer.toString();
            } else {
                responseBody = statusCode11 + ";" + postMethod.getStatusText();
            }
        } catch (HttpException var15) {
            var15.printStackTrace();
        } catch (IOException var161) {
            System.out.println("请求失败");
            var161.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }

        return responseBody;
    }

    public static String getResponseBody(String url) {
        HttpClient httpClient = new HttpClient();
        String responseBody = null;
        GetMethod getMethod = null;
        getMethod = new GetMethod(url);

        try {
            getMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
            getMethod.getParams().setParameter("http.protocol.content-charset", "UTF-8");
            String var11 = getMethod.getResponseCharSet();
            System.out.println("编码为:" + var11);
            int statusCode = httpClient.executeMethod(getMethod);
            if(statusCode != 200) {
                System.out.println("请求失败");
            }

            responseBody = getMethod.getResponseBodyAsString();
        } catch (HttpException var10) {
            var10.printStackTrace();
        } catch (IOException var111) {
            var111.printStackTrace();
        } finally {
            if(getMethod != null) {
                getMethod.releaseConnection();
            }

        }

        return responseBody;
    }

    public static void main(String[] args) {
        String jsonData = "{\"name\":\"你好\"}";
        String url = "http://localhost:8080/paymentKuaijie/payment/quick/hello";
        String response = postJsonBody(url, jsonData);
        System.out.println(response);
    }

    public static String doGetAsJson(String url, NameValuePair[] data) {
        JSONObject result = new JSONObject();
        HttpClient httpClient = new HttpClient();
        String responseBody = null;
        GetMethod getMethod = null;

        try {
            String var13 = buildHttpGetUrl(url, data);
            System.out.println("http get : " + var13);
            getMethod = new GetMethod(var13);
            getMethod.getParams().setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
            getMethod.getParams().setParameter("http.protocol.content-charset", "UTF-8");
            int statusCode = httpClient.executeMethod(getMethod);
            responseBody = getMethod.getResponseBodyAsString();
            result.put("success", Boolean.valueOf(statusCode == 200));
            result.put("statusCode", Integer.valueOf(statusCode));
            result.put("content", responseBody);
        } catch (HttpException var12) {
            var12.printStackTrace();
        } catch (IOException var131) {
            var131.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }

        return result.toString();
    }

    private static String buildHttpGetUrl(String url, NameValuePair[] data) {
        StringBuilder sb = new StringBuilder(url);
        if(data != null) {
            NameValuePair[] var3 = data;
            int var4 = data.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                NameValuePair p = var3[var5];
                if(sb.indexOf("?") <= 0 && url.indexOf("?") <= 0) {
                    sb.append("?").append(p.getName()).append("=").append(p.getValue());
                } else {
                    sb.append("&").append(p.getName()).append("=").append(p.getValue());
                }
            }
        }

        return sb.toString();
    }

    public static String postJsonBody(String url, String json) {
        String response = null;

        try {
            HttpClient var8 = new HttpClient();
            PostMethod postMethod = new PostMethod(url);
            if(null != json && !"".equals(json)) {
                StringRequestEntity entity = new StringRequestEntity(json, "application/json", "UTF-8");
                postMethod.setRequestEntity(entity);
                postMethod.releaseConnection();
                int status = var8.executeMethod(postMethod);
                if(status == 200) {
                    response = postMethod.getResponseBodyAsString();
                } else {
                    response = null;
                }

                return response;
            }
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
        } catch (Exception var81) {
            var81.printStackTrace();
        }

        return null;
    }

    public byte[] post(String url, byte[] postContent, String contentType) {
        String result = "";
        InputStream in = null;
        HttpURLConnection connection = null;
        OutputStream out = null;

        try {
            URL var19 = new URL(url);
            connection = (HttpURLConnection)var19.openConnection();
            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setConnectTimeout(30000);
            connection.setReadTimeout('꿈');
            out = connection.getOutputStream();
            out.write(postContent);
            in = connection.getInputStream();
            byte[] var9 = this.inputStreamToByte(in);
            byte[] var11 = var9;
            return var11;
        } catch (Exception var21) {
            this.log.error("post请求发生异常,url=" + url, var21);
        } finally {
            try {
                if(out != null) {
                    out.close();
                }

                if(in != null) {
                    in.close();
                }

                if(connection != null) {
                    connection.disconnect();
                }
            } catch (IOException var20) {
                this.log.error("error", var20);
            }

        }

        return new byte[0];
    }

    public byte[] inputStreamToByte(InputStream in) {
        ByteArrayOutputStream out = null;

        try {
            short var18 = 4096;
            out = new ByteArrayOutputStream();
            byte[] data = new byte[var18];
            boolean var5 = true;

            int count1;
            while((count1 = in.read(data, 0, var18)) != -1) {
                out.write(data, 0, count1);
            }

            byte[] var6 = out.toByteArray();
            byte[] var9 = var6;
            return var9;
        } catch (Exception var19) {
            this.log.error("转换字节数据出错了", var19);
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException var181) {
                ;
            }

        }

        return new byte[0];
    }
}
