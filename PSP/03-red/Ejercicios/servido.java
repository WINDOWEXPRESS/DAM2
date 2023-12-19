
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class servido {
    private static final int MAX_BYTE = 66535;
    public static final int PUERTO = 8080;
    public static void main(String[] args) {
        try {
            DatagramSocket rsocket = new DatagramSocket(PUERTO);
            DatagramSocket sSocket = new DatagramSocket();
            DatagramPacket rPacket = null;
            DatagramPacket sPacket = null;
            byte[] rData = new byte[MAX_BYTE];
            byte[] sData = new byte[MAX_BYTE];
            InetAddress ipAddress = null;
            while (true) {
                rPacket = new DatagramPacket(rData,rData.length); 
                rsocket.receive(rPacket);
                String mesagger = new String(rPacket.getData(),0,rPacket.getLength());
                
                mesagger = mesaggerReverser(mesagger);
                ipAddress = InetAddress.getByName(rPacket.getAddress().getHostAddress());
                sData = mesagger.getBytes();
                sPacket = new DatagramPacket(sData,sData.length,ipAddress,cliente.PUERTO);
                sSocket.send(sPacket);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static String mesaggerReverser(String messager){
        String newMessager = "";
        for(int i = messager.length() -1; i >= 0; i--){
            newMessager += messager.charAt(i);
        }
        return newMessager;
    }
}
