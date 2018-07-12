package com.wecash.baseTest.doubleDispatch;

/**
 * Caller类中存在call方法的两种重载，更复杂的是SubCaller集成Caller并且重写了这两个方法。其实这种情况是上面两种情况的复合情况。
 下面的代码首先会发生静态绑定，确定调用参数为String对象的call方法，然后在运行时进行动态绑定确定执行子类还是父类的call实现。
 */
public class TestMain {
    public static void main(String[] args) {
        String str = new String();
        Caller callerSub = new SubCaller();
        callerSub.call(str);
    }

    static class Caller {
        public void call(Object obj) {
            System.out.println("an Object instance in Caller");
        }

        public void call(String str) {
            System.out.println("a String instance in in Caller");
        }
    }

    static class SubCaller extends Caller {
        @Override
        public void call(Object obj) {
            System.out.println("an Object instance in SubCaller");
        }

        @Override
        public void call(String str) {
            System.out.println("a String instance in in SubCaller");
        }
    }
}
