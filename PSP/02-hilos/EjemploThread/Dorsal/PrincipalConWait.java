package Dorsal;
public class PrincipalConWait {

    private static final int TOTAL_CARRERA = 100;
    private static final int NUM_CORREDORES = 10;
    private static final int STAR_DORSAL = 1000;

    public static void main(String[] args) {
        Thread corredores[] = new Thread[NUM_CORREDORES];

        for (int i = 0; i < NUM_CORREDORES; i++) {
            corredores[i] = new Thread(new CorredorConWait(TOTAL_CARRERA, STAR_DORSAL + i));
        }

        System.out.println("La carrera va a comenzar!!!");
        for (int i = 0; i < NUM_CORREDORES; i++) {
            corredores[i].start();
            try {
                synchronized (corredores[i]) {
                    corredores[i].wait();
                }

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for (int i = 0; i < NUM_CORREDORES; i++) {
            try {
                corredores[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        corredores[0].notifyAll();

        System.out.println("¡La carrera ha terminado!");
    }
}