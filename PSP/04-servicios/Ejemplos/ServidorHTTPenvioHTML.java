import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.stream.Stream;

/***
 * 
 * @author Jorge Dueñas Lerín
 *         Objetivo: entender cómo se programa un servicio usando de ejemplo el
 *         protocolo HTTP
 */
public class ServidorHTTPenvioHTML {

    private static final int DEFAULT_PORT = 8765;
    private static final int RESOURCE_POSITION = 1;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(DEFAULT_PORT);

        while (true) {
            Socket connCliente = server.accept();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            connCliente.getInputStream()));
            String header = reader.readLine();
            System.out.println(header);
            // GET ________ HTTP/1.1
            String info = extraeInformacion(header);
            String html = generaPagina(info);

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            connCliente.getOutputStream()));
            // Escribir cabecera
            writer.write("HTTP/1.1 200 OK\n");
            // writer.write("Content-Type: application/json; charset=utf-8\n");
            // SALTO DE LINEA PARA IR A BODY
            writer.write("\n");

            writer.write(html);

            writer.flush();

            reader.close();
            writer.close();

            connCliente.close();
        }
    }

    private static String generaPagina(String info) {
        StringBuilder html = new StringBuilder();
        File file = new File("/var/www/html/index.html");
        // MOSTRAR LICHERO SI URL INTRODUCIDO USUARIO ES "/HTML"
        if (file.isFile() && info.equals("/HTML")) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                Stream<String> pagina = bufferedReader.lines();
                pagina.forEach(linea -> {
                    html.append(linea);
                });

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return html.toString();
    }

    private static String extraeInformacion(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }
}