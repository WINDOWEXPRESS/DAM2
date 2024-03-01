
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
    private static final int FIST_NUMBER_POSITION = 2;
    private static final int SECON_NUMBER_POSITION = 2;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(DEFAULT_PORT);

        while (true) {
            Socket connCliente = server.accept();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            connCliente.getInputStream()));
            // OBTENER DATOS DE PEDICION
            String header = reader.readLine();
            System.out.println(header);
            // GET ________ HTTP/1.1
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            connCliente.getOutputStream()));

            String[] info = extraeInformacion(header).split("/");

            if (info[RESOURCE_POSITION].equalsIgnoreCase("suma") || info[RESOURCE_POSITION].equalsIgnoreCase("resta")) {
                if (info[RESOURCE_POSITION].equalsIgnoreCase("suma")) {
                    Float resultado = Float.parseFloat(info[FIST_NUMBER_POSITION])
                            + Float.parseFloat(info[SECON_NUMBER_POSITION]);
                    String html = generaPagina(resultado);

                    // Escribir cabecera
                    writer.write("HTTP/1.1 200 OK\n");
                    // writer.write("Content-Type: application/json; charset=utf-8\n");
                    // SALTO DE LINEA PARA IR A BODY
                    writer.write("\n");
                    writer.write(html);
                    writer.flush();
                }
            } else {
                writer.write("HTTP/1.1 404\n");
                writer.write("Content-Type: application/json; charset=utf-8\n");
                // SALTO DE LINEA PARA IR A BODY
                writer.write("\n");
                writer.write("<h1>Operacion invalido.</h1>");
                writer.flush();
            }

            reader.close();
            writer.close();
            connCliente.close();
        }
    }

    private static String generaPagina(Float info) {

        return String.format("{\"Resultado\" : \"%.2f\"}", info);
    }

    private static String extraeInformacion(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }
}
