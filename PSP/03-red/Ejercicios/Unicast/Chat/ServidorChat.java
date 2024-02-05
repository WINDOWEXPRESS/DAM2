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
    private String mensaje;

    public ServidorChat(int puerto) {
        try {
            serverSocket = new DatagramSocket(puerto);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mensaje = "inicia el mensaje";
    }

    public String getMensaje() {
        return mensaje;
    }

    public void enviarUDP() {

        try {
            System.out.print("Mensaje para enviar : ");

            Scanner scanner = new Scanner(System.in);
            mensaje = scanner.nextLine();
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
            System.out.println(
                    "Esperando mensaje... ");
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
        while (!(servidorChat.getMensaje().equalsIgnoreCase("salir") || servidorChat.getMensaje().isBlank())) {
            if (servidorChat.getMensaje().equalsIgnoreCase("salir") || servidorChat.getMensaje().isBlank()) {
                System.out.println("\tAlguien ha salido de chat. ");
            } else {
                servidorChat.recibirUDP();
            }
            if (servidorChat.getMensaje().equalsIgnoreCase("salir") || servidorChat.getMensaje().isBlank()) {
                System.out.println("\tAlguien ha salido de chat. ");
            } else {
                servidorChat.enviarUDP();
            }
        }
    }

}
