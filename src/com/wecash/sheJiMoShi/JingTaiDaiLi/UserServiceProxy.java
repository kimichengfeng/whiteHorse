package com.wecash.sheJiMoShi.JingTaiDaiLi;

/**
 * Created by chengtong on 2017/7/14.
 * 静态代理只能服务于一种类型的对象,不利于业务的扩展
 */
public class UserServiceProxy implements UserService {
    private UserService userService;

    public UserServiceProxy(UserService userService) {
        super();
        this.userService = userService;
    }

    public void open(){
        System.out.println("1:打开数据库连接");
    }
    public void close(){
        System.out.println("3:关闭数据库连接");
    }
    @Override
    public void saveUser() {
        this.open();
        userService.saveUser();
        this.close();
    }
}
