package Tcp.EnvioPdf;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEnvia {
    private static final int PUERTO = 8888;
    private static final String RUTA = "Comandos Linux.pdf";
    private static final int TAMANIO_BUFFER = 1024;

    public static void main(String[] args) {
        try (ServerSocket servidorSocket = new ServerSocket(PUERTO)) {
            System.out.println("Esperando conexiones en el puerto " + PUERTO + "...");
            while (true) {
                Socket socket = servidorSocket.accept();
                System.out.println("Cliente conectado desde " + socket.getInetAddress());

                try (BufferedInputStream lector = new BufferedInputStream(new FileInputStream(RUTA));
                        DataOutputStream enviar = new DataOutputStream(socket.getOutputStream())) {

                    byte[] buffer = new byte[TAMANIO_BUFFER];
                    int bytesRead;
                    while ((bytesRead = lector.read(buffer)) != -1) {
                        enviar.write(buffer, 0, bytesRead);
                    }

                    System.out.println("Archivo enviado con éxito.");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
