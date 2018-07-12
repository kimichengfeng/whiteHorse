package com.wecash.MantThread.lockTest;

/**
 * Created by chengtong on 2017/10/26.
 * 对于线程安全的lock队列,与线程安全的synchronized stack代码
 * 对于 synchronized 来说。他只有一个条件队列的，里面放着对应于不同类型的（也可以说是处理不同业务类型的）线程，那这时，你只能notifyall
 ，为了保证程序的正确，把所有的线程都叫起来，不管是不是你想要的业务类型的线程。这种对于性能影响是非常大的。比如10个线程在一个条件队列上等待，那么调用notifyAll 将唤醒所有的线程
 这个时候线程产生如下：
 a 它们会在锁上面产生竞争。
 b 它们竞争完了之后大部分又大部分wait了
 这两步，会导致了大量的线程上下文切换。以及大量锁的竞争。

 但这个lock是没问题的。他可以对于 不同的条件创建wait-set ，比如生产者消费者模式，生产者生产一个对象，这时想唤醒消费者，只需要在相应的条件上面的wait set进行single.

 */
public class ProductStack {
    private Product[] products=new Product[10];
    private int index;


    public synchronized void addProduct(Product product){
        try {
            while(index>=(products.length-1)){//需要重新检查一下，条件判断s
                System.out.println(" the product array is full ; "+Thread.currentThread().getName()+" is waiting");
                wait();
            }

            products[index]=product;
            index++;
            notifyAll();//为了能启动消费线程 当然也唤醒了生产线程。
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public synchronized Product pop(){
        Product product=null;
        try {

            while(index<=0){ //需要重新检查一下，条件判断
                System.out.println("the product array is empty ;"+Thread.currentThread().getName() +"is waiting");
                wait();
            }
            index--;
            product=products[index];
            notifyAll();   //为了能启动 添加线程。 当然也唤醒了消费线程。
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }
}
