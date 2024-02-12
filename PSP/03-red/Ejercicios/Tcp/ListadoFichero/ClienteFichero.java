package Tcp.ListadoFichero;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClienteFichero {
    private static final String IP = "0.0.0.0";
    private static final int PUERTO = 8888;
    private static final String RUTA_ARCHIVO = "03-red/Ejercicios/Tcp/ListadoFichero/PRUEBA.txt";
    private static final int TAMANIO = 1024;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(IP, PUERTO);

            BufferedInputStream bufferIS = new BufferedInputStream(socket.getInputStream());

            // byte[] bytesLeidos = bufferIS.readAllBytes();
            byte[] bytesLeidos = new byte[TAMANIO];
            int bytesRead;
            StringBuilder contenido = new StringBuilder();
            while ((bytesRead = bufferIS.read(bytesLeidos)) != -1) {

                contenido.append(new String(bytesLeidos, 0, bytesRead, StandardCharsets.UTF_8));
            }
            // Escribir contenido en un archivo
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(RUTA_ARCHIVO), StandardCharsets.UTF_8))) {
                writer.write(contenido.toString());
            }

            // Cerrar el socket y el BufferedInputStream después de la comunicación
            bufferIS.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero");
        }
    }
}
