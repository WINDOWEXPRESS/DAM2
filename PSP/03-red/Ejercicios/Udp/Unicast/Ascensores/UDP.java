import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP {
    public static class Servidor implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    DatagramSocket Socket = new DatagramSocket(8888);
                    InetAddress ipAddress = InetAddress.getByName("192.168.20.204");
                    byte[] rData = null;
                    DatagramPacket rPacket = null;
                    String messager = "";
                    rData = new byte[1024];
                    rPacket = new DatagramPacket(rData, rData.length);
                    Socket.receive(rPacket);
                    messager = new String(rPacket.getData(), 0, rPacket.getLength());
                    System.out.println("Mensaje recibido : " + messager);
                    Socket.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public static class Cliente implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'run'");
        }

    }

}
