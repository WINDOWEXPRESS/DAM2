package Unicast.StringReverse;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class servidor {

    private static final int MAX_BYTE = 66535;

    // Puerto utilizado por el servidor para enviar respuestas
    public static final int PUERTO_ENVIAR = 8888;

    // Puerto utilizado por el cliente para enviar mensajes al servidor
    public static final int PUERTO_ESCUCHAR = 8080;

    // Dirección IP que representa cualquier IP
    public static final String CUALQUIER_IP = "0.0.0.0";

    public static void main(String[] args) {
        try {
            // Socket para recibir mensajes del cliente
            DatagramSocket rsocket = new DatagramSocket(PUERTO_ESCUCHAR);
            DatagramPacket rPacket = null;
            byte[] rData = new byte[MAX_BYTE];

            // Socket para enviar respuestas al cliente
            DatagramSocket sSocket = new DatagramSocket();
            DatagramPacket sPacket = null;
            byte[] sData = new byte[MAX_BYTE];
            InetAddress ipAddress = null;

            while (true) {
                // Recibir un mensaje del cliente
                rPacket = new DatagramPacket(rData, rData.length);
                rsocket.receive(rPacket);
                String mensaje = new String(rPacket.getData(), 0, rPacket.getLength());

                // Invertir el mensaje
                mensaje = mensajeAlReves(mensaje);

                // Obtener la dirección IP del cliente
                ipAddress = InetAddress.getByName(rPacket.getAddress().getHostAddress());

                // Enviar la respuesta al cliente
                sData = mensaje.getBytes();
                sPacket = new DatagramPacket(sData, sData.length, ipAddress, PUERTO_ENVIAR);
                sSocket.send(sPacket);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Método para invertir un mensaje
    private static String mensajeAlReves(String mensaje) {
        String nuevoMensaje = "";
        for (int i = mensaje.length() - 1; i >= 0; i--) {
            nuevoMensaje += mensaje.charAt(i);
        }
        return nuevoMensaje;
    }
}
