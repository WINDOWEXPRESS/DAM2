package Ejercicio2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceptorUDP {
    private int puerto;
    private static final int MAX_LENGTH = 65535;
    private static final String RUTA_LOG = "bancos.txt";
    private List<Obs> observers; // Lista de observadores registrados en el receptor

    public ReceptorUDP(int puerto) {
        this.puerto = puerto;
        observers = new ArrayList<>(); // Inicializa la lista de observadores
    }

    // Interfaz para los observadores del receptor
    public interface Obs {
        // Método de actualización que será implementado por los observadores
        public void update(String mensaje);
    }

    // Método para registrar un observador en el receptor
    public void registerObserver(Obs observer) {
        observers.add(observer);
    }

    // Método para remover un observador de la lista de observadores
    public void removeObserver(Obs observer) {
        observers.remove(observer);
    }

    // Método privado para notificar a todos los observadores registrados
    public void notifyObservers(String data) {
        // Itera sobre la lista de observadores y llama al método update para cada uno
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(data);
        }
    }

    public void escuchar() {
        try {
            try (DatagramSocket ds = new DatagramSocket(puerto, InetAddress.getByName("0.0.0.0"))) {
                byte[] buffer = new byte[MAX_LENGTH];
                while (true) {
                    DatagramPacket p = new DatagramPacket(
                            buffer,
                            MAX_LENGTH);

                    ds.receive(p);
                    String notificaciones = new String(p.getData(), 0, p.getLength());
                    verCadaNotificacion(notificaciones);
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void verCadaNotificacion(String notificaciones) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTA_LOG, true))) {
            for (int i = 0; i < notificaciones.length(); i++) {
                if (notificaciones.charAt(i) == 'O' || notificaciones.charAt(i) == 'B') {
                    notifyObservers("!Alarma!");
                    if (notificaciones.charAt(i) == 'B') {

                        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

                        writer.write(timeStamp + "\n");

                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            // Manejar cualquier excepción de E/S al escribir en el archivo
            e.printStackTrace(); // Imprimir el seguimiento de la pila para depuración
            // Aquí podrías manejar la excepción de manera más específica o notificar al
            // sistema de algún otro modo
        }

    }
}
