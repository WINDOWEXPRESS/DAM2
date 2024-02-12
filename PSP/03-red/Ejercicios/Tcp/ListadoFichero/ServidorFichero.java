package Tcp.ListadoFichero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorFichero {
    private static final int PUERTO = 8888;
    // Ruta del directorio a leer
    private static final String RUTA = "03-red\\Ejercicios";
    // private static final String RUTA = "03-red\\Ejercicios\\EJERCICIO 4.txt";
    // private static final String RUTA = "/usr/games";

    public static void main(String[] args) throws IOException {

        ServerSocket servidorSocket;

        try {
            servidorSocket = new ServerSocket(PUERTO);
            while (true) {
                Socket socket = servidorSocket.accept();

                var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                // Crear un objeto File para el directorio
                File directorio = new File(RUTA);
                // Verificar si es un directorio válido
                if (directorio.isDirectory()) {
                    // Obtener la lista de archivos y directorios en el directorio
                    String[] archivos = directorio.list();

                    // Iterar sobre la lista e imprimir los nombres de los archivos
                    if (archivos != null) {
                        for (String archivo : archivos) {
                            System.out.println(archivo);
                            writer.write(archivo);
                            writer.newLine();
                            writer.flush();
                        }
                    }
                } else {
                    BufferedReader lector = new BufferedReader(new FileReader(RUTA));
                    String linea;

                    // Leer el archivo línea por línea
                    while ((linea = lector.readLine()) != null) {
                        System.out.println(linea); // Imprimir la línea en la consola
                        writer.write(linea + "\n");
                        writer.flush();
                    }

                    // System.out.println("No es un directorio válido.");
                }
                writer.close();
                socket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
