package com.wecash.sheJiMoShi.impl.factory.easy;

/**
 * 简单工厂模式实现了生成产品类的代码跟客户端代码分离，在工厂类中你可以添加所需的生成产品的逻辑代码，但是问题来了，优秀的java代码是符合“开放-封闭”原则的，也就是说对扩展开发，对修改关闭，如果你要加一个产品类C，你就要修改工厂类里面的生成产品的代码，在这里你就要增加if-else判断。对于这个问题，我们的工厂方法模式就可以解决这个问题。
 */
public class Factory {
    //可以在工厂类中添加任何你所需要的逻辑
    public static Product create(String str)
    {
        //生成ProductA
        if(str.equalsIgnoreCase("ProductA"))
        {
            return new ProductA();
        }
        else
            //生成ProductB
            if(str.equalsIgnoreCase("ProductB"))
            {
                return new ProductB();
            }
        return null;
    }
}
