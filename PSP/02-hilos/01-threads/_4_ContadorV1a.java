/*
 * No me gusta de como ha planteado
 */

public class _4_ContadorV1a extends Thread {
    private static int contador;
    private static final Object lock = new Object();

    public _4_ContadorV1a(String name, int contador) {
        super(name);
        this.contador = contador;
    }

    public _4_ContadorV1a(String name) {
        super(name);

    }

    public synchronized void incrementar() {
        for (int i = 0; i < 10000; i++) {
            contador++;
        }

    }

    public synchronized void decrementar() {
        for (int i = 0; i < 10000; i++) {
            contador--;
        }

    }

    public int getContador() {
        return contador;

    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        _4_ContadorV1a contadorDecrementar = new _4_ContadorV1a("Prueba", 1);
        for (int i = 0; i < 5; i++) {
            _4_ContadorV1a c = new _4_ContadorV1a("Prueba");
            c.start();
            c.incrementar();
        }
        for (int i = 0; i < 5; i++) {
            _4_ContadorV1a d = new _4_ContadorV1a("Prueba");
            d.start();
            d.decrementar();
        }

        _4_ContadorV1a visualizarContador = new _4_ContadorV1a("ver");

        System.out.println(visualizarContador.getContador());

    }

}
