import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Compresor{
    public static void main(String[] args) {
        
        if(args.length != 0){
            String separatorSO = File.separator;
            System.out.println("separatorSO : "+separatorSO);
            ArrayList<String> comandos = new ArrayList<>();
            comandos.add("java");
            comandos.add("GeneradorGZ");


            for (int i = 0; i < args.length; i++) {
                String ruta = args[i].replace("\\", separatorSO);
                comandos.add(ruta) ;

                ProcessBuilder pb = new ProcessBuilder(comandos);
                try {
                    Process p = pb.start();
                    
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String cadena ;

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
            }
            comandos.forEach((x) ->
                System.out.println(x)
            );
            
        }

        
    }

}