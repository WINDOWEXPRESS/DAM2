import java.util.HashMap;

public class _5_Bancos {
    private static _5_Bancos banco;
    private String nombreBanco;
    private double fondoBanco;
    private static HashMap<String, _5_Usuario> usuarios;

    public _5_Bancos(String nombreBanco, double fondoBanco) {
        this.nombreBanco = nombreBanco;
        this.fondoBanco = fondoBanco;
        this.usuarios = new HashMap<>();
    }

    public void aniadirUsuario(_5_Usuario usuario) {
        usuarios.put(usuario.getUsuario(), usuario);
    }

    public static void transferencia(double dineroTransferir, String nombreReceptor) {
        usuarios.get(nombreReceptor).depositar(dineroTransferir);

    }

}