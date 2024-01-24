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
            socket = new MulticastSocket(1234);
            group = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(group);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                try {
                    socket.receive(packet);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                String received = new String(
                        packet.getData(), 0, packet.getLength());
                System.out.println(received);
                if ("end".equals(received)) {
                    break;
                }
            }

            socket.leaveGroup(group);

            socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}