public class _7_ProductoConsumidor {
    public static void main(String[] args) {
        MonitorCompartido monitor = new MonitorCompartido();

        Thread hiloProductor = new Thread(new Productor(monitor), "Productor");
        Thread hiloConsumidor = new Thread(new Consumidor(monitor), "Consumidor");

        hiloProductor.start();
        hiloConsumidor.start();
    }
}

class MonitorCompartido {
    private boolean valorGenerado = false;

    public synchronized void generarValor() {
        // El productor llama a este método para generar un valor
        while (valorGenerado) {
            try {
                // Esperar si ya se generó un valor
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Generar el valor
        System.out.println(Thread.currentThread().getName() + " generó un valor.");
        valorGenerado = true;

        // Notificar al consumidor que puede consumir el valor generado
        notifyAll();
    }

    public synchronized void consumirValor() {
        // El consumidor llama a este método para consumir el valor
        while (!valorGenerado) {
            try {
                // Esperar si no hay un valor generado
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Consumir el valor
        System.out.println(Thread.currentThread().getName() + " consumió el valor.");
        valorGenerado = false;

        // Notificar al productor que puede generar un nuevo valor
        notifyAll();
    }
}

class Productor implements Runnable {
    private MonitorCompartido monitor;

    public Productor(MonitorCompartido monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            // Generar un valor
            monitor.generarValor();

            // Simular un tiempo de espera antes de generar el siguiente valor
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumidor implements Runnable {
    private MonitorCompartido monitor;

    public Consumidor(MonitorCompartido monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            // Consumir un valor
            monitor.consumirValor();

            // Simular un tiempo de espera antes de consumir el siguiente valor
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
