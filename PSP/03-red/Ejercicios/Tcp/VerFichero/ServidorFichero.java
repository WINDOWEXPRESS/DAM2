package Tcp.VerFichero;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorFichero {
    private static final int PUERTO = 8888;
    private static final String RUTA = "03-red\\Ejercicios\\EJERCICIO 4.txt";
    // private static final String RUTA = "/usr/games";

    public static void main(String[] args) throws IOException {

        ServerSocket servidorSocket;

        try {
            servidorSocket = new ServerSocket(PUERTO);
            while (true) {
                Socket socket = servidorSocket.accept();

                BufferedReader lector = new BufferedReader(new FileReader(RUTA));
                String linea;

                DataOutputStream enviar = new DataOutputStream(socket.getOutputStream());
                // Leer el archivo línea por línea
                while ((linea = lector.readLine()) != null) {
                    System.out.println(linea); // Imprimir la línea en la consola
                    enviar.writeUTF(linea);
                    enviar.flush();
                }

                lector.close();
                // sbufferedReader.close();
                enviar.close();
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
