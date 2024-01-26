package Unicast.Eco;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClientScannerEco {
    private static final int MAX_BYTE = 1024;

    public static void main(String[] args) {
        Scanner datos = new Scanner(System.in);

        final int SOCKET = datos.nextInt();
        datos.nextLine();
        try {
            DatagramSocket socketEnviar = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost"); // Dirección del servidor
            // InetAddress IPAddress = InetAddress.getByName("192.168.20.119");

            String sentence;

            DatagramSocket socketRecibir;
            socketRecibir = new DatagramSocket(1234); // 1234 como puerto por defecto
            do {
                System.out.print("Mensaje para enviar :");
                byte[] sendData = new byte[MAX_BYTE];
                sentence = datos.nextLine(); // Mensaje a enviar
                sendData = sentence.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, SOCKET);
                socketEnviar.send(sendPacket); // Envía el paquete al servidor

                // Esperar 3s para mostrar
                for (int i = 0; i < 3; i++) {
                    System.out.print(".");
                    Thread.sleep(1000);
                }
                System.out.println();

                byte[] receivedData = new byte[MAX_BYTE];
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socketRecibir.receive(receivedPacket); // Espera y recibe el paquete

                // Extrae la información del paquete
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("Mensaje recibido desde servidor : " + message);

            } while (!sentence.equals("salir"));
            // Cerrar los socket
            socketEnviar.close();
            socketRecibir.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
