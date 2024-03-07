package Ejercicio2;

import Ejercicio2.ReceptorUDP.Obs;

public class Main {
    // Definir el número total de parámetros esperados en la línea de comandos
    private static int NUMERO_TOTAL_PARAMETROS = 2;
    private static final int INDEX_PUERTO_ESCUCHAR = 0;
    private static final int INDEX_PUERTO_ENVIAR = 1;

    public static void main(String[] args) {
        /*
         * // Verificar si se proporcionó el puerto como argumento de línea de comandos
         * if (args.length != NUMERO_TOTAL_PARAMETROS) {
         * System.out.println("Uso: java Main <puerto escuchar> <puerto enviar>");
         * return;
         * }
         */
        // Obtener los parámetros de entrada
        try {
            // int puertoEscucha = Integer.parseInt(args[INDEX_PUERTO_ESCUCHAR]);
            // int puertoEnviar = Integer.parseInt(args[INDEX_PUERTO_ENVIAR]);
            // EnviadorUDP enviadorUDP = new EnviadorUDP(puertoEnviar);
            // ReceptorUDP receptorUDP = new ReceptorUDP(puertoEscucha);

            EnviadorUDP enviadorUDP = new EnviadorUDP(8765);
            ReceptorUDP receptorUDP = new ReceptorUDP(8888);
            receptorUDP.registerObserver(new Obs() {

                @Override
                public void update(String mensaje) {
                    // System.out.println("HOLA DESDE ANONIMO OBSERVER.");
                    // envioBroadcast(puertoEnviar, mensaje);
                    enviadorUDP.envio(mensaje);
                }

            });

            receptorUDP.escuchar();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    /*
     * private static void envioBroadcast(int puerto, String mensaje) {
     * try {
     * // System.out.println(mensaje + 1);
     * DatagramSocket ds = new DatagramSocket();
     * byte buffer[] = mensaje.getBytes();
     * String ip = "255.255.255.255";
     * ds.setBroadcast(true);
     * DatagramPacket p = new DatagramPacket(
     * buffer,
     * buffer.length,
     * InetAddress.getByName(ip),
     * puerto);
     * 
     * ds.send(p);
     * ds.close();
     * } catch (SocketException e) {
     * e.printStackTrace();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     */
}
