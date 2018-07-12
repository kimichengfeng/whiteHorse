package com.wecash.sheJiMoShi.JingTaiDaiLi;

/**
 * Created by chengtong on 2017/7/14.
 */
public class TestProxy {
    public static void main(String[] args) {
        UserService userService =new UserServiceProxy(new UserServiceImpl());
        userService.saveUser();
    }
}
