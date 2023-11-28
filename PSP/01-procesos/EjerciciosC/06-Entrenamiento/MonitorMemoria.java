import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MonitorMemoria {
    protected final static int INTTERVALO = 5000;

    public static void main(String[] args) {
        String comando = "free -h"; // El comando que deseas ejecutar

        while (true) {
            Process proceso;
            // Ejecutar el comando
            try {
                proceso = Runtime.getRuntime().exec(comando);

                // Obtener el flujo de salida del proceso
                BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

                // Leer y mostrar la salida del comando línea por línea
                String linea;
                try {
                    while ((linea = reader.readLine()) != null) {
                        System.out.println(linea);
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Thread.sleep(INTTERVALO);
            } catch (IOException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println();
        }
    }
}
