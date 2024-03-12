package Unicast.Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteChat {
    // Ocupa cliente este puerto para enviar
    private static final int MAX_BYTE = 66535;
    private InetAddress ipEnviar;
    private int puerto;
    private String mensaje;
    private DatagramSocket clientSocket;
    private byte[] datosParaEnviar;

    public ClienteChat(String ip, int puerto) {
        try {
            this.ipEnviar = InetAddress.getByName(ip);
            this.clientSocket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.puerto = puerto;
        this.mensaje = "inicia el mensaje";
    }

    public String getMensaje() {
        return mensaje;
    }

    public void enviarUDP() {
        try {
            // INTRODUCIR DATOS POR TECLADO
            System.out.print("Mensaje para enviar: ");
            try (Scanner scanner = new Scanner(System.in)) {
                mensaje = scanner.nextLine();
            }
            datosParaEnviar = mensaje.getBytes();

            // ENVIANDO DATAGRAMA AL SERVIDOR
            DatagramPacket enviarPaquete = new DatagramPacket(datosParaEnviar, datosParaEnviar.length, ipEnviar,
                    puerto);
            clientSocket.send(enviarPaquete);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void recibirUDP() {
        try {
            System.out.println(
                    "Esperando mensaje... ");
            byte[] datosRecibidos = new byte[MAX_BYTE];
            DatagramPacket recibirPaquete = new DatagramPacket(datosRecibidos, datosRecibidos.length);
            clientSocket.receive(recibirPaquete);

            System.out.println(
                    "Mensaje recibido : " + new String(recibirPaquete.getData(), 0, recibirPaquete.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClienteChat clienteChat = new ClienteChat("localhost", 8888);
        while (true) {
            clienteChat.enviarUDP();
            clienteChat.recibirUDP();
            // while (!(clienteChat.getMensaje().equalsIgnoreCase("salir") ||
            // clienteChat.getMensaje().isBlank())) {

            // if (clienteChat.getMensaje().equalsIgnoreCase("salir") ||
            // clienteChat.getMensaje().isBlank()) {
            // System.out
            // .println("\tAlguien ha salido de chat.");
            // } else {
            // clienteChat.enviarUDP();
            // }
            // if (clienteChat.getMensaje().equalsIgnoreCase("salir") ||
            // clienteChat.getMensaje().isBlank()) {
            // System.out
            // .println("\tAlguien ha salido de chat.");
            // } else {
            // clienteChat.recibirUDP();
            // }

        }

    }
}