package Tcp.EnvioPdf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    private static final String IP = "0.0.0.0";
    private static final int PUERTO = 8888;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(IP, PUERTO);

            BufferedInputStream lector = new BufferedInputStream(new DataInputStream(socket.getInputStream()));
            byte[] bytesLeidos = lector.readAllBytes();

            BufferedOutputStream buffer = new BufferedOutputStream(new FileOutputStream("prueba.pdf"));

            buffer.write(bytesLeidos);

            // Cerrar el socket y los lectores después de la comunicación
            buffer.close();
            lector.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
