package recursos;

import classes.Manifestacao;
// ignorar
public class testeCrud {

  public static void main(String[] args) {
    ManifestacaoCRUD manifestacaoCRUD = new ManifestacaoCRUD();

    // Manifestacao manifestacao = new Manifestacao();
    // manifestacao.setTitulo("Primeira Manifestação");
    // manifestacao.setDescricao("Descrição da primeira manifestação.");
    // manifestacaoCRUD.criarManifestacao(manifestacao);

    Manifestacao manifestacao2 = new Manifestacao();
    manifestacao2.setTitulo("Segunda Manifestação");
    manifestacao2.setDescricao("Descrição da segunda manifestação.");
    manifestacaoCRUD.criarManifestacao(manifestacao2);

    Manifestacao manifestacao3 = new Manifestacao();
    manifestacao3.setTitulo("Terceira Manifestação");
    manifestacao3.setDescricao("Descrição da terceira manifestação.");
    manifestacaoCRUD.criarManifestacao(manifestacao3);

    System.out.println("Teste de CRUD");
    for (Manifestacao m : manifestacaoCRUD.listarManifestacoes()) {
      System.out.println(
        "Titulo: " +
        m.getTitulo() +
        "\nDescricao: " +
        m.getDescricao() +
        "\nProtocolo: " +
        m.getProtocolo() +
        "\n"
      );
    }
  }
}
