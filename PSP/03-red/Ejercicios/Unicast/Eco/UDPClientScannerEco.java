import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClientScannerEco {
    public static void main(String[] args) {
        Scanner datos = new Scanner(System.in);

        final int SOCKET = datos.nextInt();
        datos.nextLine();
            try {
                DatagramSocket socket = new DatagramSocket();
                InetAddress IPAddress = InetAddress.getByName("localhost"); // Dirección del servidor
                //InetAddress IPAddress = InetAddress.getByName("192.168.20.119"); // Dirección del servidor
                String sentence;

                do{
                    byte[] sendData = new byte[1024];
                    sentence = datos.nextLine(); // Mensaje a enviar
                    sendData = sentence.getBytes();

                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, SOCKET);
                    socket.send(sendPacket); // Envía el paquete al servidor
                    for (int i = 0; i < 3; i++) {
                        System.out.print(".");
                        Thread.sleep(1000);
                    }
                    DatagramSocket socketServidor = new DatagramSocket(1234); // Abre el socket en el puerto recibido por args
                    byte[] receivedData = new byte[1024];
                    DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                    socketServidor.receive(receivedPacket); // Espera y recibe el paquete

                    // Extrae la información del paquete
                    String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                    System.out.println("Mensaje recibido desde servidor : " + message);

                }while(!sentence.equals("salir"));
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


    }
}
