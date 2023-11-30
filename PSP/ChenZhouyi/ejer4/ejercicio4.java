package ejer4;

public class ejercicio4 {

    private static final int POSICION_COHETE = 0;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Falta parametro.");
        }
        int cantidadCohete = Integer.parseInt(args[POSICION_COHETE]);
        if (cantidadCohete < 0) {
            System.out.println("El numero de cohete no puede ser negativo.");
        } else {

            Thread cohetes[] = new Thread[cantidadCohete];

            // Inicializar y comenzar los hilos de los corredores
            for (int i = 0; i < cantidadCohete; i++) {
                AEL cohete = new AEL(i);
                cohetes[i] = new Thread(cohete);
                cohetes[i].start();
            }

        }
        Islantilla.cohetesLanzado =  cantidadCohete;
        while (Islantilla.cohetesTotal != cantidadCohete) {

            synchronized (Islantilla.CONTROL) {
                try {
                    Islantilla.CONTROL.wait();
                } catch (InterruptedException e) {
                    // Manejar excepciones de interrupción
                    throw new RuntimeException(e);
                }
            }

        }
        System.out.println("Han fracasado " + Islantilla.getExplotado());
        System.out.println("Han llegado " + Islantilla.getLlegado());
        System.out.println("La operacion ha sido " + (Islantilla.esExito() ? "exito" : "fracaso"));

    }

}