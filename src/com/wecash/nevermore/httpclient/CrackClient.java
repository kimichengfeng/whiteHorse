package com.wecash.nevermore.httpclient;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wecash.nevermore.concurrent.ManagedThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * 爬虫封装的的client，附加一些小功能，包括资源的配置和获取、爬虫任务时间线的记录以及
 */

@SuppressWarnings("unchecked")
public class CrackClient<TASK, OUT extends Result>  {
    private static Logger log= LoggerFactory.getLogger(ManagedThreadPool.class);

    private String taskId;
    private Client client = Client.localClient();
    private List<Resource> resources = Lists.newArrayList();
    private List<TimeLineItem> timeLine = Lists.newArrayList();  //扭转状态的时间线，用来复盘这次抓取的过程
    private Map<String, String> map = Maps.newHashMap(); //该map用来存储在各个阶段需要的值，用来解耦代码
    private TASK task;
    private OUT result;
    private Stopwatch watch=Stopwatch.createStarted();


    public static <TASK, OUT extends Result> CrackClient<TASK, OUT> local() {
        return new CrackClient<>(Client.localClient());
    }

    //如果获取不到proxyIP，请参考下，可能是白名单之类的问题 https://git.wecash.net/league/proxy-pool
    public static <TASK, OUT extends Result> CrackClient<TASK, OUT> proxy() {
        return new CrackClient(Client.proxy());
    }

    public Stopwatch getWatch() {
        return watch;
    }

    public void setWatch(Stopwatch watch) {
        this.watch = watch;
    }

    public TASK getTask() {
        return task;
    }

    public CrackClient<TASK, OUT> setTask(TASK task) {
        this.task = task;
        return this;
    }

    public OUT getResult() {
        return result;
    }

    public CrackClient setResult(OUT result) {
        this.result = result;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public CrackClient(Client client) {
        this.client = client;
        this.taskId = generateTaskId("task");
    }

    public CrackClient(Client client, List<Resource> resources, List<TimeLineItem> timeLine, Map<String, String> map) {
        this.client = client;
        this.resources = resources;
        this.timeLine = timeLine;
        this.map = map;
        this.taskId = generateTaskId("task");
    }

    public String getKey(String key) {
        return map.get(key);
    }

    public CrackClient putKV(String key, String value) {
        if (StringUtils.isNotEmpty(map.get(key))) {
            log.warn("你往crackClient中放了一个已经存在的值，key：{}，oldValue：{}，newValue：{}", key, map.get(key), value);
        }
        map.put(key, value);
        return this;
    }

    public Account getAccount() {
        for (Resource resource : resources) {
            if (resource.type() == ResourceType.ACCOUNT) {
                log.info("找到账号了:{}", resource.content());
                return (Account) resource;
            }
        }
        log.info("没有找到账号", taskId);
        throw new IllegalArgumentException("没有账号你获取个毛啊");
    }

    //记录一个状态
    public CrackClient mark(Status status) {
        log.info("扭转状态:{}", status);
        this.timeLine.add(new TimeLineItem(status, new DateTime()));
        return this;
    }

    public String get(String url, List<Header> headers) throws IOException {
        return this.client.get(url, headers);
    }

    public String get(String url) throws IOException {
        return this.client.get(url);
    }

    public String get(String url, IParam param, List<Header> headers) throws IOException {
        return this.client.get(url, param, headers);
    }

    public String post(String url, Map<String, String> map) throws IOException {
        return this.client.post(url, map);
    }

    public String post(String url, Map<String, String> map, List<Header> headers) throws IOException {
        return this.client.post(url, map, headers);
    }

    public String post(String url, IParam param, List<Header> headers) throws IOException {
        return this.client.post(url, param, headers);
    }

    public String post(String url, IParam param) throws IOException {
        return this.client.post(url, param);
    }

    public String postContent(String url, String content, List<Header> headers) throws IOException {
        return this.client.postContent(url, content, headers);
    }

    public String postContent(String url, String content) throws IOException {
        return this.client.postContent(url, content);
    }

    public String postContent(String url, IParam param) throws IOException {
        return this.client.postContent(url, param);
    }

    public String postContent(String url, IParam param, List<Header> headers) throws IOException {
        return this.client.postContent(url, param, headers);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public CrackClient addResource(Resource... resources) {
        this.resources.addAll(Lists.newArrayList(resources));
        return this;
    }

    public List<TimeLineItem> getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(List<TimeLineItem> timeLine) {
        this.timeLine = timeLine;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    private String generateTaskId(String prefix) {
        return prefix + "_" + new DateTime().toString("yyyy-MM-dd HH:mm:ss") + "_" + Thread.currentThread().getName() + "_" + Thread.currentThread().getId();
    }

    public static void main(String[] args) {
        System.out.println(CrackClient.local().getTaskId());
    }

}
