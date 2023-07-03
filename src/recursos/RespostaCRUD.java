package recursos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import classes.Resposta;
import helpers.ConnectionFactory;

public class RespostaCRUD {
    
    public  void criarResposta(Resposta Resposta) {
        String sql = "INSERT INTO resposta (protocolo, descricao, idOuvidor, nomeOuvidor, dataResposta, autor) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Resposta.getProtocolo());
            stmt.setString(2, Resposta.getDescricao());
            stmt.setInt(3, Resposta.getIdOuvidor());
            stmt.setString(4, Resposta.getNomeOuvidor());
            stmt.setTimestamp(5, new java.sql.Timestamp(Resposta.getDataResposta().getTime()));
            stmt.setString(6, Resposta.getAutor());
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

    public void atualizarResposta(Resposta Resposta) {
        String sql = "UPDATE resposta SET protocolo = ?, descricao = ?, idOuvidor = ?, nomeOuvidor = ?, autor = ? WHERE idResposta = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Resposta.getProtocolo());
            stmt.setString(2, Resposta.getDescricao());
            stmt.setInt(3, Resposta.getIdOuvidor());
            stmt.setString(4, Resposta.getNomeOuvidor());
            stmt.setInt(5, Resposta.getIdResposta());
            stmt.setString(6, Resposta.getAutor());
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

    public void deletarResposta(int idResposta){
    String sql = "DELETE FROM resposta WHERE idResposta = ?";
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
        conn = ConnectionFactory.createConnectionToMySQL();
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idResposta);
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

    public List<Resposta> listarRespostas(int protocolo) {
        String sql = "SELECT * FROM resposta WHERE protocolo = ? ORDER BY dataResposta ASC";
        List<Resposta> listaResposta = new ArrayList<Resposta>();
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, protocolo);
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Resposta Resposta = new Resposta();
                Resposta.setIdResposta(rs.getInt("idResposta"));
                Resposta.setProtocolo(rs.getInt("protocolo"));
                Resposta.setDescricao(rs.getString("descricao"));
                Resposta.setIdOuvidor(rs.getInt("idOuvidor"));
                Resposta.setNomeOuvidor(rs.getString("nomeOuvidor"));
                Resposta.setDataResposta(rs.getTimestamp("dataResposta"));
                Resposta.setAutor(rs.getString("autor"));
                listaResposta.add(Resposta);
            }
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
        return listaResposta;
    }
}
