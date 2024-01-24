import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class PrincipalSimulador {
    static Scanner datos = new Scanner(System.in);

    public static void main(String[] args) {

        //1 recoger parametros ip puertos
        //2. bucle ,menu ,recoger opcion, enviar a ascensor comando
        SimuladorUI simuladorUI = new SimuladorUI();

        AscensorInterface asc1 = new Ascensor();

        asc1.setConfig(01,"192.168.20.204" ,8888);

        Thread hilo = new Thread((Runnable) asc1);

        //getDatos(asc1);
        Thread hiloUDP = new Thread((Runnable) new UDP.Servidor());
        hiloUDP.start();
        hilo.start();

        while(true){
            simuladorUI.pintarMenu();
            if(simuladorUI.obtenerSeleccion() == simuladorUI.SUBIR){
                asc1.subir();
            }else if(simuladorUI.obtenerSeleccion() == simuladorUI.BAJAR){
                asc1.bajar();
            }else{
                System.out.println("Salir de ascensor.");
                try {
                    hiloUDP.interrupt();
                    hilo.interrupt();
                    
                } catch (Exception e) {
                    System.err.println(""+e);
                }

                break;
            }
            /*synchronized (Ascensor.Lock) {
                Ascensor.Lock.notifyAll();
            }*/
            //obetenerInfoUDP();
        }
    }

    private static void obetenerInfoUDP(){
        try{
            DatagramSocket  Socket = new DatagramSocket(8888);
            InetAddress ipAddress = InetAddress.getByName("192.168.20.204");
            byte[] rData = null;
            DatagramPacket rPacket = null;
            String messager = "";
                    rData = new byte[1024];
                    rPacket = new DatagramPacket(rData,rData.length);
                    Socket.receive(rPacket);
                    messager = new String(rPacket.getData(),0,rPacket.getLength());
                    System.out.println("Mensaje recibido : " + messager);
            Socket.close();
        }catch(Exception e){
                System.out.println(e.getMessage());
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
