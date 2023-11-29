public class _6_Corredor implements Runnable {
    private static final double MAX_INTERVALO_KM = 3;
    private static int TIEMPO_DESCANSO = 3;

    private int kmTotales;
    private int dorsal;
    private int kmRecorrido;
    private long time;
    long salida;
    long llegada;

    public _6_Corredor(int kmTotales, int dorsal) {
        this.kmTotales = kmTotales;
        this.dorsal = dorsal;
        this.kmRecorrido = 0;
    }

    @Override
    public void run() {
        System.out.println(String.format("¡¡Soy el dorsal %d estoy listo para correr!!", dorsal));

        // Esperar a que todos los corredores estén en la línea de salida
        esperar();

        try {
            // Marcar el tiempo de salida
            salida = System.currentTimeMillis();
            System.out.println(String.format("¡¡Soy el dorsal %d inicio mi carrera!!  Tiempo : %d", dorsal, salida));

            // Simular la carrera hasta alcanzar la meta
            while (kmRecorrido < kmTotales) {
                // Simular tiempo de descanso entre intervalos de carrera
                Thread.sleep((long) ((Math.random() * TIEMPO_DESCANSO) + TIEMPO_DESCANSO));

                // Avanzar en la carrera
                kmRecorrido += Math.random() * MAX_INTERVALO_KM;
                System.out.println(String.format("Dorsal %d : %d/%d", dorsal, kmRecorrido, kmTotales));
            }

            // Marcar el tiempo de llegada
            llegada = System.currentTimeMillis();
            time = llegada - salida;

            // Imprimir mensaje al llegar a la meta
            System.out.println(String.format("¡¡Soy el dorsal %d TERMINÉ!! He llegado a la meta en %d milisegundos. Tiempo : %d", dorsal, time, llegada));

            // Notificar al disparador de la llegada
            synchronized (_6_Disparador.PISTOLA_LLEGADA) {
                _6_Disparador.PISTOLA_LLEGADA.notify();
            }
        } catch (InterruptedException e) {
            // En caso de interrupción, imprimir mensaje parcial de la carrera
            System.out.println(String.format("Soy Dorsal %d he llegado hasta %d/%d", dorsal, kmRecorrido, kmTotales));
        }
    }

    // Método para esperar en la línea de salida
    public void esperar() {
        synchronized (_6_Disparador.PISTOLA_SALIDA) {
            try {
                _6_Disparador.PISTOLA_SALIDA.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
