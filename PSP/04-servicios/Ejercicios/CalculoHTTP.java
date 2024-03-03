
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/***
 * 
 * @author Jorge Dueñas Lerín
 *         Objetivo: entender cómo se programa un servicio usando de ejemplo el
 *         protocolo HTTP
 */
public class CalculoHTTP {

    private static final int DEFAULT_PORT = 8765;
    private static final int RESOURCE_POSITION = 1;
    private static final int FIRST_NUMBER_POSITION = 2;
    private static final int SECOND_NUMBER_POSITION = 3;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(DEFAULT_PORT);

        while (true) {
            Socket connCliente = server.accept();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            connCliente.getInputStream()));
            // Leer la solicitud del cliente (primera línea del encabezado HTTP)
            String header = reader.readLine();
            System.out.println(header);
            // GET ________ HTTP/1.1
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            connCliente.getOutputStream()));
            // Extraer información de la URL de la solicitud
            String[] info = extraeInformacion(header).split("/");

            // Verificar si la operación solicitada es suma o resta
            if (info[RESOURCE_POSITION].equalsIgnoreCase("suma") || info[RESOURCE_POSITION].equalsIgnoreCase("resta")) {
                // Realizar la operación correspondiente
                Float resultado;
                if (info[RESOURCE_POSITION].equalsIgnoreCase("suma")) {
                    resultado = Float.parseFloat(info[FIRST_NUMBER_POSITION])
                            + Float.parseFloat(info[SECOND_NUMBER_POSITION]);
                } else {
                    resultado = Float.parseFloat(info[FIRST_NUMBER_POSITION])
                            - Float.parseFloat(info[SECOND_NUMBER_POSITION]);
                }
                // Generar la respuesta HTTP con el resultado
                String html = generaPagina(resultado);

                // Escribir la cabecera de la respuesta HTTP
                writer.write("HTTP/1.1 200 OK\n");
                writer.write("Content-Type: application/json; charset=utf-8\n");
                writer.write("\n"); // Salto de línea para indicar el fin de la cabecera

                // Escribir el cuerpo de la respuesta HTTP
                writer.write(html);
                writer.flush(); // Forzar el envío de los datos al cliente
            } else {
                 // Si la operación solicitada no es suma ni resta, devolver un error 404
                writer.write("HTTP/1.1 404 Not Found\n");
                // SALTO DE LINEA PARA IR A BODY
                writer.write("\n");
                writer.write("<h1>Operación inválida.</h1>");
                writer.flush();
            }
            // Cerrar los recursos
            reader.close();
            writer.close();
            connCliente.close();
        }

    }

     // Método para generar la página HTML de respuesta con el resultado de la operación
    private static String generaPagina(Float resultado) {
        //return String.format("<h1>El resultado es: %.2f</h1>", resultado);
        return String.format("{\"Resultado\" : \"%.2f\"}", resultado);
    }
// Método para extraer la información relevante de la solicitud HTTP
    private static String extraeInformacion(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }
}
