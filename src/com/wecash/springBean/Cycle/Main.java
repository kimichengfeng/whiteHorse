package com.wecash.springBean.Cycle;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2020-10-13
 * Time: 11:56
 */
public class Main {
    // 预加载
    public static void main(String[] args) {
        Cycle cycle = new Cycle();
        cycle.init();
        UserService userService = (UserService) cycle.getBean("userService");
        OrderService orderService = (OrderService) cycle.getBean("orderService");
        System.out.println(userService.orderService);
        System.out.println(orderService.userService);
    }
    // 懒加载
    public static void main2(String[] args) {
        Cycle cycle = new Cycle();
        UserService userService = (UserService) cycle.getBean("userService");
        OrderService orderService = (OrderService) cycle.getBean("orderService");
        System.out.println(userService.orderService);
        System.out.println(orderService.userService);
    }
}
