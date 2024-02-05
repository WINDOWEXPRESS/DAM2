import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerSocketPorParametro {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error de parametros!");
        } else {
            final int SOCKET = Integer.parseInt(args[0]);
            try {
                try (DatagramSocket socket = new DatagramSocket(SOCKET)) {
                    byte[] receivedData = new byte[1024];

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
}