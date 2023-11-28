import java.util.HashMap;

public class _5_Bancos {
    private static _5_Bancos banco;
    private String nombreBanco;
    private double fondoBanco;
    private HashMap<String, _5_Usuario> usuarios;

    public _5_Bancos(String nombreBanco, double fondoBanco) {
        this.nombreBanco = nombreBanco;
        this.fondoBanco = fondoBanco;
        this.usuarios = new HashMap<>();
    }

    public void aniadirUsuario(_5_Usuario usuario) {
        usuarios.put(usuario.getIban(), usuario);
    }

    public static synchronized void transferencia(String ibanEmisor, String ibanReceptor) {

    }

}