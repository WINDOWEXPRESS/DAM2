package ejer4;

public class AEL implements Runnable {
    private static final int RECORRIDO = 50;
    private static final int INTERVALO_MINIMO_DORMIR = 500;
    private static final int INTERVALO_MAXIMO_DORMIR = 1000;
    private static final int ESTACION_ESPACIAL = 408;
    private int kmRecorrido = 0;
    private boolean explotado;
    private int codigoCohete;

    boolean haFallado() {
        return ((float) Math.random() < 0.1);
    }

    long salida;
    long llegada;

    public AEL(int codigoCohete) {
        this.kmRecorrido = 0;
        this.explotado = false;
        this.codigoCohete = codigoCohete;
    }

    @Override
    public void run() {

        while (kmRecorrido < ESTACION_ESPACIAL && !explotado) {
            // Avanzar en la carrera
            kmRecorrido += RECORRIDO;

            //System.out.println("Soy el cohete " + codigoCohete + " y he recorrido " +
            //        kmRecorrido);
            // Simular tiempo de descanso entre intervalos de carrera
            try {
                Thread.sleep((long) ((Math.random() * INTERVALO_MAXIMO_DORMIR - INTERVALO_MINIMO_DORMIR)
                        + INTERVALO_MINIMO_DORMIR));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            explotado = haFallado();

        }
        if (explotado) {
            synchronized (Islantilla.CONTROL) {
                Islantilla.cohetesTotal += 1;
                Islantilla.incrementarExplotado();
            }
        } else {
            synchronized (Islantilla.CONTROL) {
                Islantilla.cohetesTotal += 1;
                Islantilla.incrementarLlegado();
            }
        }
        if(Islantilla.cohetesLanzado == Islantilla.cohetesTotal){
            synchronized (Islantilla.CONTROL) {
                Islantilla.CONTROL.notifyAll();
            }
        }
        
    }
}
