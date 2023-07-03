package classes;

import java.sql.Date;

public class Usuario {
  private int id;
  private String nome;
  private String email;
  private String senha;
  private String cpf;
  private Date dataNascimento;

  public enum tipoUsuario {
    OUVIDOR("Ouvidor"),
    PACIENTE("Paciente"),
    FAMILIAR("Familiar");

    private String tipo;

    tipoUsuario(String tipo) {
      this.tipo = tipo;
    }

    public String getTipo() {
      return tipo;
    }
  }

  private tipoUsuario tipo;
  
  public int getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }
  
  public String getSenha() {
    return senha;
  }
  public String getCpf() {
    return cpf;
  }

  public tipoUsuario getTipo() {
    return tipo;
  }

  public Date getDataNascimento() {
    return dataNascimento;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setTipo(tipoUsuario tipo) {
    this.tipo = tipo;
  }
}
