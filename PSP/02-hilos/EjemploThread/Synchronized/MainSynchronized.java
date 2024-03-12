package Synchronized;

public class MainSynchronized {
    public static void main(String[] args) throws Exception {
        var add = new AddThread();
        var dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter.count);
    }
}

class Counter {
    public static final Object lock = new Object();
    public static int count = 0;
}

class AddThread extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.lock) {
                Counter.count += 1;
                // System.out.println("Soy" + this.getName() + ": " + Counter.count);
            }
        }
    }
}

class DecThread extends Thread {
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.lock) {
                Counter.count -= 1;
                // System.out.println("Soy" + this.getName() + ": " + Counter.count);
            }
        }
    }
}

/*
 * 对于static方法，是没有this实例的，因为static方法是针对类而不是实例。但是我们注意到任何一个类都有一个由JVM自动创建的Class实例，因此
 * ，对static方法添加synchronized，锁住的是该类的Class实例。上述synchronized static方法实际上相当于：
 * public static void test(int n) {
        synchronized(Counter.class) {
            ...
        }
    }
 */