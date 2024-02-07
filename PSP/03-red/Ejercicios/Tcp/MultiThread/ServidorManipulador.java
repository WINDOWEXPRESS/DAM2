package Tcp.MultiThread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;

public class ServidorManipulador implements Runnable {
    private Socket socket;
    private int numeroBomba;
    private int intentos;
    private final int RANGO_MAX = 100;
    private final int RANGO_MIN = 1;
    public ServidorManipulador(Socket socket) {
        this.socket = socket;
        numeroBomba = (int) ((Math.random()*RANGO_MAX)+RANGO_MIN);
        intentos = 5;
    }

    @Override
    public void run() {
        try {
            while (true) {
               // BufferedInputStream bufferedInputStream = new BufferedInputStream(new DataInputStream(socket.getInputStream()) );
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new DataOutputStream(socket.getOutputStream()));
                

                String mensajeJuego="Inicia el juego 'Numero Bomba'\nNumero desde 1 a 100.\nMáximo 5 intentos.";
                bufferedOutputStream.write(mensajeJuego.getBytes());
                
                // int numeroIntroducido = bufferedInputStream.read();
                // if(numeroIntroducido != numeroBomba){
                //     intentos--;
                // }
    
                // String texto = "Te quedas "+intentos+" intentos.";
                // byte[] mensaje = texto.getBytes();
                // bufferedOutputStream.write(mensaje);
                // if(intentos == 0){
                //     bufferedOutputStream.write("Fin de juego".getBytes());
                // }
               // bufferedInputStream.close();
                bufferedOutputStream.close();
                socket.close();
    
            }
 
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

}
