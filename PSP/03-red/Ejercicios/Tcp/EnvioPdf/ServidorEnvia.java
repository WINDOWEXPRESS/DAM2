package Tcp.EnvioPdf;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEnvia {
    private static final int PUERTO = 8888;
    private static final String RUTA = "Comandos Linux.pdf";
    // private static final String RUTA = "/usr/games";

    public static void main(String[] args) throws IOException {

        ServerSocket servidorSocket;

        try {
            servidorSocket = new ServerSocket(PUERTO);
            while (true) {
                Socket socket = servidorSocket.accept();

                BufferedInputStream lector = new BufferedInputStream(new FileInputStream(RUTA));
                byte[] bytesLeidos =  lector.readAllBytes();

                DataOutputStream enviar = new DataOutputStream(socket.getOutputStream());
                // Leer el archivo línea por línea
               
                    System.out.println(bytesLeidos); // Imprimir la línea en la consola
                    enviar.write(bytesLeidos);
                    enviar.flush();
                

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
