package Tcp.ListadoFichero;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClienteFichero {
    private static final String IP = "0.0.0.0";
    private static final int PUERTO = 8888;
    private static final String ARCHIVO = "PRUEBA.txt";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(IP, PUERTO);

            BufferedInputStream bufferIS = new BufferedInputStream(new DataInputStream(socket.getInputStream()));

            byte[] bytesLeidos = bufferIS.readAllBytes();

            /*
             * //Leer el txt recibido
             * System.out.println("Mensaje recibido desde servidor: \n");
             * while ((linea = br.readLine()) != null) {
             * System.out.println(linea);
             * }
             */

            // Escribir lo que ha recibido en un fichero txt
            BufferedOutputStream bufferOS = new BufferedOutputStream(new FileOutputStream(ARCHIVO));

            bufferOS.write(bytesLeidos);

            // Cerrar el socket y los lectores después de la comunicación
            bufferOS.close();
            bufferIS.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero");
        }
    }
}
