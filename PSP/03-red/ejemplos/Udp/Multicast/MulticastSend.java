import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

public class MulticastSend {

	public static void main(String[] args) {
		try {

			Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface netint : Collections.list(nets)) {
				System.out.println(netint);
			}
			Scanner in = new Scanner(System.in);
			System.out.println("Especifica el nombre del interfaz");
			// String iName = in.nextLine();
			// String iName = "enp0s3";
			String iName = "wlan0";
			NetworkInterface netIf = NetworkInterface.getByName(iName);
			System.out.println(netIf);

			int port = 1234;

			InetAddress mcastaddr = InetAddress.getByName("230.0.0.1");
			InetSocketAddress group = new InetSocketAddress(mcastaddr, port);
			MulticastSocket s = new MulticastSocket(port);

			s.joinGroup(group, netIf);

			String msg = "Hello desde ordenador 4";
			byte[] msgBytes = msg.getBytes();
			DatagramPacket hi = new DatagramPacket(msgBytes, msgBytes.length, group);
			s.send(hi);

			// get their responses!

			// byte[] buf = new byte[1000];
			// DatagramPacket recv = new DatagramPacket(buf, buf.length);
			// s.receive(recv);

			// System.out.println(new String(recv.getData(), 0, recv.getLength()));

			// OK, I'm done talking - leave the group...
			s.leaveGroup(group, netIf);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
/* 
    public static void main(String[] args) {
        try {
            // Obtener la lista de interfaces de red disponibles
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                System.out.println(netint);
            }

            // Solicitar al usuario que especifique el nombre del interfaz de red
            Scanner in = new Scanner(System.in);
            System.out.println("Especifica el nombre del interfaz");
            String iName = in.nextLine(); // Leer el nombre del interfaz
            NetworkInterface netIf = NetworkInterface.getByName(iName); // Obtener el interfaz por su nombre
            System.out.println(netIf);

            int port = 1234; // Puerto para la comunicación multicast

            InetAddress mcastaddr = InetAddress.getByName("230.0.0.1"); // Dirección IP multicast
            InetSocketAddress group = new InetSocketAddress(mcastaddr, port); // Dirección y puerto multicast
            MulticastSocket s = new MulticastSocket(port); // Crear un socket multicast

            // Unirse al grupo multicast en la interfaz de red especificada
            s.joinGroup(group, netIf);

            String msg = "Hello desde ordenador 4"; // Mensaje a enviar
            byte[] msgBytes = msg.getBytes(); // Convertir el mensaje a bytes
            DatagramPacket hi = new DatagramPacket(msgBytes, msgBytes.length, group); // Crear un paquete de datos
            s.send(hi); // Enviar el mensaje al grupo multicast

            // Dejar el grupo multicast después de enviar el mensaje
            s.leaveGroup(group, netIf);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/