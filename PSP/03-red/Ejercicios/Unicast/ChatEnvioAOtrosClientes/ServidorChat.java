package Unicast.ChatEnvioAOtrosClientes;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashSet;
import java.util.Set;


public class ServidorChat {
    private static final int MAX_BYTE = 66535;

    private DatagramSocket serverSocket;
    private DatagramPacket paqRecibido;
    private DatagramPacket paqEnviado;
    private String mensaje;
    private Set<ClienteInfo> listaClientes;

    public ServidorChat(int puerto) {
        try {
            serverSocket = new DatagramSocket(puerto);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        listaClientes  = new HashSet<>();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void enviarUDP() {
        InetAddress ipOriginal =  paqRecibido.getAddress();
        int PuertoOriginal =  paqRecibido.getPort();
        for (ClienteInfo cliente : listaClientes) {
            if (ipOriginal.equals(cliente.getDireccion()) &&  PuertoOriginal== cliente.getPuerto())
                 continue;
            try {
                byte[] datos = mensaje.getBytes();
                paqEnviado = new DatagramPacket(datos, datos.length, cliente.getDireccion(), cliente.getPuerto());
                serverSocket.send(paqEnviado);
            } catch (IOException e) {
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
            mensaje = new String(paqRecibido.getData(), 0, paqRecibido.getLength());
            // comprobacion de conexion
            System.out.println("\tOrigen: " + paqRecibido.getAddress() + ":" + paqRecibido.getPort());
            System.out.println(
                    "\tMensaje recibido : " + mensaje);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean aniadirYBorrarCliente(){
            // Manejar conexión y desconexión
            if (mensaje.equals(ClienteChat.CONEXION)) {
                ClienteInfo nuevoCliente = new ClienteInfo(paqRecibido.getAddress(), paqRecibido.getPort());
                listaClientes.add(nuevoCliente);
                System.out.println("\tCliente añadido.");
                return true;
            } else if (mensaje.equals(ClienteChat.DESCONEXION)) {
                ClienteInfo clienteDesconectado = buscarCliente(paqRecibido.getAddress(), paqRecibido.getPort());
                if (clienteDesconectado != null) {
                    listaClientes.remove(clienteDesconectado);
                    System.out.println("\tCliente eliminado.");
                    return true;
                }
            }
            return false;
    }

    private ClienteInfo buscarCliente(java.net.InetAddress direccion, int puerto) {
        for (ClienteInfo cliente : listaClientes) {
            if (cliente.getDireccion().equals(direccion) && cliente.getPuerto() == puerto) {
                return cliente;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        ServidorChat servidorChat = new ServidorChat(8888);
        while (true) {

            servidorChat.recibirUDP();
            if(servidorChat.aniadirYBorrarCliente()){
                continue;
            }
            servidorChat.enviarUDP();

        }
    }

}

class ClienteInfo {
    private java.net.InetAddress direccion;
    private int puerto;

    public ClienteInfo(java.net.InetAddress direccion, int puerto) {
        this.direccion = direccion;
        this.puerto = puerto;
    }

    public java.net.InetAddress getDireccion() {
        return direccion;
    }

    public int getPuerto() {
        return puerto;
    }
}