import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClientScanner {
    public static void main(String[] args) {
        Scanner datos = new Scanner(System.in);

        final int SOCKET = datos.nextInt();
        datos.nextLine();
            try {
                DatagramSocket socket = new DatagramSocket();
                //InetAddress IPAddress = InetAddress.getByName("localhost"); // Dirección del servidor
                InetAddress IPAddress = InetAddress.getByName("192.168.20.119"); // Dirección del servidor
                String sentence;
                do{
                    byte[] sendData = new byte[1024];
                    sentence = datos.nextLine(); // Mensaje a enviar
                    sendData = sentence.getBytes();

                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, SOCKET);
                    socket.send(sendPacket); // Envía el paquete al servidor
                }while(!sentence.equals("salir"));
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


    }
}
