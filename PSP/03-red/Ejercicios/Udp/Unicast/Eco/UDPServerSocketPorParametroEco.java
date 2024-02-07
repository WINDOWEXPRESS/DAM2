package Unicast.Eco;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerSocketPorParametroEco {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error de parametros!");
        } else {
            final int SOCKET = Integer.parseInt(args[0]);
            try {
                DatagramSocket socketRecibir = new DatagramSocket(SOCKET); // Abre el socket en el puerto recibido desde
                                                                           // args
                byte[] receivedData = new byte[1024];
                while (true) {
                    DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                    socketRecibir.receive(receivedPacket); // Espera y recibe el paquete

                    // Extrae la información del paquete
                    String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                    System.out.println("Mensaje recibido: '" + message + "' con Ip de cliente: "
                            + receivedPacket.getAddress().getHostAddress());

                    // Esperar 3s para mostrar
                    for (int i = 0; i < 3; i++) {
                        System.out.print(".");
                        Thread.sleep(1000);
                    }
                    System.out.println();
                    // Una forma de mostrar array de byte
                    // byte[] sendData = new byte[1024];
                    // sendData = message.getBytes();
                    // for (int i = 0; i < sendData.length; i++) {
                    // System.out.print((char) sendData[i]);
                    // }

                    DatagramSocket socketEnviarCliente = new DatagramSocket();
                    InetAddress IPAddress = InetAddress.getByName(receivedPacket.getAddress().getHostAddress());
                    DatagramPacket enviarPaquete = new DatagramPacket(receivedData, receivedData.length, IPAddress,
                            1234);// 1234 como puerto por defecto
                    socketEnviarCliente.send(enviarPaquete); // Envía el paquete al servidor
                    socketEnviarCliente.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}