package com.wecash.sheJiMoShi.factory.method;

/**
 * 工厂方法模式中我们把生成产品类的时间延迟，就是通过对应的工厂类来生成对应的产品类，在这里我们就可以实现“开发-封闭”原则，无论加多少产品类，我们都不用修改原来类中的代码，而是通过增加工厂类来实现。但是这还是有缺点的，如果产品类过多，我们就要生成很多的工厂类。假如我们要实现的产品接口不止一个，也就是有多个产品接口，不同产品接口有对应的产品族。什么是产品族呢？简单的理解就是，不同牌子产的车里面会有跑车类型，家庭类型，商用类型等的车，不同牌子的车的跑车类型的车可以组成一个产品族。对于这种情况我们可以采用抽象工厂模式。
 */
public class Client {
    public static void main(String[] args) {
        Factory factory;
        factory = new FactoryA();
        factory.createProduct();
        factory = new FactoryB();
        factory.createProduct();
    }
}
