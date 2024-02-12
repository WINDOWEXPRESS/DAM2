package Tcp.MultiThread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;

public class ServidorManipulador implements Runnable {
    private Socket socket;
    private int numeroBomba;
    private int intentos;
    private boolean juegoTerminado;
    private final int RANGO_MAX = 100;
    private final int RANGO_MIN = 1;

    public ServidorManipulador(Socket socket) {
        this.socket = socket;
        numeroBomba = (int) ((Math.random() * RANGO_MAX) + RANGO_MIN);
        intentos = 5;
        juegoTerminado = false;
    }

    @Override
    public void run() {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(
                    socket.getInputStream());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    socket.getOutputStream());

            String mensajeJuego = "Inicia el juego 'Numero Bomba'\nNumero desde 1 a 100.\nMáximo 5 intentos.";
            bufferedOutputStream.write(mensajeJuego.getBytes());
            bufferedOutputStream.flush();

            while (intentos > 0 && !juegoTerminado) {

                int numeroIntroducido = bufferedInputStream.read();
                String respuesta;

                // System.out.println(numeroIntroducido);

                if (numeroIntroducido == numeroBomba) {
                    respuesta = "¡Felicidades! Has adivinado el número.";
                    juegoTerminado = true; // Juego terminado
                } else if (numeroIntroducido < numeroBomba) {
                    respuesta = "El número es mayor. Te quedan " + --intentos + " intentos.";
                } else {
                    respuesta = "El número es menor. Te quedan " + --intentos + " intentos.";
                }
                // System.out.println(respuesta);
                bufferedOutputStream.write(respuesta.getBytes());
                if (intentos == 0) {
                    String finJuego = "\nSe acabaron los intentos. El número correcto era: " + numeroBomba;
                    bufferedOutputStream.write(finJuego.getBytes());
                }
                bufferedOutputStream.flush();
            }
            bufferedInputStream.close();
            bufferedOutputStream.close();
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
