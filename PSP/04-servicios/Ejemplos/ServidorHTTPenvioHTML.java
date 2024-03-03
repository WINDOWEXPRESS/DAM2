import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Stream;

public class ServidorHTTPenvioHTML {

    private static final int DEFAULT_PORT = 8765;
    private static final int RESOURCE_POSITION = 1;

    public static void main(String[] args) {
        try {
            // Crear un ServerSocket que escuche en el puerto 8765
            ServerSocket server = new ServerSocket(DEFAULT_PORT);
            
            // Bucle infinito para aceptar conexiones entrantes
            while (true) {
                // Aceptar una conexión del cliente
                Socket connCliente = server.accept();
                
                // Crear un lector de entrada para leer la solicitud HTTP del cliente
                BufferedReader reader = new BufferedReader(new InputStreamReader(connCliente.getInputStream()));
                
                // Leer la primera línea del encabezado HTTP que contiene la solicitud GET
                String header = reader.readLine();
                System.out.println(header);

                // Extraer la información relevante de la solicitud (la URL solicitada)
                String info = extraeInformacion(header);
                
                // Generar la página HTML correspondiente a la URL solicitada
                String html = generaPagina(info);

                // Crear un escritor de salida para enviar la respuesta HTTP al cliente
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connCliente.getOutputStream()));

                // Escribir la cabecera de la respuesta HTTP (código de estado 200 OK)
                writer.write("HTTP/1.1 200 OK\n");
                writer.write("\n"); // Separador entre la cabecera y el cuerpo de la respuesta
                
                // Escribir el cuerpo de la respuesta (contenido HTML)
                writer.write(html);

                // Forzar la escritura de los datos al cliente
                writer.flush();

                // Cerrar el lector, el escritor y el socket del cliente
                reader.close();
                writer.close();
                connCliente.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para extraer la información relevante (URL solicitada) del encabezado HTTP
    private static String extraeInformacion(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }

    // Método para generar la página HTML correspondiente a la URL solicitada
    private static String generaPagina(String info) {
        StringBuilder html = new StringBuilder();

        // Verificar si la URL solicitada es "/HTML"
        if ("/HTML".equals(info)) {
            // Leer el contenido del archivo index.html y agregarlo al StringBuilder
            File file = new File("/var/www/html/index.html");
            if (file.isFile()) {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                    Stream<String> pagina = bufferedReader.lines();
                    pagina.forEach(linea -> html.append(linea));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Retornar el contenido HTML generado
        return html.toString();
    }
}
