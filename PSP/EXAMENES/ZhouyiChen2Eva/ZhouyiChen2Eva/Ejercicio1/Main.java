package Ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import Ejercicio1.Observer.Obs;

public class Main {
    private static final int NUMERO_TOTAL_PARAMETROS = 1;
    private static final int RESOURCE_POSITION = 1;
    private static final int CAPITAL_POSITION = 2;
    static Map<String, Boolean> ciudades = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // Verificar si se proporciona el número correcto de argumentos en la línea de
        // comandos

        if (args.length != NUMERO_TOTAL_PARAMETROS) {
            System.out.println("Uso: java Main <puerto>");
            return; // Salir del programa si el número de argumentos es incorrecto
        }

        try {
            int puerto = Integer.parseInt(args[0]);
            ServerSocket server = new ServerSocket(puerto);
            addCapitales();

            Observer observer = new Observer();
            while (true) {

                observer.registerObserver(new Obs() {

                    @Override
                    public void update(String mensaje) {
                        System.out.println("Ha sido consultada una capital pacificada:" + mensaje);
                    }

                });

                Socket connCliente = server.accept();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                connCliente.getInputStream()));
                String header = reader.readLine();
                System.out.println(header);
                // Supongamos que el /consulta es bien escrito

                // obtener ciudad de url
                String ciudad = extraeInformacion(header).split("/")[CAPITAL_POSITION];

                // String html = generaPagina(info);

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(
                                connCliente.getOutputStream()));

                if (!encontrarCiudad(ciudad)) {
                    writer.write("HTTP/1.1 404 No encontrado\n");
                    System.out.println("HTTP/1.1 404 No encontrado");
                } else {
                    if (esPacificada(ciudad)) {
                        writer.write("HTTP/1.1 200 Pacificada\n");
                        System.out.println("HTTP/1.1 4 200 Pacificada");
                        if (esCapital(ciudad)) {
                            observer.notifyObservers(ciudad);
                        }

                    } else {
                        writer.write("HTTP/1.1 200 Salvaje\n");
                        System.out.println("HTTP/1.1 200 Salvaje");

                    }

                }
                // writer.write("Content-Type: application/json; charset=utf-8\n");
                // SALTO DE LINEA PARA IR A BODY
                writer.write("\n");
                // writer.write(html);
                writer.flush();

                reader.close();
                writer.close();
                connCliente.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private static String extraeInformacion(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }

    private static void addCapitales() {
        ciudades.put("MADRID", true); // Capital de España
        ciudades.put("Barcelona", false); // No es capital
        ciudades.put("LISBOA", true); // Capital de Portugal
        ciudades.put("PARIS", true); // Capital de Francia
        ciudades.put("BEIJING", false); // Capital de China
        ciudades.put("Sidney", false); // No es capital
        ciudades.put("BUENOSAIRES", true); // Capital de Argentina
        ciudades.put("Valencia", false); // No es capital
        ciudades.put("Malaga", false); // No es capital
        ciudades.put("Osaka", false); // No es capital
        ciudades.put("Liverpool", false); // No es capital
        ciudades.put("Marsella", true); // No es capital
        ciudades.put("Florencia", false); // No es capital
        ciudades.put("SanFrancisco", false); // No es capital
        ciudades.put("Melbourne", false); // No es capital
        ciudades.put("LasVegas", false); // No es capital
        ciudades.put("Boston", false); // No es capital
    }

    private static boolean esCapital(String ciudad) {
        if (ciudades.containsKey(ciudad.toUpperCase())) {
            return true;
        }
        return false;

    }

    private static boolean esPacificada(String ciudad) {
        if (esCapital(ciudad)) {
            return ciudades.get(ciudad.toUpperCase());
        } else {
            return ciudades.get(ciudad);
        }

    }

    private static boolean encontrarCiudad(String ciudad) {
        if (ciudades.containsKey(ciudad.toUpperCase()) || ciudades.containsKey(ciudad)) {
            return true;
        }
        return false;

    }
}