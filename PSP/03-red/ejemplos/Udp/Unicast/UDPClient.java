import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    private final static int PUERTO = 9876;
    private final static String DIRECCION = "localhost";
    private final static int TAMANIO = 1024;
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(DIRECCION); // Dirección del servidor
            byte[] sendData = new byte[TAMANIO];
            String sentence = "Hola desde el cliente"; // Mensaje a enviar
            sendData = sentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, PUERTO);
            socket.send(sendPacket); // Envía el paquete al servidor
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
