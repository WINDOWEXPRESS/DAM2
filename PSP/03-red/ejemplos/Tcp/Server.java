import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		ServerSocket server;
		try {
			server = new ServerSocket(1234);
			while (true) {
				// Espera cliente
				Socket socket = server.accept();

				DataInputStream in = new DataInputStream(socket.getInputStream());

				/* Segunda forma de leer */
				// InputStream inputStream = socket.getInputStream();
				// BufferedReader bufferedReader = new BufferedReader(new
				// InputStreamReader(inputStream,StandardCharsets.UTF_8));
				// System.out.println(bufferedReader.readLine());

				// String s = in.readUTF();
				byte[] data = in.readAllBytes();
				String s = new String(data, 0, data.length);
				System.out.println("El mensaje es : " + s);
				/* Si es demasiado grande se para no llega enviar nada */
				// for (int x = 0; x < 32000; x++) {
				// for (int i = 0; i < 1000; i++) {
				// out.writeUTF(s.toUpperCase());
				// }
				// }
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.writeUTF(s.toUpperCase());
				// esforzar limpiar buffer
				out.flush();

				in.close();
				out.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}