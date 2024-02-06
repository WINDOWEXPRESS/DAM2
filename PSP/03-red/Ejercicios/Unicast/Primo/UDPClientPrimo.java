import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClientPrimo {
    public static void main(String[] args) {
        try {
            Scanner datos = new Scanner(System.in);
            System.out.println("Introducir un numero entero y el servidor te dira si es primo o no.");
            int numero = datos.nextInt();
            // instancia el byte a una cpacidad de 1024
            byte[] sendData = new byte[1024];
            // Convertir el número entero a un arreglo de bytes .
            // ByteBuffer.allocate(4) crea un ByteBuffer con capacidad para 4 bytes
            sendData = intToByteArray(numero);
            DatagramSocket socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost"); // Dirección del servidor
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            socket.send(sendPacket); // Envía el paquete al servidor

            // instancia el byte a una cpacidad de 1024
            byte[] datosRecibido = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibido, datosRecibido.length);
            socket.receive(paqueteRecibido);
            System.out.println(
                    new String(paqueteRecibido.getData(), paqueteRecibido.getOffset(), paqueteRecibido.getLength()));
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] intToByteArray(int number) {
        return new byte[] {
                (byte) (number >>> 24),
                (byte) (number >>> 16),
                (byte) (number >>> 8),
                (byte) number
        };
    }
    /*
     * (byte) (number >>> 24): Desplaza los bits del número a la derecha por 24
     * lugares y toma solo los últimos 8 bits, convirtiéndolos a un byte.
     * (byte) (number >>> 16): Similar al paso anterior, pero desplazando por 16
     * lugares.
     * (byte) (number >>> 8): Similar al paso anterior, pero desplazando por 8
     * lugares.
     * (byte) number: Toma los últimos 8 bits del número y los convierte a un byte.
     * Estos bytes se almacenan en un nuevo array y se devuelven.
     */
}
