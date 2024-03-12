
public class _5_Cuenta {
    private double saldo;
    public _5_Cuenta() {
    }
    public _5_Cuenta(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void transferir(_5_Cuenta destino, double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            destino.depositar(cantidad);
            System.out.println(Thread.currentThread().getName() + " transfirió " + cantidad + " euros a " + destino);
        } else {
            System.out.println(Thread.currentThread().getName() + " no tiene suficiente saldo para transferir " + cantidad + " euros.");
        }
    }

    public synchronized void depositar(double cantidad) {
        saldo += cantidad;
    }
    

    @Override
    public String toString() {
        return "Cuenta[saldo=" + saldo + "]";
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}