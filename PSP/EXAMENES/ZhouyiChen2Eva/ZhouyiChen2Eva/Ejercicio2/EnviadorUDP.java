package Ejercicio2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EnviadorUDP {
    private int puerto;
    DatagramSocket ds;

    public EnviadorUDP(int puerto) {
        this.puerto = puerto;
        try {
            ds = new DatagramSocket();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void envio(String mensaje) {
        try {
            // System.out.println(mensaje + 1);

            byte buffer[] = mensaje.getBytes();
            String ip = "255.255.255.255";
            ds.setBroadcast(true);
            DatagramPacket p = new DatagramPacket(
                    buffer,
                    buffer.length,
                    InetAddress.getByName(ip),
                    puerto);

            ds.send(p);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
