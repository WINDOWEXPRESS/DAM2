public class _2_TablasMultiplicar implements Runnable {
    private int numero;

    public _2_TablasMultiplicar(int numero) {
        this.numero = numero;
    }

    @Override
    public void run() {
        System.out.println("Tabla del " + numero + ":");
        for (int i = 1; i <= numero; i++) {
            System.out.println(numero + " x " + i + " = " + (numero * i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 5; // Número de Threads

        // Crear array de Threads
        Thread[] threads = new Thread[n];

        // Instanciar y ejecutar los Threads
        for (int i = 0; i < n; i++) {
            _2_TablasMultiplicar tablaThread = new _2_TablasMultiplicar(i + 1);
            threads[i] = new Thread(tablaThread);
            threads[i].setName("Thread-" + (i + 1));
            threads[i].start();

            /*//Por que quiere ordenada
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
        }

        // Esperar a que todos los Threads terminen
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("Todas las tablas han sido escritas.");
    }
}
/*
    Para ejecutar y redirigir la salida a un archivo en la terminal de Linux, puedes hacer lo siguiente:
        java TablasMultiplicar > salida.txt

    Luego, para verificar y ordenar las tablas multiplicativas en el archivo, puedes usar el comando sort:
        sort salida.txt
 */