import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Ascensor implements AscensorInterface, Runnable {
    public static final Object Lock = new Object();
    private static final int MAX_BYTE = 1024;
    final int TIEMPO_ESPERA = 100;
    final int UN_SEGUNDO = 1000;
    int id = 0;
    String ip = "";
    int puerto = 0;
    int planta = 0;
    char direccion = ' ';
    DatagramSocket  Socket ;
    @Override
    public synchronized void subir() {
        direccion = 'U';
    }

    @Override
    public synchronized void bajar() {
        direccion = 'D';
    }

    @Override
    public synchronized String getPlanta() {
        if (planta == 0) {
            return "PB";
        }
        return String.valueOf(planta);
    }

    @Override
    public synchronized String toProtocolo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void run() {
        String informacion;
        while (true) {
            informacion = String.format("[%02d;%2s;%c]\t", id, getPlanta(), direccion);
            if (direccion == ' ') {
                try {
                    Thread.sleep(UN_SEGUNDO);
                    enviarInfoUDP(informacion);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else{
                try {
                    for (int i = TIEMPO_ESPERA; i < UN_SEGUNDO; i += TIEMPO_ESPERA) {
                        enviarInfoUDP(informacion);
                        Thread.sleep(TIEMPO_ESPERA);
                    }
                    if (direccion == 'U') {
                        planta += 1;
                    } else {
                        planta -= 1;
                    }
                    enviarInfoUDP("DIN!!! Has llegado a la planta "+getPlanta());
                    direccion = ' ';
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    @Override
    public void setConfig(int id, String ip, int puerto) {
        this.id = id;
        this.ip = ip;
        this.puerto = puerto;
    }

    public void enviarInfoUDP(String informacion){
        try{
            this.Socket = new DatagramSocket();

            InetAddress ipAddress = InetAddress.getByName(ip);
            byte[] Data = null;
            DatagramPacket Packet = null;

                    Data = new byte[MAX_BYTE];
                    Data = informacion.getBytes();
                    Packet = new DatagramPacket(Data,Data.length,ipAddress,puerto);
                    Socket.send(Packet);

            this.Socket.close();
        }catch(Exception e){
                System.out.println(e.getMessage());
        }
    }
}
