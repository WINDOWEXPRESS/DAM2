import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class UDPServerPrimo {
    public static void main(String[] args) {
        try {
            try (DatagramSocket socket = new DatagramSocket(9876)) {
                byte[] receivedData = new byte[1024];

                while (true) {
                    DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                    socket.receive(receivedPacket); // Espera y recibe el paquete

                    // Extrae la información del paquete
                    System.out.println("Mensaje recibido: " + byteArrayToInt(receivedData));

                    try {
                        String mensaje = "El numero introducido "
                                + (esPrimo(byteArrayToInt(receivedData)) ? "Si" : "No") + " es primo";
                        byte[] datos = mensaje.getBytes();
                        DatagramSocket serverSocket = new DatagramSocket();
                        DatagramPacket paqEnviado = new DatagramPacket(datos, datos.length, receivedPacket.getAddress(),
                                receivedPacket.getPort());
                        serverSocket.send(paqEnviado);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false; // Los números menores o iguales a 1 no son primos
        }

        // Verificar divisibilidad hasta la raíz cuadrada del número
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false; // Si es divisible por algún número, no es primo
            }
        }

        return true; // Si no es divisible por ningún número, es primo

    }

    private static int byteArrayToInt(byte[] bytes) {
        return (bytes[0] << 24) | ((bytes[1] & 0xFF) << 16) | ((bytes[2] & 0xFF) << 8) | (bytes[3] & 0xFF);
    }
    /*
     * bytes[0] << 24: Desplaza los bits del byte en la posición 0 a la izquierda
     * por 24 lugares, llenando con ceros en la parte derecha.
     * ((bytes[1] & 0xFF) << 16): Realiza una operación lógica AND con 0xFF para
     * asegurarse de que solo se tomen los últimos 8 bits del byte en la posición 1,
     * luego desplaza esos bits a la izquierda por 16 lugares.
     * ((bytes[2] & 0xFF) << 8): Similar al paso anterior para el byte en la
     * posición 2, pero desplazando por 8 lugares.
     * (bytes[3] & 0xFF): Toma los últimos 8 bits del byte en la posición 3.
     * Luego, todos estos resultados se combinan con el operador | (bitwise OR) para
     * obtener el valor final.
     */
}
