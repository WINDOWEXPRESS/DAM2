import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        // Recibir mensaje por el hilo
        new Thread(new MulticastReceiver()).start();

        // instanciar para enviar mensaje
        MulticastPublisher multicastPublisher = new MulticastPublisher();

        try {
            // Esperar 2s para asegurar enviar mensaje despues de iniciar el hilo
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            // Envio mensaje
            multicastPublisher.multicast("Hello desde ordenador 4");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
