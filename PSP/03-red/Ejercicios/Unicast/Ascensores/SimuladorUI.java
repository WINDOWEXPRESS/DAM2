import java.util.Scanner;

public class SimuladorUI {
    public static final int SUBIR = 1;
    public static final int BAJAR = 2; 
    int opcion;
    Scanner datos;
    public SimuladorUI(){
        datos = new Scanner(System.in);
    }
    void pintarMenu(){
        System.out.println("1.Subir");
        System.out.println("2.Bajar");
        opcion = datos.nextInt();
    };
    int obtenerSeleccion(){
        return opcion;
    }
}
