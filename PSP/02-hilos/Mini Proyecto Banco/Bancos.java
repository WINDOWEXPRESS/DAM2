import java.util.HashMap;

public class Bancos {
    private static Bancos banco;
    private String nombreBanco;
    private double fondoBanco;
    private HashMap<String, Usuario> usuarios;

    public Bancos(String nombreBanco, double fondoBanco) {
        this.nombreBanco = nombreBanco;
        this.fondoBanco = fondoBanco;
        this.usuarios = new HashMap<>();
    }

    public void aniadirUsuario(Usuario usuario) {
        usuarios.put(usuario.getIban(), usuario);
    }

    public static synchronized void transferencia(String ibanEmisor, String ibanReceptor) {

    }

}