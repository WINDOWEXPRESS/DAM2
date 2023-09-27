import java.io.File;
import java.util.Scanner;

public class ej1_1 {
    public static void main(String[] args) {
        Scanner dato = new Scanner(System.in);
        System.out.println("Quieres ver archivos en el directorio actual?\n1.Si\n2.No");

        int op = dato.nextInt();

        if (op == 1) {
            mostrarArchivos(".");//"." para mostrar directorio actual
        }else{
            //Pedir la ruta
            dato.nextLine();
            System.out.print("Introducir la ruta absoluta:");
            String rutaAbsoluta = dato.nextLine();
            //visualizar
            mostrarArchivos(rutaAbsoluta);
        }


    }
    public static void mostrarArchivos(String url){
        File fichero = new File(url);
        File[] archivos = fichero.listFiles();
        for (int i = 0; i < archivos.length; i++) {
            System.out.println(i+". "+archivos[i].getName()+"\n");
        }
    }
}
