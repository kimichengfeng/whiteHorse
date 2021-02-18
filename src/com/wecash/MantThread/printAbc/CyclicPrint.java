package com.wecash.MantThread.printAbc;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: tong.cheng
 * Date: 2020-08-07
 * Time: 15:24
 */
public class CyclicPrint {
    public static void main(String[] args) {

//        CyclicABC cyclicABC = new CyclicABC(6);
//        new Thread(()->cyclicABC.print(()-> System.out.println("c"),'c')).start();
//        new Thread(()->cyclicABC.print(()-> System.out.print("a"),'a')).start();
//        new Thread(()->cyclicABC.print(()-> System.out.print("b"),'b')).start();
        printAlphabet();


    }

    /**
     * EXTRA
     * 循环打印字母表
     */
    public static void printAlphabet() {
        CyclicAlphabet cyclicAlphabet = new CyclicAlphabet(6);
        for (int i = 0; i < 3; i++) {
            int mask = i;
            char c = (char) ('a' + i);
            new Thread(()-> cyclicAlphabet.print(()-> System.out.print(c),mask)).start();
        }
    }
}


/**
 * 循环打印 abc。无锁
 */
class CyclicABC{
    private final int n;

    public CyclicABC(int n) {
        this.n = n;
    }

    volatile char flag = 'a';

    public void print(Runnable print,char id){
        for (int i = 0; i < n; ) {
            if(flag == id){
                print.run();
                flag = flag == 'c' ? 'a' : (char) (flag + 1);
                i++;
            }
        }
    }

}


/**
 *  EXTRA
 *  循环打印字母表 lock.condition
 */
class CyclicAlphabet{
    private final int n;
    Lock lock;
    List<Condition> conditions ;
    public CyclicAlphabet(int n) {
        this.n = n;
        lock = new ReentrantLock();
        List<Condition> list = new ArrayList<>();
        Lock lock1 = lock;
        for (long count = 26; count > 0; count--) {
            Condition condition = lock1.newCondition();
            list.add(condition);
        }
        conditions = list;
    }

    int index = 0;

    public void print(Runnable runnable,int mask) {
        for (int i = 0; i < n; i++) {
            try{
                lock.lock();
                if(index != mask)
                    conditions.get(mask).await();
                runnable.run();
                if(++index == 3) {
                    System.out.println();
                    index = 0;
                }
                conditions.get(index).signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
