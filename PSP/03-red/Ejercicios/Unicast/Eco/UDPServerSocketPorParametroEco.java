import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServerSocketPorParametroEco {
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Error de parametros!");
        }else{
            final int SOCKET = Integer.parseInt(args[0]);
            try {
                DatagramSocket socket = new DatagramSocket(SOCKET); // Abre el socket en el puerto recibido desde args
                byte[] receivedData = new byte[1024];
                while(true) {
                    DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                    socket.receive(receivedPacket); // Espera y recibe el paquete

                    // Extrae la información del paquete
                    String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                    System.out.println("Mensaje recibido: " + message);
                    System.out.println("Ip de cliente: "+receivedPacket.getAddress().getHostAddress());

                    for (int i = 0; i < 4; i++) {
                        System.out.print(".");
                        Thread.sleep(1000);
                    }
                    //devolver el mensaje a cliente
                    byte[] sendData = new byte[1024];
                    sendData = message.getBytes();
                    for (int i = 0; i < sendData.length; i++) {
                        System.out.print((char)sendData[i]);
                    }
                    DatagramSocket socketEnviarCliente = new DatagramSocket();
                    InetAddress IPAddress = InetAddress.getByName(receivedPacket.getAddress().getHostAddress());
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 1234);
                    socketEnviarCliente.send(sendPacket); // Envía el paquete al servidor
                    socketEnviarCliente.close();
               }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}