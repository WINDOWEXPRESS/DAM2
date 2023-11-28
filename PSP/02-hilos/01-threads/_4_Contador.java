public class _4_Contador extends Thread{
    private static int contador;

    public _4_Contador(String name, int contador) {
        super(name);
        this.contador = contador;
    }

    public void incrementar(){
        contador++;
    }

    public int getContador() {
        return contador;

    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            incrementar();
        }

        System.out.println(getContador());
    }

    public static void main(String[] args) {
        _4_Contador contador1 = new _4_Contador("Prueba",1);
        _4_Contador contador2 = new _4_Contador("Prueba",1);

        contador2.start();
        contador1.start();

        System.out.println("contador1.getContador() :"+contador1.getContador());
        System.out.println("contador2.getContador() :"+contador2.getContador());
    }
}
