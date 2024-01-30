package Unicast.Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class ServidorChat {
    private static final int MAX_BYTE = 66535;

    private DatagramSocket serverSocket;
    private DatagramPacket paqRecibido;
    private InetAddress ipOrigen;
    private DatagramPacket paqEnviado;
    private int puerto;

    public ServidorChat(int puerto) {
        try {
            serverSocket = new DatagramSocket(puerto);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void enviarUDP() {

        try {
            System.out.print("Mensaje para enviar : ");

            Scanner scanner = new Scanner(System.in);
            String mensaje = scanner.nextLine();
            byte[] datos = mensaje.getBytes();

            paqEnviado = new DatagramPacket(datos, datos.length, ipOrigen, puerto);
            serverSocket.send(paqEnviado);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void recibirUDP() {
        try {
            byte[] datosRecibidos = new byte[MAX_BYTE];
            // RECIBO DATAGRAMA
            paqRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);
            serverSocket.receive(paqRecibido);

            // DIRECCION ORIGEN
            ipOrigen = paqRecibido.getAddress();
            puerto = paqRecibido.getPort();

            System.out.println("Origen: " + ipOrigen + ":" + puerto);
            System.out.println(
                    "Mensaje recibido : \n\t" + new String(paqRecibido.getData(), 0, paqRecibido.getLength()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ServidorChat servidorChat = new ServidorChat(8888);
        while (true) {
            servidorChat.recibirUDP();
            servidorChat.enviarUDP();
        }
    }
}
