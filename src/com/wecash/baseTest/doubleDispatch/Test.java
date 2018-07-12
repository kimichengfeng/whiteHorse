package com.wecash.baseTest.doubleDispatch;
/**
* 
* @author chengTong
* @date 2018-07-10 11:30
 * 1.总的来说，Java中访问变量会访问编译类型的变量，调用方法会调用实例的方法。
 * 2.具体到题目中来说，P p = new B(); p的编译类型是P，实例是B的实例，通过自动向上转型成P类型的引用。
 * 3.再详细的说，根据继承对象实例化时会先执行父类构造器的原则，在执行new的时候，先调用父类P的构造器，
 * 而P构造器里的方法display（）会调用当前实例的方法（第1点），也就是B类的display（）方法，此时由于B类还没有执行构造方法对变量初始化，
 * 所以默认值是0，也就是输出第一句In B,Value is 0的原因。P类构造器执行完后，此时P.a值为22，再执行B类构造方法，正常输出In B,Value is 44。
 * 最后通过p访问a的值，访问的是编译类型也就是P类的值，故为22。
**/
public class Test {
    public static void main(String[] args) {
        P b = new B();
        System.out.println(b.a);
    }
    static class P {
        public int a = 11;
        public P() {
            a = 22;
            diplay();
        }
        public void diplay() {
            System.out.println("I am in P, value is " + a);
        }
    }
    static class B extends P {
        int a = 33;
        int b = 100;
        public B() {
            a = 44;
            diplay();
        }
        public void diplay() {
            System.out.println("I am in B, value is " + a);
        }
    }
}
