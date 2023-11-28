public class _1_CrearHilo {
    public static void main(String[] args) {
        ConThread conThread = new ConThread();
        conThread.start();

        ConRunnable conRunnable = new ConRunnable();
        Thread hiloRunnable = new Thread(conRunnable);
        hiloRunnable.start();

        ConRunnable conRunnable1 = new ConRunnable();
        Thread hiloRunnable1 = new Thread(conRunnable,"Runnable");
        hiloRunnable1.start();

        Thread hiloLamba = new Thread( () -> {
            System.out.println("Soy "+Thread.currentThread().getName()+ " con lambda.");
        },"hilo");
        hiloLamba.start();
    }
}

class ConThread extends Thread{

    @Override
    public void run() {
        System.out.println("Soy "+Thread.currentThread().getName()+ " con extends Thread.");
    }
}
class ConRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Soy "+Thread.currentThread().getName()+ " con implements Runnable.");
    }
}