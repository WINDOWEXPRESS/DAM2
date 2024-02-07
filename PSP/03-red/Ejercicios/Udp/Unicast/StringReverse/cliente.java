package Unicast.StringReverse;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class cliente {

    // Puerto que utiliza el servidor para enviar respuestas
    public static final int PUERTO = 8080;

    // Tamaño máximo de datos en bytes
    private static final int MAX_BYTE = 66535;

    // Marca para salir del bucle
    private static final String OUT = "salir";

    // Dirección IP del servidor (localhost en este caso)
    private static final String LOCAL_HOST = "localhost";

    public static void main(String[] args) {
        try {
            // Obtener la dirección IP del servidor
            InetAddress ipAddress = InetAddress.getByName(LOCAL_HOST);

            // Socket para enviar mensajes al servidor
            DatagramSocket enviarSocket = new DatagramSocket();
            byte[] datosParaEnviar = null;
            DatagramPacket enviarPaquete = null;

            // Socket para recibir respuestas del servidor
            DatagramSocket rSocket = new DatagramSocket(servidor.PUERTO_ENVIAR);
            byte[] rData = null;
            DatagramPacket rPacket = null;

            // Scanner para leer la entrada del usuario
            Scanner sc = new Scanner(System.in);
            String mensaje = "";

            do {
                // Solicitar al usuario que introduzca algo para invertir
                System.out.print("Introduce algo para invertir la palabra: ");
                mensaje = sc.nextLine();

                // Convertir el mensaje en bytes
                datosParaEnviar = new byte[MAX_BYTE];
                datosParaEnviar = mensaje.getBytes();

                // Enviar el mensaje al servidor
                enviarPaquete = new DatagramPacket(datosParaEnviar, datosParaEnviar.length, ipAddress,
                        servidor.PUERTO_ESCUCHAR);
                enviarSocket.send(enviarPaquete);

                // Recibir la respuesta del servidor
                rData = new byte[MAX_BYTE];
                rPacket = new DatagramPacket(rData, rData.length);
                rSocket.receive(rPacket);

                // Mostrar el mensaje invertido recibido del servidor
                System.out.println("Mensaje recibido al revés: " +
                        new String(rPacket.getData(), 0, rPacket.getLength()));

            } while (!mensaje.toLowerCase().equals(OUT)); // Salir del bucle si el usuario ingresa "salir"

            // Cerrar el socket de envío
            enviarSocket.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
