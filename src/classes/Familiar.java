package classes;

public class Familiar extends Usuario {

  private String cpfPaciente;
  private String prontuarioPaciente;

  public String getCpfPaciente() {
    return cpfPaciente;
  }

  public String getProntuarioPaciente() {
    return prontuarioPaciente;
  }

  public void setCpfPaciente(String cpfPaciente) {
    this.cpfPaciente = cpfPaciente;
  }

  public void setProntuarioPaciente(String prontuarioPaciente) {
    this.prontuarioPaciente = prontuarioPaciente;
  }
}
