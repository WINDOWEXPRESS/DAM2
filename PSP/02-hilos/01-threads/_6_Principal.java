public class _6_Principal {
    private static final int TOTAL_CARRERA = 100;
    private static final int NUM_CORREDORES = 10;
    private static final int STAR_DORSAL = 1000;
    private static final int TIEMPO_ESPERA = 3;
    private static final int UN_SEGUNDO = 1000;

    public static void main(String[] args) {
        // Arreglo para almacenar los hilos de los corredores
        Thread corredores[] = new Thread[NUM_CORREDORES];

        // Inicializar y comenzar los hilos de los corredores
        for (int i = 0; i < NUM_CORREDORES; i++) {
            _6_Corredor corredor = new _6_Corredor(TOTAL_CARRERA, STAR_DORSAL + i);
            corredores[i] = new Thread(corredor);
            corredores[i].start();
        }

        try {
            // Realizar una cuenta regresiva antes de comenzar la carrera
            for (int i = TIEMPO_ESPERA; i >= 0; i--) {
                System.out.println(i);
                Thread.sleep(UN_SEGUNDO);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La carrera va a comenzar!!!");

        // Notificar a todos los hilos (corredores) que la carrera ha comenzado
        synchronized (_6_Disparador.PISTOLA_SALIDA) {
            _6_Disparador.PISTOLA_SALIDA.notifyAll();
        }

        // Esperar a que el primer que llegue a la meta
        synchronized (_6_Disparador.CINTA_LLEGADA) {
            try {
                _6_Disparador.CINTA_LLEGADA.wait();
            } catch (InterruptedException e) {
                // Manejar excepciones de interrupción
                throw new RuntimeException(e);
            }
        }

        System.out.println("¡La carrera ha terminado!");

        // Interrumpir todos los hilos de los corredores cuando hay ganador
        for (int i = 0; i < NUM_CORREDORES; i++) {
            corredores[i].isAlive(); // Esto solo verifica si el hilo está vivo, pero no hace nada con el resultado
            corredores[i].interrupt(); // Interrumpir el hilo
        }
    }
}
