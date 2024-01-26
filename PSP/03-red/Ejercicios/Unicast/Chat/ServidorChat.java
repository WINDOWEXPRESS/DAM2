package Unicast.Chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ServidorChat {
    private static final int MAX_BYTE = 66535;
    public static final int NUMERO_PARAMETRO = 1;
    private static final String OUT = "salir";
    private String ip;
    private int puerto;
    private String mensaje;
    private static Scanner scanner;

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

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Error falta parámetro: <PUERTO>");
        } else {
            try {
                final int PUERTO = Integer.parseInt(args[NUMERO_PARAMETRO - 1]);
                DatagramSocket socketRecibir = new DatagramSocket(PUERTO);
                DatagramPacket packetRecibir = null;
                byte[] dataRecibir = new byte[MAX_BYTE];

                DatagramSocket socketEnviar = new DatagramSocket();
                DatagramPacket packetEnviar = null;
                byte[] dataEnviar = new byte[MAX_BYTE];
                InetAddress ipAddress = null;

                Scanner sc = new Scanner(System.in);
                String mensaje = "";
                while (true) {
                    packetRecibir = new DatagramPacket(dataRecibir, dataRecibir.length);
                    socketRecibir.receive(packetRecibir);
                    System.out.println(
                            "Mensaje recibido : " + new String(packetRecibir.getData(), 0, packetRecibir.getLength()));

                    System.out.println("Mensaje para enviar: ");
                    mensaje = sc.nextLine();

                    ipAddress = InetAddress.getByName(packetRecibir.getAddress().getHostAddress());
                    dataEnviar = mensaje.getBytes();
                    packetEnviar = new DatagramPacket(dataEnviar, dataEnviar.length, ipAddress,
                            PUERTO);
                    socketEnviar.send(packetEnviar);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

    }

}
