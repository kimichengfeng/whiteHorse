package com.wecash.sheJiMoShi.impl.factory.abstractF;

/**
 * 抽象工厂模式中我们可以定义实现不止一个接口，一个工厂也可以生成不止一个产品类，抽象工厂模式较好的实现了“开放-封闭”原则，是三个模式中较为抽象，并具一般性的模式。我们在使用中要注意使用抽象工厂模式的条件。
 */
public class Client {
    public static void main(String[] args) {
        Factory factory;
        factory = new FactoryA();
        factory.createProduct();
        factory.createGift();
        factory = new FactoryB();
        factory.createProduct();
        factory.createGift();
    }
}
