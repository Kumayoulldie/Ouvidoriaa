package helpers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import classes.Usuario;

public class auth {
    public static boolean autenticar(Usuario usuario) {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setTipo(Usuario.tipoUsuario.valueOf(rs.getString("tipo_usuario")));
                SessionManager.iniciarSessao(usuario);
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
