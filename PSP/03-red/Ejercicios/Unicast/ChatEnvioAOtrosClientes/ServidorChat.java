package Unicast.ChatEnvioAOtrosClientes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServidorChat {
    private static final int MAX_BYTE = 66535;

    private DatagramSocket serverSocket;
    private DatagramPacket paqRecibido;
    private InetAddress ipOrigen;
    private DatagramPacket paqEnviado;
    private int puerto;
    private String mensaje;

    private List<DatagramPacket> listaClientes;

    public ServidorChat(int puerto) {
        try {
            serverSocket = new DatagramSocket(puerto);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        listaClientes = new ArrayList<>();
        mensaje = "inicia el mensaje";
    }

    public String getMensaje() {
        return mensaje;
    }

    public void enviarUDP() {
        for (DatagramPacket datagramPacket : listaClientes) {
            if (mensaje == new String(datagramPacket.getData(), 0, paqRecibido.getLength()))
                continue;

            try {
                System.out.println("Mensaje para enviar a cliente: " + datagramPacket.getAddress());

                byte[] datos = mensaje.getBytes();

                paqEnviado = new DatagramPacket(datos, datos.length, datagramPacket.getAddress(),
                        datagramPacket.getPort());
                serverSocket.send(paqEnviado);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void recibirUDP() {
        try {
            System.out.println(
                    "Esperando mensaje... ");
            byte[] datosRecibidos = new byte[MAX_BYTE];
            // RECIBO DATAGRAMA
            paqRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);
            serverSocket.receive(paqRecibido);

            // DIRECCION ORIGEN
            ipOrigen = paqRecibido.getAddress();
            puerto = paqRecibido.getPort();
            mensaje = new String(paqRecibido.getData(), 0, paqRecibido.getLength());
            if (mensaje.equals("inicia conexion")) {
                aniadirCliente();
            } else {
                System.out.println("\tOrigen: " + ipOrigen + ":" + puerto);
                System.out.println(
                        "\tMensaje recibido : " + mensaje);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void aniadirCliente() {
        listaClientes.add(paqEnviado);
    }

    public void borrarCliente() {
        listaClientes.remove(paqEnviado);
    }

    public static void main(String[] args) {
        ServidorChat servidorChat = new ServidorChat(8888);
        while (true) {

            servidorChat.recibirUDP();

            servidorChat.enviarUDP();

        }
    }

}
