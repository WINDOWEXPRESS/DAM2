package Tcp.MultiThread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String IP = "0.0.0.0";
    private static final int PUERTO = 8888;
    private static final int TAMANIO = 1024;
    private static final int NUMERO_INTENTO = 5;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (Socket socket = new Socket(IP, PUERTO)) {
            // puede hacerlo sin envolver new dataoutputstream
            BufferedOutputStream bufferOS = new BufferedOutputStream(
                    new DataOutputStream(socket.getOutputStream()));
            BufferedInputStream bufferIS = new BufferedInputStream(new DataInputStream(socket.getInputStream()));

            // Leer mensaje de mensajeJuego de Servidor
            byte[] mensajeBytes = new byte[TAMANIO];
            int bytesLeidos = bufferIS.read(mensajeBytes);
            if (bytesLeidos != -1) {
                String mensaje = new String(mensajeBytes, 0, bytesLeidos);
                System.out.println("Mensaje del servidor: " + mensaje);
            } else {
                System.out.println("Conexión cerrada por el servidor.");

            }
            for (int i = 0; i < NUMERO_INTENTO; i++) {
                System.out.println();
                // Leer entrada del usuario
                System.out.print("Introduce el número: ");
                int numeroIntroducido = sc.nextInt();

                // Enviar número al servidor
                bufferOS.write(numeroIntroducido);
                bufferOS.flush(); // Asegurar que todos los datos se envíen
                // Leer respuesta del servidor
                byte[] respuestaBytes = new byte[TAMANIO];
                bytesLeidos = bufferIS.read(respuestaBytes);
                if (bytesLeidos != -1) {
                    String respuesta = new String(respuestaBytes, 0, bytesLeidos);
                    System.out.println("Respuesta del servidor: \n\t" + respuesta);
                } else {
                    System.out.println("Conexión cerrada por el servidor.");
                    break;
                }

            }

            // Cerrar el socket y los lectores después de la comunicación
            bufferOS.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor");
            System.out.println(e);
        }
    }

}
