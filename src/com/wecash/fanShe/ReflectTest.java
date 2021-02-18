package com.wecash.fanShe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by chengtong on 2017/7/4.
 */
public class ReflectTest {
    public static Car  initByDefaultConst() throws Throwable
    {
        //①通过类装载器获取Car类对象
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class clazz = loader.loadClass("com.wecash.fanshe.Car");

        //②获取类的默认构造器对象并通过它实例化Car
        Constructor cons = clazz.getDeclaredConstructor((Class[])null);
        Car car = (Car)cons.newInstance();


        //③通过反射方法设置属性
        Method setBrand = clazz.getMethod("setBrand",String.class);
        setBrand.invoke(car,"红旗CA72");
        Method setColor = clazz.getMethod("setColor",String.class);
        setColor.invoke(car,"黑色");
        Method setMaxSpeed = clazz.getMethod("setMaxSpeed",int.class);
        setMaxSpeed.invoke(car,200);
        return car;
    }

    /**
     * class.forName()前者除了将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块。注意这里的静态块指的是在类初始化时的一些数据。但是classloader却没有，想要弄清楚这个原因，还是直接到源码中看看。
     * @throws ClassNotFoundException
     */
    public void classForName() throws ClassNotFoundException {
        Class className = Class.forName("com.wecash.testNull");
        System.out.println("className:"+className);
    }
    public static void main(String[] args) throws Throwable {
        Car car = initByDefaultConst();
        car.introduce();
    }
}
