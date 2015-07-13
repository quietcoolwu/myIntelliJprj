package com.imooc.concurrent.base;

/**
 * Created by William on 2015/6/27.
 */
public class WrongWayStopThread extends Thread {

    public static void main(String[] args) {
        WrongWayStopThread thread = new WrongWayStopThread();
        System.out.println("Starting thread...");
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Interrupting thread...");
        //中断线程
        thread.interrupt();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping application...");

    }

    public void run() {
        while (!this.isInterrupted()) {
            //特殊的退出旗标
            System.out.println("Thread is running...");
//            long time = System.currentTimeMillis();
//            while(System.currentTimeMillis()-time < 1000){
//
//            }
            //为什么用如下方法线程会抛出异常但仍不停止?
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
