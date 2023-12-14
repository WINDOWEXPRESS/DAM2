package ejer3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        String cadena;
        String md5;
        Scanner sc = new Scanner(System.in);

        System.out.print("Introducir cadena : ");
        cadena = sc.next();
        sc.nextLine();
        System.out.print("Introducir md5 : ");
        md5 = sc.next();

        String[] comando = { "./ejer3/md5sum", cadena };
        Process proceso;
        try {
            proceso = Runtime.getRuntime().exec(comando);

            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

            // Leer y mostrar la salida del comando línea por línea
            String md5Generado;
            while ((md5Generado = reader.readLine()) != null) {
                System.out.println("Cadena : " + cadena + "\nmd5 recibido: " + md5 + "\nmd5 generado: " + md5Generado);
                if (md5Generado.equals(md5)) {
                    System.out.println("Los dos MD son iguales");
                } else {
                    System.out.println("Los dos MD no son iguales");
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
