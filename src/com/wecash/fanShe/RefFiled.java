package com.wecash.fanShe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by chengtong on 2018/1/11.
 */
public class RefFiled {
    public double x;
    public Double y;

    public RefFiled(double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String args[]) throws NoSuchFieldException, IllegalAccessException {
        Class c = RefFiled.class;
        Modifier.isPublic(c.getModifiers());
        System.out.println(c.getModifiers());


        Field xf = c.getField("x");
        Field yf = c.getField("y");

        RefFiled obj = new RefFiled(10L, (double) 20L);

        System.out.println("变更前x=" + xf.get(obj));
        //变更成员x值
        xf.set(obj, 1.1);
        System.out.println("变更后x=" + xf.get(obj));

        System.out.println("变更前y=" + yf.get(obj));
        //变更成员y值
        yf.set(obj, 2.1);
        System.out.println("变更后y=" + yf.get(obj));
    }
}
