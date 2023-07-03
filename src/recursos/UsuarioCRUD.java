package recursos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import classes.Usuario;
import helpers.ConnectionFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioCRUD {
    
    public void cadastrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email, senha, cpf, tipo_usuario) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getTipo().name());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                stmt.close();
                }

                if (conn != null) {
                conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void atualizarCadastro(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, email = ?, senha = ?, cpf = ?, tipo_usuario = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getCpf());
            stmt.setString(5, usuario.getTipo().name());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                stmt.close();
                }

                if (conn != null) {
                conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void excluirCadastro(Usuario usuario) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, usuario.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                stmt.close();
                }

                if (conn != null) {
                conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<Usuario>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setTipo(Usuario.tipoUsuario.valueOf(rs.getString("tipo_usuario")));
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                rs.close();
                }

                if (stmt != null) {
                stmt.close();
                }

                if (conn != null) {
                conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return usuarios;
    }
}
