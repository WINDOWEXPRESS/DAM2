package Synchronized;

public class Main {
    /*
     * Si usas solo add.start(); el resultado es siempre 0 porq sout siempre imprime
     * antes de que termine el hilo y si usas join el sout espera que el hilo
     * termine
     */
    // public static void main(String[] args) throws Exception {
    // var add = new AddThread();
    // var dec = new DecThread();
    // add.start();
    // // dec.start();
    // add.join();
    // // dec.join();
    // System.out.println(Counter.count);
    // }

    /*
     * Al utilizar `join()`, estás indicando al programa principal que espere a que
     * los hilos `add` y `dec` terminen de ejecutarse antes de imprimir el valor de
     * `Counter.count`. Esto ayuda a garantizar que el valor impreso sea el
     * resultado final de las operaciones realizadas por los hilos.
     */
    public static void main(String[] args) throws Exception {
        var add = new AddThread();
        var dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter.count);
    }
    /*
     * Sin embargo, debido a la naturaleza de la concurrencia, el resultado puede
     * variar incluso al utilizar `join()`, ya que los hilos compiten por acceder y
     * modificar la variable `count`. Por lo tanto, el número impreso puede ser
     * aleatorio en diferentes ejecuciones del programa.
     * 这句话的意思是，如果线程对象`t`已经执行完成（即线程执行体中的任务已经执行完毕），那么对`t`调用`join()`方法将会立刻返回，不再阻塞调用线程。
     * 在代码中，当主线程调用`t.join()`时，主线程会等待线程`t`执行完毕，然后再继续执行自身的任务。但是，如果线程`t`在主线程调用`t.join()
     * `之前就已经执行完毕，`join()`方法会立刻返回，而不再阻塞主线程。
     * 这是因为`join()`方法实际上是在等待调用该方法的线程（在这里是主线程）等待目标线程（在这里是线程`t`）执行完毕。如果目标线程已经执行完毕，调用`
     * join()`方法就会立即返回，而不会再等待。这是一个非常有用的特性，因为它允许程序在不同的时间点等待线程的完成，而不需要知道线程是否已经结束。
     * 总体来说，`join()`方法的调用会使得当前线程等待被调用对象所表示的线程执行完毕。如果被调用对象已经执行完毕，`join()`方法会立即返回。
     */
}

class Counter {
    public static int count = 0;
}

class AddThread extends Thread {
    public void run() {
        for (int i = 0; i < 100000; i++) {
            Counter.count += 1;
            // System.out.println("Soy" + this.getName() + ": " + Counter.count);
        }
    }
}

class DecThread extends Thread {
    public void run() {
        for (int i = 0; i < 100000; i++) {
            Counter.count -= 1;
            // System.out.println("Soy" + this.getName() + ": " + Counter.count);
        }
    }
}
