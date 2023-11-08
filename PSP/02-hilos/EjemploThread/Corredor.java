
public class Corredor implements Runnable{
    private static final double MAX_INTERVALO_KM = 3;
    private static int TIEMPO_DESCANSO = 3;
    private int kmTotales;
    private int dorsal;
    private int kmRecorrido;
    public Corredor(int kmTotales,int dorsal){
        this.kmTotales = kmTotales;
        this.dorsal = dorsal;
        this.kmRecorrido = 0;
    }
    @Override
    public void run(){
        System.out.println(String.format("¡¡Soy el dorsal %d inicio mi carrera!!",dorsal, null));
        while(kmRecorrido < kmTotales){
            try{
                Thread.sleep((long)((Math.random()*TIEMPO_DESCANSO)+TIEMPO_DESCANSO));
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            kmRecorrido += Math.random()*MAX_INTERVALO_KM;
            System.out.println(String.format("Dorsal %d: %d/%d", dorsal,kmRecorrido,kmTotales));
        }
        System.out.println(String.format("¡¡Soy el dorsal %d TERMINÉ!!", dorsal));
    }
    
}
