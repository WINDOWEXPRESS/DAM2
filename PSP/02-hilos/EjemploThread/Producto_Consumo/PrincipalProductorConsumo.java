package Producto_Consumo;


public class PrincipalProductorConsumo {

    public static void main(String[] args) {    
        ClaseCompartida objetoCompartido = new ClaseCompartida();
        Productor productor  = new Productor(objetoCompartido);
        Consumidor consumidor  = new Consumidor(objetoCompartido);
        productor.start();
        consumidor.start();

        // No es obligatorio, pero en ocasiones puede interesar que la ClasePrincipal
        // espere a que acaben los hilos
        try {
            productor.join();
            consumidor.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Acciones a realizar una vez hayan acabado el productor y el consumidor
    }
    
}
