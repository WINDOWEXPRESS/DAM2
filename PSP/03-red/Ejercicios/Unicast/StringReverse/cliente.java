package Unicast.StringReverse;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class cliente {
    // Ocupa cliente este puerto para enviar
    public static final int PUERTO = 8080;
    private static final int MAX_BYTE = 66535;
    private static final String OUT = "salir";
    private static final String LOCAL_HOST = "localhost";

    public static void main(String[] args) {
        try {

            InetAddress ipAddress = InetAddress.getByName(LOCAL_HOST);
            DatagramSocket enviarSocket = new DatagramSocket();
            byte[] datosParaEnviar = null;
            DatagramPacket enviarPaquete = null;

            DatagramSocket rSocket = new DatagramSocket(servidor.PUERTO_ENVIAR);
            byte[] rData = null;
            DatagramPacket rPacket = null;

            Scanner sc = new Scanner(System.in);
            String mensaje = "";

            do {
                System.out.print("Introduce algo para hacer reverse de palabra: ");
                mensaje = sc.nextLine();
                datosParaEnviar = new byte[MAX_BYTE];
                datosParaEnviar = mensaje.getBytes();
                enviarPaquete = new DatagramPacket(datosParaEnviar, datosParaEnviar.length, ipAddress,
                        servidor.PUERTO_ESCUCHAR);
                enviarSocket.send(enviarPaquete);

                rData = new byte[MAX_BYTE];
                rPacket = new DatagramPacket(rData, rData.length);
                rSocket.receive(rPacket);
                System.out.println(
                        "mensaje recibido al revés es : " + new String(rPacket.getData(), 0, rPacket.getLength()));
            } while (!mensaje.toLowerCase().equals(OUT));
            enviarSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
