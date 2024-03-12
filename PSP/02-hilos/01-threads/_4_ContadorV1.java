public class _4_ContadorV1 extends Thread {
    private static int contador;
    private static final Object lock = new Object();

    public _4_ContadorV1(String name, int contador) {
        super(name);
        this.contador = contador;
    }

    public void incrementar() {
        contador++;
    }

    public int getContador() {
        return contador;

    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            synchronized (lock) {//puede ser en bloque o en metodo
                incrementar();
            }

        }

        System.out.println(getContador());
    }

    public static void main(String[] args) {
        _4_ContadorV1 contador1 = new _4_ContadorV1("Prueba", 1);
        _4_ContadorV1 contador2 = new _4_ContadorV1("Prueba", 1);

        // Al poner star aqui el contador1 , sout que esta dentro de run el valor es
        // aleatorio
        contador1.start();
        contador2.start();

        try {
            // Al poner star aqui el contador1 , sout que esta dentro de run el valor es es
            // siempre 1001
            // contador1.start();
            contador1.join();

            // contador2.start();
            contador2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("contador1.getContador() :" + contador1.getContador());
        System.out.println("contador2.getContador() :" + contador2.getContador());
    }
}
