package Tcp.MultiThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PUERTO = 8888;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO); // Escuchar puerto especifico
            while (true) {
                System.out.println("Esperando conexion con el cliente...");
                Socket socket = serverSocket.accept();
                System.out.println("Cliente " + socket.getRemoteSocketAddress() + " conectado.");

                ServidorManipulador manipulador = new ServidorManipulador(socket);
                Thread hilo = new Thread(manipulador);
                hilo.start();
                System.out.println("Cliente " + socket.getRemoteSocketAddress() + " desconectado.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
