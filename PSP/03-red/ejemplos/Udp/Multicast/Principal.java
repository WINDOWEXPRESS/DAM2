import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        // Recibir mensaje
        new Thread(new MulticastReceiver()).start();
        MulticastPublisher multicastPublisher = new MulticastPublisher();

        // Envio mensaje
        // try {
        // Thread.sleep(2000);
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // try {
        // multicastPublisher.multicast("Hello desde ordenador 4");
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }
}
