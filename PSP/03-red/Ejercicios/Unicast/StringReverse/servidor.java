package Unicast.StringReverse;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class servidor {
    private static final int MAX_BYTE = 66535;

    // Puerto ocupado servidor para enviar
    public static final int PUERTO_ENVIAR = 8888;
    // Ocupa cliente este puerto para enviar
    public static final int PUERTO_ESCUCHAR = 8080;
    public static final String CUALQUIER_IP = "0.0.0.0";

    public static void main(String[] args) {
        try {
            DatagramSocket rsocket = new DatagramSocket(PUERTO_ESCUCHAR);
            DatagramPacket rPacket = null;
            byte[] rData = new byte[MAX_BYTE];

            DatagramSocket sSocket = new DatagramSocket();
            DatagramPacket sPacket = null;
            byte[] sData = new byte[MAX_BYTE];
            InetAddress ipAddress = null;
            while (true) {
                rPacket = new DatagramPacket(rData, rData.length);
                rsocket.receive(rPacket);
                String mensaje = new String(rPacket.getData(), 0, rPacket.getLength());

                mensaje = mensajeAlReves(mensaje);
                ipAddress = InetAddress.getByName(rPacket.getAddress().getHostAddress());
                sData = mensaje.getBytes();
                sPacket = new DatagramPacket(sData, sData.length, ipAddress,
                        PUERTO_ENVIAR);
                sSocket.send(sPacket);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static String mensajeAlReves(String mensaje) {
        String nuevoMensaje = "";
        for (int i = mensaje.length() - 1; i >= 0; i--) {
            nuevoMensaje += mensaje.charAt(i);
        }
        return nuevoMensaje;
    }
}
