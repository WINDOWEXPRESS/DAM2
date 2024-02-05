import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastReceiver extends Thread {
    protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];

    public void run() {
        InetAddress group;

        try {
            // Se crea un socket multicast en el puerto 1234
            socket = new MulticastSocket(1234);

            // Se obtiene la dirección del grupo multicast (230.0.0.1)
            group = InetAddress.getByName("230.0.0.1");

            // Se une al grupo multicast
            socket.joinGroup(group);

            while (true) {
                // Se prepara un DatagramPacket para recibir datos
                DatagramPacket packet = new DatagramPacket(buf, buf.length);

                try {
                    // Se recibe un paquete del socket
                    socket.receive(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Se convierten los datos recibidos a una cadena
                String received = new String(packet.getData(), 0, packet.getLength());

                // Se muestra en consola el mensaje recibido
                System.out.println(received);

                // Si el mensaje es "end", se sale del bucle
                if ("end".equals(received)) {
                    break;
                }
            }

            // Se abandona el grupo multicast
            socket.leaveGroup(group);

            // Se cierra el socket
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Se crea una instancia del receptor multicast y se inicia
        MulticastReceiver receiver = new MulticastReceiver();
        receiver.start();
    }
}
