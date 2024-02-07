package Unicast.Chat;

public class Chat {
    public static void main(String[] args) {

        ClienteChat cliente = new ClienteChat("localhost", 8888);
        ServidorChat servidor = new ServidorChat(8888);
        while (true) {
            cliente.enviarUDP();
            servidor.recibirUDP();
        }
    }

}