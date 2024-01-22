import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerUDP {
	private static final int MAX_LENGTH = 65535;

	public static void main(String args[]) {

		try {
			DatagramSocket ds = new DatagramSocket(8000, InetAddress.getByName("0.0.0.0"));
			byte[] buffer = new byte[MAX_LENGTH];
			while (true) {
				DatagramPacket p = new DatagramPacket(
						buffer,
						MAX_LENGTH);

				ds.receive(p);
				System.out
						.println(p.getSocketAddress().toString() + " " + p.getAddress().toString() + " " + p.getPort());
				System.out.println(new String(p.getData(), 0, p.getLength()));
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
