package Tcp.ContarVocalConsonante;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Cliente {
    private static final String IP = "0.0.0.0";
    private static final int PUERTO = 8888;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(IP, PUERTO);
            // Socket socket = new Socket("192.168.20.10", 1234);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Hola mundo de los sockets!!!");
            out.flush();
            // es lo mismo que out.close()
            socket.shutdownOutput();

            DataInputStream in = new DataInputStream(socket.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String s = br.readLine();
            System.out.println("El mensaje es: " + s);
            // out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
