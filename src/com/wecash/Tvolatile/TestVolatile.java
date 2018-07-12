package com.wecash.Tvolatile;

import java.util.concurrent.atomic.AtomicBoolean;

public class TestVolatile {

    public static void main(String[] args) {
        CMap map = new CMap();
        new MThread1(map).start();
        new MThread2(map).start();
    }

    static class MThread1 extends Thread {
        private CMap map = null;

        MThread1(CMap cmap) {
            this.map = cmap;
        }

        @Override
        public void run() {
            System.out.println("spin start");
            map.spin();
            System.out.println("spin end");
        }

    }

    static class MThread2 extends Thread {
        private CMap map = null;

        MThread2(CMap cmap) {
            this.map = cmap;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("put start");
            map.put();
            System.out.println("put end");
        }

    }

    static class CMap {
        volatile Object[] table = null;
        AtomicBoolean ctl = new AtomicBoolean(false);

        public CMap() {
            System.out.println("init CMap");
            table = new Object[16];
        }

        public void spin() {
            // 把这里的t = table 去掉，则无法跳出循环
//            for (Object[] t = table ; ;t = table) {
//                if (t[0] == null) {
//                } else {
//                    break;
//                }
//            }

            for ( ; ;) {
                if (table == null) {
                } else {
                    break;
                }
            }

//            for (Object[] t = table ; ;) {
//                if (t[0] == null) {
//                } else {
//                    break;
//                }
//            }
        }

        public void put() {
            Object[] t = table;
            t[0] = new Object();
        }
    }


}
