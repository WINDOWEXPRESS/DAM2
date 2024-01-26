package Unicast.Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteChat {
    // Ocupa cliente este puerto para enviar
    private static final int MAX_BYTE = 66535;
    private static final String OUT = "salir";
    private String ip;
    private int puerto;
    private String mensaje;
    private static Scanner scanner;

    public ClienteChat(String ip, int puerto) {
        this.ip = ip;
        this.puerto = puerto;
        mensaje = "";
        scanner = new Scanner(System.in);
    }

    public void enviarUDP() {

        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            DatagramSocket enviarSocket = new DatagramSocket();
            byte[] datosParaEnviar = null;
            DatagramPacket enviarPaquete = null;

            System.out.println("Mensaje para enviar: ");
            mensaje = scanner.nextLine();
            datosParaEnviar = new byte[MAX_BYTE];
            datosParaEnviar = mensaje.getBytes();
            enviarPaquete = new DatagramPacket(datosParaEnviar, datosParaEnviar.length, ipAddress,
                    puerto);
            enviarSocket.send(enviarPaquete);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void recibirUDP(int puerto) {
        try {
            DatagramSocket rSocket = new DatagramSocket(puerto);
            byte[] rData = null;
            DatagramPacket rPacket = null;
            mensaje = "";

            rData = new byte[MAX_BYTE];
            rPacket = new DatagramPacket(rData, rData.length);
            rSocket.receive(rPacket);
            System.out.println(
                    "Mensaje recibido : " + new String(rPacket.getData(), 0, rPacket.getLength()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
