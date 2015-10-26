package testThread;

/**
 * 1.我们都知道，Java是单继承机制，不允许同时继承多个类。因此，当你继承Thread类(extends Thread)后，你就不能再继承其他类了。而你实现Runnable接口就不一样了，你可以继承其他类了。
 * <p>
 * 2.当你继承Thread类时，你的每一个Thread对象创造不同的对象然后关联它们。
 * <p>
 * 而继承Runnable接口则不一样，多个线程共享一个对象。
 * Created by William on 2015/6/28.
 */
class ImplementsRunnable implements Runnable {


    private int counter = 0;


    public void run() {
        counter++;
        System.out.println("ImplementsRunnable : Counter : " + counter);
    }
}

class ExtendsThread extends Thread {

    private int counter = 0;
    //private static int counter = 0;

    public void run() {
        counter++;
        System.out.println("ExtendsThread : Counter : " + counter);
    }
}

public class ThreadVsRunnable {

    public static void main(String args[]) throws Exception {
        //多线程共享一个对象
        ImplementsRunnable rc = new ImplementsRunnable();
        Thread t1 = new Thread(rc);
        t1.start();
        Thread.sleep(1000); // 在开启下个线程前先等待1秒
        Thread t2 = new Thread(rc);
        t2.start();
        Thread.sleep(1000); // 在开启下个线程前先等待1秒
        Thread t3 = new Thread(rc);
        t3.start();

        //为每一个线程创造新的实例
        ExtendsThread tc1 = new ExtendsThread();
        tc1.start();
        Thread.sleep(1000); // 在开启下个线程前先等待1秒
        ExtendsThread tc2 = new ExtendsThread();
        tc2.start();
        Thread.sleep(1000); // 在开启下个线程前先等待1秒
        ExtendsThread tc3 = new ExtendsThread();
        tc3.start();
    }
}

/**
 * 运行结果:
 * <p>
 * <p>
 * <p>
 * 从运行的结果，我们可以看出。实现Runnable接口，只创建了一个类的实例，而且被多个线程共享了。因此Counter递增。而继承Thread类，你必须为每一个线程创建不同的实例。因此每个类的实例分配了不同的内存空间，每一个有不同的Counter，它们的值相同。这意味着没有增加因为没有一个对象的引用是相同的。
 * <p>
 * 那什么时候用Runnable接口呢？
 * <p>
 * 当你想要在一组线程中访问相同的资源时，使用Runnable接口。在这种情况下要避免使用Thread类，因为多对象的创建会占用更多的内存，会导致大的性能花费。
 * <p>
 * PS:Thread类内部实现了Runnable接口
 * <p>
 * 最后，哪种方式最好用呢？
 * <p>
 * 显而易见，当然是实现Runnable接口更好。
 */