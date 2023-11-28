public class EjemploJoin {
    public static void main(String[] args) {
        // Crear un hilo
        Thread miHilo = new MiHilo();

        // Iniciar el hilo
        miHilo.start();

        try {
            // Esperar a que el hilo termine antes de continuar
            miHilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Este código se ejecutará después de que el hilo haya terminado
        System.out.println("El hilo ha terminado, continuando con el hilo principal.");
    }
}

class MiHilo extends Thread {
    public void run() {
        // Código que se ejecuta en el hilo
        for (int i = 1; i <= 5; i++) {
            System.out.println("Contador: " + i);
            try {
                // Simular una tarea que toma tiempo
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
