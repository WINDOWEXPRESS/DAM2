import java.util.Scanner;

public class PrincipalSimulador {
    static Scanner datos = new Scanner(System.in);

    public static void main(String[] args) {

        //1 recoger parametros ip puertos
        //2. bucle ,menu ,recoger opcion, enviar a ascensor comando
        SimuladorUI simuladorUI = new SimuladorUI();

       
        AscensorInterface asc1 = new Ascensor();
        Thread hilo = new Thread((Runnable) asc1);

        //getDatos(asc1);

        hilo.start();

        while(true){
            simuladorUI.pintarMenu();
            if(simuladorUI.obtenerSeleccion() == simuladorUI.SUBIR){
                asc1.subir();
            }else if(simuladorUI.obtenerSeleccion() == simuladorUI.BAJAR){
                asc1.bajar();
            }else{
                System.out.println("Salir de ascensor.");
                break;
            }
            synchronized (Ascensor.Lock) {
                Ascensor.Lock.notifyAll();
            }
        }


        
    }

    private static void getDatos(AscensorInterface ascensor) {

        System.out.println("Introducir el ip :");
        String ip = datos.nextLine();
        System.out.println("Introducir el puerto :");
        int puerto = datos.nextInt();
        System.out.println("Introducir el codigo del ascensor :");
        int id = datos.nextInt();

        ascensor.setConfig(id, ip, puerto);
    }
}
