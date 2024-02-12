package Tcp.EnvioPdf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final String IP = "0.0.0.0";
    private static final int PUERTO = 8888;
    private static final String RUTA = "03-red\\Ejercicios\\Tcp\\EnvioPdf\\prueba.pdf";
    private static final int TAMANIO_BUFFER = 1024;

    /*
     * public static void main(String[] args) {
     * try {
     * Socket socket = new Socket(IP, PUERTO);
     * 
     * BufferedInputStream lector = new BufferedInputStream(new
     * DataInputStream(socket.getInputStream()));
     * byte[] bytesLeidos = lector.readAllBytes();
     * 
     * BufferedOutputStream buffer = new BufferedOutputStream(new
     * FileOutputStream(RUTA));
     * 
     * buffer.write(bytesLeidos);
     * 
     * // Cerrar el socket y los lectores después de la comunicación
     * buffer.close();
     * lector.close();
     * socket.close();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     */
    public static void main(String[] args) {
        try (Socket socket = new Socket(IP, PUERTO);) {
            System.out.println("Pulsa ENTER para recibir PDF desde servidor");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
            try (BufferedInputStream lector = new BufferedInputStream(socket.getInputStream());
                    DataOutputStream enviar = new DataOutputStream(new FileOutputStream(RUTA))) {

                byte[] buffer = new byte[TAMANIO_BUFFER];
                int bytesRead;
                while ((bytesRead = lector.read(buffer)) != -1) {
                    enviar.write(buffer, 0, bytesRead);
                }
                System.out.println("PDF recibido y guardado como " + RUTA);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
