package Tcp.ListadoFichero;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import javax.sound.sampled.Line;

public class ServerComando {
    public static void main(String[] args) throws InterruptedException {
        ServerSocket server;
        try {
            server = new ServerSocket(1234);
            while (true) {
                // Espera cliente
                System.out.println("Servidor preparado.");
                Socket socket = server.accept();
                System.out.println("Me ha llegado un cliente, voy a mandarle el mensaje");
                // "/c" indica que se debe cerrar la ventana de comandos después de ejecutar
                // si no indica no funciona correctamente el codigo
                ProcessBuilder builder = new ProcessBuilder("cmd", "/c", "dir",
                        "D:\\DAM2\\PSP\\03-red\\Ejercicios");
                // ProcessBuilder builder = new ProcessBuilder("dir", "C:\\");
                Process process = builder.start();
                String msg = "";

                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        msg += line + "\n"; // hace falta el salto de línea ya que el BufferedReader lo consume
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                // Esperar a que el proceso termine
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    System.out.println("Proceso completado exitosamente.");
                } else {
                    System.out.println("El proceso finalizó con un código de salida " + exitCode);
                }
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                out.writeUTF(msg);
                System.out.println("Mensaje enviado a cliente.");

                socket.close();
                out.close();
                System.out.println("Mensaje enviado, lo leerá cuando el quiera");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
