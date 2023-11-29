class ClaseCompartida {
    int valorAccedidoSimultaneamente;

    ClaseCompartida() {
        // Se inicializa el valor
        this.valorAccedidoSimultaneamente = 0;
    }

    public synchronized void accionDeConsumir() {
        // Si no se cumple la condición para poder consumir, el consumidor debe esperar
        while (valorAccedidoSimultaneamente == 0) {
            try {
                System.out.println("Consumidor espera...");
                wait();
            } catch (InterruptedException ex) {
                // Si es necesario se realizará la gestión de la Interrupción
            }
        }

        // Cuando se ha cumplido la condición para consumir, el consumidor consume
        valorAccedidoSimultaneamente--;
        System.out.printf("Se ha consumido: %d.\n", valorAccedidoSimultaneamente);

        // Se activa a otros hilos que puedan estar en espera
        notifyAll();
    }

    public synchronized void accionDeProducir() {
        // Si no se cumple la condición para poder producir, el productor debe esperar
        while (valorAccedidoSimultaneamente > 10) {
            try {
                System.out.println("Productor espera...");
                wait();
            } catch (InterruptedException ex) {
                // Si es necesario se realizará la gestión de la Interrupción
            }
        }

        // Cuando se ha cumplido la condición para producir, el productor produce
        valorAccedidoSimultaneamente++;
        System.out.printf("Se ha producido: %d.\n", valorAccedidoSimultaneamente);

        // Se activa a otros hilos que puedan estar en espera
        notifyAll();
    }
}
