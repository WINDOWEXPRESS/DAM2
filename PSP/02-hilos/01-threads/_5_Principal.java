public class _5_Principal {
    public static void main(String[] args) throws InterruptedException {

        // // Crear cuentas
        // _5_Cuenta cuentaAlice = new _5_Cuenta(10000);
        // _5_Cuenta cuentaBob = new _5_Cuenta(10000);

        // // Ejecución sin sincronización
        // Thread threadAlice = new Thread(new _5_Usuario(cuentaAlice, cuentaBob));
        // Thread threadBob = new Thread(new _5_Usuario(cuentaBob, cuentaAlice));

        // threadAlice.start();
        // threadBob.start();

        // threadAlice.join();
        // threadBob.join();

        // System.out.println("Saldo de cuenta de Alice: " + cuentaAlice.getSaldo());
        // System.out.println("Saldo de cuenta de Bob: " + cuentaBob.getSaldo());

        // Ejecución sincronizada
        _5_Cuenta cuentaSincronizadaAlice = new _5_Cuenta(10000);
        _5_Cuenta cuentaSincronizadaBob = new _5_Cuenta();

        Thread threadSincronizadoAlice = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (cuentaSincronizadaAlice) {
                    for (int i = 0; i < 1000; i++) {
                        cuentaSincronizadaAlice.transferir(cuentaSincronizadaBob, 10);
                    }
                }
            }
        });

        Thread threadSincronizadoBob = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (cuentaSincronizadaAlice) {
                    for (int i = 0; i < 1000; i++) {
                        cuentaSincronizadaBob.transferir(cuentaSincronizadaAlice, 10);
                    }
                }
            }
        });

        threadSincronizadoAlice.start();
        threadSincronizadoBob.start();

        threadSincronizadoAlice.join();
        threadSincronizadoBob.join();

        System.out.println("Saldo de cuenta de Alice (sincronizado): " + cuentaSincronizadaAlice.getSaldo());
        System.out.println("Saldo de cuenta de Bob (sincronizado): " + cuentaSincronizadaBob.getSaldo());
    }
}
