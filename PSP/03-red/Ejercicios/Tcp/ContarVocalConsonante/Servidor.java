package Tcp.ContarVocalConsonante;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Servidor {
    private static final int PUERTO = 8888;
    private static int totalVocal = 0;
    private static int totalConsonante = 0;

    public static void main(String[] args) {
        ServerSocket servidorSocket;

        try {
            servidorSocket = new ServerSocket(PUERTO);
            while (true) {
                Socket socket = servidorSocket.accept();

                InputStream inputStream = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String cadena = bufferedReader.readLine().trim();

                for (int i = 0; i < cadena.length(); i++) {
                    if (cadena.charAt(i) == ' ') {
                        continue;
                    }
                    if (esVocal(cadena.charAt(i))) {
                        totalVocal++;
                        System.out.println(cadena.charAt(i) + " " + totalVocal);
                    } else {
                        totalConsonante++;
                        System.out.println(cadena.charAt(i) + " " + totalConsonante);
                    }
                }

                DataOutputStream enviar = new DataOutputStream(socket.getOutputStream());
                enviar.writeUTF(mensajeDevuelto());

                enviar.flush();

                bufferedReader.close();
                enviar.close();
                socket.close();
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean esVocal(char caracter) {
        String vocales = "aeiouAEIOU";
        return vocales.contains(Character.toString(caracter));
    }

    public static String mensajeDevuelto() {
        return "El texto que has enviado tiene " + totalVocal + " vocales y " + totalConsonante + " consonantes.";

    }
}
