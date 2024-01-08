import java.util.concurrent.locks.Lock;

public class Ascensor implements AscensorInterface, Runnable {
    public static final Object Lock = new Object();
    final int TIEMPO_ESPERA = 100;
    final int UN_SEGUNDO = 1000;
    int id = 0;
    String ip = "";
    int puerto = 0;
    int planta = 0;
    char direccion = ' ';

    @Override
    public synchronized void subir() {
        direccion = 'U';
    }

    @Override
    public synchronized void bajar() {
        direccion = 'D';
    }

    @Override
    public synchronized String getPlanta() {
        if (planta == 0) {
            return "PB";
        }
        return String.valueOf(planta);
    }

    @Override
    public synchronized String toProtocolo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void run() {

        while (true) {
            if (direccion == ' ') {
                esperar();
            }
            try {
                for (int i = TIEMPO_ESPERA; i < UN_SEGUNDO; i += TIEMPO_ESPERA) {
                    System.out.printf("[%02d;%2s;%c]\t", id, getPlanta(), direccion);
                    Thread.sleep(TIEMPO_ESPERA);
                }
                if (direccion == 'U') {
                    planta += 1;
                } else {
                    planta -= 1;
                }
                System.out.println("DIN!!! Has llegado a la planta "+getPlanta());
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            esperar();
        }

    }

    @Override
    public void setConfig(int id, String ip, int puerto) {
        this.id = id;
        this.ip = ip;
        this.puerto = puerto;
    }

    // Método para esperar en la línea de salida
    public void esperar() {
        synchronized (Ascensor.Lock) {
            try {
                Ascensor.Lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
