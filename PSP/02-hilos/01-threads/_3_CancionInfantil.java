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
    }
}
