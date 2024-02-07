package Unicast.ChatEnvioAOtrosClientes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteChat implements Runnable {
    // Ocupa cliente este puerto para enviar
    private static final int MAX_BYTE = 66535;
    public static final String CONEXION = "inicia conexion";
    public static final String DESCONEXION = "fin conexion";
    private InetAddress ipEnviar;
    private int puerto;
    private String mensaje;
    private DatagramSocket clientSocket;
    private DatagramPacket enviarPaquete;
    private DatagramPacket recibirPaquete;
    private byte[] datosParaEnviar;

    public ClienteChat(String ip, int puerto) {
        try {
            this.ipEnviar = InetAddress.getByName(ip);
            this.clientSocket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.puerto = puerto;
        this.mensaje = "";
    }

    public String getMensaje() {
        return mensaje;
    }

    public void enviarUDP() {
        try {
            // INTRODUCIR DATOS POR TECLADO
            System.out.print("Mensaje para enviar: ");
            Scanner scanner = new Scanner(System.in);
            mensaje = scanner.nextLine();
            datosParaEnviar = mensaje.getBytes();

            // ENVIANDO DATAGRAMA AL SERVIDOR
            enviarPaquete = new DatagramPacket(datosParaEnviar, datosParaEnviar.length, ipEnviar,
                    puerto);
            clientSocket.send(enviarPaquete);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void recibirUDP() {
        try {
            byte[] datosRecibidos = new byte[MAX_BYTE];
            recibirPaquete = new DatagramPacket(datosRecibidos, datosRecibidos.length);
            clientSocket.receive(recibirPaquete);
            mensaje = new String(recibirPaquete.getData(), 0, recibirPaquete.getLength());

            //Imprimir el mensaje 
            System.out.println(
                    "\tMensaje recibido : " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void conexion() {
        try {
            datosParaEnviar = CONEXION.getBytes();

            // ENVIANDO DATAGRAMA AL SERVIDOR para conectar
            enviarPaquete = new DatagramPacket(datosParaEnviar, datosParaEnviar.length, ipEnviar,
                    puerto);
            clientSocket.send(enviarPaquete);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void desconexion() {
        try {
            byte[] datos = DESCONEXION.getBytes();
            enviarPaquete = new DatagramPacket(datos, datos.length, ipEnviar, puerto);
            clientSocket.send(enviarPaquete);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClienteChat clienteChat = new ClienteChat("localhost", 8888);
        clienteChat.conexion();
        System.out.println("\tHas entrado a chat.");

        Thread hiloRecibir = new Thread(clienteChat);
        hiloRecibir.start();
        try {
            while (!(clienteChat.getMensaje().equalsIgnoreCase("salir"))) {

                clienteChat.enviarUDP();

            }
        } finally {
            // Cerrar el Scanner al salir
            System.out.println("\tHas salido del chat.");
            clienteChat.desconexion();
            // Cerrar el socket al salir
            if (clienteChat.clientSocket != null && !clienteChat.clientSocket.isClosed()) {
                clienteChat.clientSocket.close();
            }
        }
    }

    @Override
    public void run() {
        recibirUDP();
    }
}
