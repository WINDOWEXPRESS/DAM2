import java.math.BigDecimal;

public class Usuario implements Runnable{
    private int id;
    // en futuro el iban lo genera el banco aleatoriamente lo asigna y lo guarda
    private String iban;
    // usuario puede dividir en mas variables como nombre ,apellido,nacimiento...
    private String usuario;
    private double dinero;

    public Usuario(int id, String usuario, String iban) {
        this.id = id;
        this.usuario = usuario;
        this.iban = "5312 1112 1113 1234";
    }


    public Usuario(String usuario2, double dinero) {
        this.usuario = usuario2;
        this.dinero= dinero;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
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

    public void transferencia(double dinero, String ibanReceptor) {
        if (esDineroSuficiente(dinero)) {
            this.dinero -= dinero;
            Bancos.transferencia(getIban(), ibanReceptor);
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    public void depositar(double dinero) {
        this.dinero += dinero;
    }

    public void retirar(double dinero) {
        if (esDineroSuficiente(dinero)) {
            this.dinero -= dinero;
        } else {
            System.out.println("Saldo insuficiente!");
        }

    }

    public boolean esDineroSuficiente(double dinero) {
        return this.dinero < dinero;
    }


    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

}
