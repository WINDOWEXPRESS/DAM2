public class _3_CancionInfantil implements Runnable{
    private static final String[] NUMEROS_En_LETRAS = {"Un", "Dos", "Tres", "Cuatro", "Cinco",
            "Seis", "Siete", "Ocho", "Nueve", "Diez"};
    private static final String LUGAR = "sobre tela de una araña";
    private String animal;
    private String accion;
    private int numeroDeVeces;
    public _3_CancionInfantil(String animal, String accion,int numeroDeVeces) {
        this.animal = animal;
        this.accion = accion;
        this.numeroDeVeces = numeroDeVeces;
    }

    @Override
    public void run() {
        for (int i = 0; i < numeroDeVeces; i++) {
            System.out.println(NUMEROS_En_LETRAS[i]+" "+animal+" "+accion +" "+LUGAR);

            if(((int)(Math.random() * 200000) + 100000) % 2 == 0){
                System.out.println("Primo");
            }else {
                System.out.println("No es primo");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        _3_CancionInfantil estrofa = new _3_CancionInfantil("elefante","se balanceaba",5);
        Thread hiloElefante = new Thread(estrofa,"elefante");
        hiloElefante.start();


        _3_CancionInfantil cancion = new _3_CancionInfantil("perro","se ladraba",5);
        Thread hiloPerro = new Thread(cancion,"perro");
        //la prioridad no garantiza que un hilo de mayor prioridad siempre
        // se ejecute antes que un hilo de menor prioridad.
        hiloPerro.setPriority(3);

        _3_CancionInfantil cancion1 = new _3_CancionInfantil("gato","se maullaba",5);
        Thread hiloGato = new Thread(cancion1);
        hiloGato.setPriority(7);


        _3_CancionInfantil cancion2 = new _3_CancionInfantil("loro","se tuturuaba",5);
        Thread hiloLoro = new Thread(cancion2);
        hiloLoro.setPriority(10);

        hiloPerro.start();
        hiloGato.start();
        hiloLoro.start();

        try {
            hiloElefante.join();
            hiloPerro.join();
            hiloGato.join();
            hiloLoro.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
