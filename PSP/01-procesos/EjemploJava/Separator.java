import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Separator {
    public static void main(String[] args) {
        String homeDirectory = System.getProperty("user.home");
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        String separator = File.separator;
        String pathSeparator = File.pathSeparator;
        String getProperty = System.getProperty("file.separator");

        if (isWindows) {
            try {
                Runtime.getRuntime().exec(String.format("cmd.exe /c dir %s", homeDirectory));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                Process proceso =  Runtime.getRuntime().exec("ls -l");
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
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("isWindows : " + isWindows);
        System.out.println("homeDirectory : " + homeDirectory);
        System.out.println("separator : " + separator);
        System.out.println("pathSeparator : " + pathSeparator);
        System.out.println("getProperty : " + getProperty);

    }
}