import java.io.*;
import java.util.Arrays;

public class ej1_1_2 {
    public static final String FILESEPARATOR = File.separator;

    public static void main(String[] args) {
        if (args.length!=1){
            System.out.println("Introducir una ruta de directorio por args(Comando).");

        }else{
            //obtener la ruta con separator de ordenador 
            String archivoRuta = "."+FILESEPARATOR+args[0];

            File f = new File(archivoRuta);
            
            if(f.isDirectory()){
                System.out.println("Directorio si existe.");
                //imprimer los archivos
                File[] listaArchivos = f.listFiles();
                for (File file : listaArchivos) {
                    System.out.println(file);
                }
            }else{
                System.out.println("Directorio no existe.");
            }
        }
       

    }
}
