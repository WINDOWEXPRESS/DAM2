package Tcp.VerFichero;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClienteFichero {
    private static final String IP = "0.0.0.0";
    private static final int PUERTO = 8888;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(IP, PUERTO);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            String linea;
            linea = br.readLine();
            System.out.println("Mensaje del servidor: \n" + linea);

            // Cerrar el socket y los lectores después de la comunicación
            br.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
