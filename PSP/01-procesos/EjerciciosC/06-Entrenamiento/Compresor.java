import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Compresor {

    public static void main(String[] args) {

        // Verificar si se proporcionan argumentos
        if (args.length == 1) {

            // Obtener el separador de ruta del sistema operativo
            String separatorSO = File.separator;

            // Imprimir el separador de ruta del sistema operativo
            System.out.println("separatorSO : " + separatorSO);

            // Crear una lista para almacenar los comandos
            ArrayList<String> comandos = new ArrayList<>();

            // Agregar el comando principal y el nombre del programa "GeneradorGZ"
            comandos.add("java");
            comandos.add("GeneradorGZ");

            // Recorrer los argumentos proporcionados
            for (int i = 0; i < args.length; i++) {

                // Reemplazar las barras invertidas en las rutas por el separador del sistema operativo
                String ruta = args[i].replace("\\", separatorSO);

                // Agregar la ruta a la lista de comandos
                comandos.add(ruta);

                // Crear un constructor de procesos con los comandos
                ProcessBuilder pb = new ProcessBuilder(comandos);

                try {
                    // Iniciar el proceso
                    Process p = pb.start();

                    // Leer la salida del proceso
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

                    // Hacer algo con la salida del proceso si es necesario
                    System.out.println("Operacion exitosa!");
                } catch (IOException e) {
                    // Manejar cualquier excepción de entrada/salida
                    e.printStackTrace();
                }
            }

            // Imprimir los comandos para verificar
            comandos.forEach((x) ->
                System.out.println(x)
            );
        }else{
            System.out.println("Sin parametro o hay varios por comando! : fichero <ruta>");
        }
    }
}
