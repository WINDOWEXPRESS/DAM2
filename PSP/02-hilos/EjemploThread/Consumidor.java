public class Consumidor extends Thread {
    private ClaseCompartida objetoCompartido;

    Consumidor(ClaseCompartida objetoCompartido) {
        this.objetoCompartido = objetoCompartido;
    }

    @Override
    public void run() {
        // La ejecución del método run estará normalmente gestionada por un bucle
        // que controlará el ciclo de vida del hilo y se adaptará al problema.
        // En el caso de simulaciones se harán esperas proporcionales.
        try {
            // Código que hace el hilo consumidor
            objetoCompartido.accionDeConsumir();
            // La espera es opcional
            Thread.sleep((long)(Math.random()*1000+1000));
        } catch (InterruptedException ex) {

        }
    }
}
