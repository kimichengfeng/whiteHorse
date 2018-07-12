package com.wecash.sheJiMoShi.dongTaiDaiLi.cglib;

import com.wecash.sheJiMoShi.dongTaiDaiLi.ForumServiceImpl;

/**
 * Created by chengtong on 2017/7/13.
 */
public class TestForumService {
    public static void main(String[] args) {
        CglibProxy proxy = new CglibProxy();
        ForumServiceImpl forumService = (ForumServiceImpl)proxy.getProxy(ForumServiceImpl.class);
        forumService.removeForum(10);
        forumService.removeTopic(1023);
    }
}
