
public class _5_Usuario implements Runnable {
    private _5_Cuenta cuentaOrigen;
    private _5_Cuenta cuentaDestino;

    public _5_Usuario(_5_Cuenta cuentaOrigen, _5_Cuenta cuentaDestino) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            cuentaOrigen.transferir(cuentaDestino, 10D);
        }
    }
}
