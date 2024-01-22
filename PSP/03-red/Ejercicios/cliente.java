
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


public class cliente {
    public static final int PUERTO = 8080;
    private static final int MAX_BYTE = 66535;
    private static final String OUT = "salir";
    public static void main(String[] args) {
        String input = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce algo para hacer reverse de palabra");
        try{
            DatagramSocket  sSocket = new DatagramSocket();
            DatagramSocket  rSocket = new DatagramSocket(PUERTO);
            InetAddress ipAddress = InetAddress.getByName("192.168.20.104");
            byte[] sData = null;
            byte[] rData = null;
            DatagramPacket sPacket = null;
            DatagramPacket rPacket = null;
            String messager = "";
            do {
                input = sc.nextLine();
                    sData = new byte[MAX_BYTE];
                    sData = input.getBytes();
                    sPacket = new DatagramPacket(sData,sData.length,ipAddress,8080);
                    sSocket.send(sPacket);
                    
                    rData = new byte[MAX_BYTE];
                    rPacket = new DatagramPacket(rData,rData.length);
                    rSocket.receive(rPacket);
                    messager = new String(rPacket.getData(),0,rPacket.getLength());
                    System.out.println("Mesagger reverse: " + messager);
            } while(!input.toLowerCase().equals(OUT));
            sSocket.close();
        }catch(Exception e){
                System.out.println(e.getMessage());
        }
    }
}
