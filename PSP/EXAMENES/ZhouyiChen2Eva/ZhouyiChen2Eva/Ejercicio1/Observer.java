package Ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Observer {
    // Interfaz para los observadores del receptor
    public interface Obs {
        // Método de actualización que será implementado por los observadores
        public void update(String mensaje);
    }

    private List<Obs> observers; // Lista de observadores registrados en el receptor

    // Constructor
    public Observer() {

        observers = new ArrayList<>(); // Inicializa la lista de observadores
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
}
