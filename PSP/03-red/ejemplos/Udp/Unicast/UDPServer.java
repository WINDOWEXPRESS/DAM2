import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    private final static int PUERTO = 9876;
    private final static int TAMANIO = 1024;
    public static void main(String[] args) {
        try {
            try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
                byte[] receivedData = new byte[TAMANIO];

                while (true) {
                    DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                    socket.receive(receivedPacket); // Espera y recibe el paquete

                    // Extrae la información del paquete
                    String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                    System.out.println("Mensaje recibido: " + message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}