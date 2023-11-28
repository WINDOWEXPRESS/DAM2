import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class direccionIp {
    public static void main(String[] args) {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        String comando;

        if (isWindows) {
            comando = "ipconfig";
            Process proceso;
            try {
                proceso = Runtime.getRuntime().exec(comando);
                // Obtener el flujo de salida del proceso
                BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

                // Leer y mostrar la salida del comando línea por línea
                String linea;
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            /*
             * comando = "ifconfig";
             * Process proceso;
             * try {
             * proceso = Runtime.getRuntime().exec(comando);
             * // Obtener el flujo de salida del proceso
             * BufferedReader reader = new BufferedReader(new
             * InputStreamReader(proceso.getInputStream()));
             * 
             * // Leer y mostrar la salida del comando línea por línea
             * String linea;
             * while ((linea = reader.readLine()) != null) {
             * System.out.println(linea);
             * }
             * } catch (IOException e) {
             * // TODO Auto-generated catch block
             * e.printStackTrace();
             * }
             */
            ProcessBuilder processBuilder = new ProcessBuilder("ifconfig");
            //Con el método inheritIO() podemos redireccionar todos los flujos de E/S del proceso hijo a la E/S estándar del proceso padre.
            processBuilder.inheritIO();
            Process process;
            try {
                process = processBuilder.start();
                int exitCode = process.waitFor();
            } catch (IOException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            

        }

    }
}
