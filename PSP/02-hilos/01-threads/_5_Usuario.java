
public class _5_Usuario implements Runnable {
    private String usuario;
    private double dinero;
    private static final Object lock = new Object();

    public _5_Usuario(String usuario, double dinero) {
        this.usuario = usuario;
        this.dinero = dinero;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public void depositar(double dinero) {
        this.dinero += dinero;
    }

    /*   
    public synchronized void transferencia(double dinero, String ibanReceptor) {
        this.dinero -= dinero;
        _5_Bancos.transferencia(dinero, ibanReceptor);
    }
    */
    public void transferencia(double dinero, String ibanReceptor) {
        this.dinero -= dinero;
        _5_Bancos.transferencia(dinero, ibanReceptor);
    }

    @Override
    public void run() {
        synchronized(lock){
            for (int i = 0; i < 10000; i++) {
                transferencia(1, "chen");
                System.out.println(usuario +" ha transferido "+i+" veces a chen");
            }
        }

    }

}
