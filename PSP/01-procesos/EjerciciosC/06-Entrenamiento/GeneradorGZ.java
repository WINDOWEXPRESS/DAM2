import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;


public class GeneradorGZ {
    public static void main(String[] args) {
        final String EXTENSION = ".tar.gz";
        if(args.length != 0){
            ArrayList<String> comandos = new ArrayList<>();
            comandos.add("tar");
            comandos.add("-cvzf");
            
            LocalDate fecha = LocalDate.now();
            //Ejemplo hola_mundo_2021_03_21.tar.gz
            String nombreTA_GZ = args[args.length-1].replace("/", "_")
                +String.format("_%d_%d_%d_%s", fecha.getYear(),fecha.getMonthValue(),fecha.getDayOfMonth(),EXTENSION);
            comandos.add(nombreTA_GZ);
            comandos.add(args[0]);

            ProcessBuilder pb = new ProcessBuilder(comandos);
            try {
                Process p = pb.start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            comandos.forEach((x)->
                System.out.println(x)
            );
            
        }else{
            System.out.println("No hay ARGS");
        }
        
    }
}
