package recursos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import classes.Manifestacao;
import helpers.ConnectionFactory;

public class ManifestacaoCRUD {

  public void criarManifestacao(Manifestacao manifestacao) {
    String sql = "INSERT INTO manifestacao (titulo, descricao, status_manifestacao, tipo_manifestacao, id_usuario) VALUES (?, ?, ?, ?, ?)";
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, manifestacao.getTitulo());
      stmt.setString(2, manifestacao.getDescricao());
      stmt.setString(3, manifestacao.getStatus().name());
      stmt.setString(4, manifestacao.getTipo().name());
      stmt.setInt(5, manifestacao.getId_usuario());
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

  public void atualizarManifestacao(Manifestacao manifestacao) {
    String sql =
      "UPDATE manifestacao SET titulo = ?, descricao = ?, status_manifestacao = ?, tipo_manifestacao = ? WHERE protocolo = ?";
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, manifestacao.getTitulo());
      stmt.setString(2, manifestacao.getDescricao());
      stmt.setString(3, manifestacao.getStatus().name());
      stmt.setString(4, manifestacao.getTipo().name());
      stmt.setInt(5, manifestacao.getProtocolo());
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

  public void deletarManifestacao(int protocolo) {
    String sql = "DELETE FROM manifestacao WHERE protocolo = ?";
    Connection conn = null;
    PreparedStatement stmt = null;
    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, protocolo);
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
  public List<Manifestacao> listarManifestacaoProtocolo(int protocolo){
    String sql = "SELECT * FROM manifestacao WHERE protocolo = ?";
    Connection conn = null;
    PreparedStatement stmt = null;
    List<Manifestacao> manifestacoes = new ArrayList<Manifestacao>();
    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, protocolo);
      java.sql.ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Manifestacao manifestacao = new Manifestacao();
        manifestacao.setTitulo(rs.getString("titulo"));
        manifestacao.setDescricao(rs.getString("descricao"));
        manifestacao.setProtocolo(rs.getInt("protocolo"));
        manifestacao.setStatus(Manifestacao.statusManifestacao.valueOf(rs.getString("status_manifestacao")));
        manifestacao.setTipo(Manifestacao.tipoManifestacao.valueOf(rs.getString("tipo_manifestacao")));
        manifestacao.setId_usuario(rs.getInt("id_usuario"));
        manifestacoes.add(manifestacao);
      }
      return manifestacoes;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
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
  public List<Manifestacao> listarManifestacoes() {
    String sql = "SELECT * FROM manifestacao";
    Connection conn = null;
    PreparedStatement stmt = null;
    List<Manifestacao> manifestacoes = new ArrayList<Manifestacao>();
    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      stmt = conn.prepareStatement(sql);
      java.sql.ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Manifestacao manifestacao = new Manifestacao();
        manifestacao.setTitulo(rs.getString("titulo"));
        manifestacao.setDescricao(rs.getString("descricao"));
        manifestacao.setProtocolo(rs.getInt("protocolo"));
        manifestacao.setStatus(Manifestacao.statusManifestacao.valueOf(rs.getString("status_manifestacao")));
        manifestacao.setTipo(Manifestacao.tipoManifestacao.valueOf(rs.getString("tipo_manifestacao")));
        manifestacao.setId_usuario(rs.getInt("id_usuario"));
        manifestacoes.add(manifestacao);
      }
      return manifestacoes;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
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
  public List<Manifestacao> listarManifestacoesPorUsuario(int id_usuario) {
    String sql = "SELECT * FROM manifestacao WHERE id_usuario = ?";
    Connection conn = null;
    PreparedStatement stmt = null;
    List<Manifestacao> manifestacoes = new ArrayList<Manifestacao>();
    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      stmt = conn.prepareStatement(sql);
      stmt.setInt(1, id_usuario);
      java.sql.ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Manifestacao manifestacao = new Manifestacao();
        manifestacao.setTitulo(rs.getString("titulo"));
        manifestacao.setDescricao(rs.getString("descricao"));
        manifestacao.setProtocolo(rs.getInt("protocolo"));
        manifestacao.setStatus(Manifestacao.statusManifestacao.valueOf(rs.getString("status_manifestacao")));
        manifestacao.setTipo(Manifestacao.tipoManifestacao.valueOf(rs.getString("tipo_manifestacao")));
        manifestacao.setId_usuario(rs.getInt("id_usuario"));
        manifestacoes.add(manifestacao);
      }
      return manifestacoes;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
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
  public List<Manifestacao> listarManifestacoesPorStatus(Manifestacao.statusManifestacao status) {
    String sql = "SELECT * FROM manifestacao WHERE status_manifestacao = ?";
    Connection conn = null;
    PreparedStatement stmt = null;
    List<Manifestacao> manifestacoes = new ArrayList<Manifestacao>();
    try {
      conn = ConnectionFactory.createConnectionToMySQL();
      stmt = conn.prepareStatement(sql);
      stmt.setString(1, status.name());
      java.sql.ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Manifestacao manifestacao = new Manifestacao();
        manifestacao.setTitulo(rs.getString("titulo"));
        manifestacao.setDescricao(rs.getString("descricao"));
        manifestacao.setProtocolo(rs.getInt("protocolo"));
        manifestacao.setStatus(Manifestacao.statusManifestacao.valueOf(rs.getString("status_manifestacao")));
        manifestacao.setTipo(Manifestacao.tipoManifestacao.valueOf(rs.getString("tipo_manifestacao")));
        manifestacao.setId_usuario(rs.getInt("id_usuario"));
        manifestacoes.add(manifestacao);
      }
      return manifestacoes;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
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
}
