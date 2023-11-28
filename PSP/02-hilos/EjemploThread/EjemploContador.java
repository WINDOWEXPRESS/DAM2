class Contador {
    // monitor contador
    private int actual;
    public Contador(int inicial)
    {
        actual = inicial;
    }
    public synchronized void inc() { actual++; }
    public synchronized void dec() { actual--; }
    public synchronized int valor() { return actual;}
}

class Usuario extends Thread {
    // clase hilo usuario
    private Contador cnt;
    public Usuario(String nombre, Contador cnt) {
        super(nombre); this.cnt = cnt;
    }
    public void run() {
        for (int i = 0; i < 1000; i++) {
            cnt.inc();
            System.out.println("Hola, soy " + this.getName() + ", mi contador vale " + cnt.valor());
        }
    }
}

class EjemploContador {
    // principal
    final static int nHebras = 20;
    public static void main(String[] args) {
        // metodo principal
        final Contador cont1 = new Contador(10);
        Usuario hebra[] = new Usuario[nHebras];
        for (int i = 0; i < nHebras; i++) {
            //crea hebras
            hebra[i] = new Usuario("la hebra-" + i, cont1);
            // lanza hebras
            hebra[i].start(); }
    }

}
/*
Es importante hacer notar, aunque suene a pesado, que todos los hilos
acceden al código protegido del objeto cont1 que se crea en el método main.
Así, no puede haber dos hilos ejecutando a la vez ninguna de los tres
métodos de la instancia cont1 de la clase Contador.
 */