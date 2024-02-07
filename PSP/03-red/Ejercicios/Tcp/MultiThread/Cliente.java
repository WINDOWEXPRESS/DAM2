package Tcp.MultiThread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    private static final String IP = "0.0.0.0";
    private static final int PUERTO = 8888;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try (Socket socket = new Socket(IP, PUERTO)) {
            while (true) {

                BufferedOutputStream bufferOS = new BufferedOutputStream(
                        new DataOutputStream(socket.getOutputStream()));
                BufferedInputStream bufferIS = new BufferedInputStream(new DataInputStream(socket.getInputStream()));

                //Leer mensaje de mensajeJuego de Servidor
                byte[] bytesLeidosJuego = bufferIS.readAllBytes();
                System.out.println(new String(bytesLeidosJuego, 0, bytesLeidosJuego.length));

                System.out.print("Introducir el numero:");
                int numeroIntroducido = sc.nextByte();

                bufferOS.write(numeroIntroducido);

                byte[] bytesLeidos = bufferIS.readAllBytes();
                bufferIS.read(bytesLeidos);

                // Cerrar el socket y los lectores después de la comunicación
                // bufferOS.close();
                // socket.close();

            }
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor");
            System.out.println(e);
        }
    }

}
