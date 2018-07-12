package com.wecash.baseTest;

/**
 * Created by chengtong on 2018/4/12.
 */
public class PartVariable {
    private int x; // instance变量

    private static int staticX; //static 变量

    public void stackAccess(int val) { //访问和操作stack变量j
        final long deadline = System.nanoTime();
        int j= 0;
        for(int i=0;i<val;i++){
            j+=i;
        }
        final long nanoTime = System.nanoTime() - deadline;
        System.out.println("j="+j+";耗时："+nanoTime);
    }


    public void instanceAccess(int val) {//访问和操作instance变量x
        final long deadline = System.nanoTime();
        for(int i=0;i<val;i++){
            x+=i;
        }
        final long nanoTime = System.nanoTime() - deadline;
        System.out.println("x="+x+";耗时："+nanoTime);
    }
    public void instanceAccessOpti(int val) {//访问和操作instance变量x(优化)
        final long deadline = System.nanoTime();
        int xx = x;
        for(int i=0;i<val;i++){
            xx+=i;
        }
        final long nanoTime = System.nanoTime() - deadline;
        System.out.println("优化后x="+xx+";耗时："+nanoTime);
    }

    public void staticAccess(int val) {//访问和操作static变量staticX

        final long deadline = System.nanoTime();
        for(int i=0;i<val;i++){
            staticX+=i;
        }
        final long nanoTime = System.nanoTime() - deadline;
        System.out.println("staticX="+staticX+";耗时："+nanoTime);
    }
    public void staticAccessOpti(int val) {//访问和操作static变量staticX(优化)

        final long deadline = System.nanoTime();
        int staticXX = staticX;
        for(int i=0;i<val;i++){
            staticXX+=i;
        }
        final long nanoTime = System.nanoTime() - deadline;
        System.out.println("优化后staticX="+staticXX+";耗时："+nanoTime);
    }
    public static void main(String[] args) {
        int val = 10;
        PartVariable variable = new PartVariable();
        variable.stackAccess(val);
        variable.instanceAccess(val);
        variable.staticAccess(val);
        variable.instanceAccessOpti(val);
        variable.staticAccessOpti(val);
    }
}
