package helpers;
import classes.Usuario;

public class SessionManager {
    private static Usuario usuarioAutenticado;
    
    public static void iniciarSessao(Usuario usuario) {
        usuarioAutenticado = usuario;
    }

    public static void encerrarSessao() {
        usuarioAutenticado = null;
    }

    public static boolean autenticado() {
        return usuarioAutenticado != null;
    }

    public static Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }
}
