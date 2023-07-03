package classes;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import helpers.Menu;
import helpers.SessionManager;
import recursos.ManifestacaoCRUD;
import recursos.RespostaCRUD;

public class Resposta {
    private int idResposta;
    private int protocolo;
    private String descricao;
    private int idOuvidor;
    private String nomeOuvidor;
    private Timestamp dataResposta;
    private String autor;

    public static void criarResposta(int protocolo){
      Scanner sc = new Scanner(System.in);
      ManifestacaoCRUD manifestacaoCRUD = new ManifestacaoCRUD();
      List<Manifestacao> manifestacoes = manifestacaoCRUD.listarManifestacoes();
      if (protocolo < 1 || protocolo > manifestacoes.size()) {
          System.out.println("Protocolo inválido!");
          sc.close();
          return;
      }
      Manifestacao manifestacao = manifestacoes.get(protocolo - 1);
      System.out.println("Digite a resposta: ");
      String descricao = sc.nextLine();
      if (descricao.isEmpty()) {
          System.out.println("Descrição inválida!");
          sc.close();
          return;
      }
      if (descricao.length() > 255) {
          System.out.println("Descrição muito longa!");
          sc.close();
          return;
      }
      if (manifestacao.getStatus().getStatus().equals("Encerrada")) {
          System.out.println("Manifestação encerrada!");
          sc.close();
          return;
      }
    RespostaCRUD respostaCRUD = new RespostaCRUD();
    Resposta resposta = new Resposta();
    resposta.setAutor(SessionManager.getUsuarioAutenticado().getNome());
    resposta.setDescricao(descricao);
    resposta.setProtocolo(manifestacao.getProtocolo());
    resposta.setDataResposta(new java.sql.Timestamp(new Date().getTime()));
    respostaCRUD.criarResposta(resposta);
    System.out.println("Resposta cadastrada com sucesso!");
    Menu.menuManifestacao(protocolo);
    sc.close();
  }
    public static void listarRespostas(int protocolo){
      Scanner sc = new Scanner(System.in);
      RespostaCRUD respostaCRUD = new RespostaCRUD();
      ManifestacaoCRUD manifestacaoCRUD = new ManifestacaoCRUD();
      List<Manifestacao> manifestacao = manifestacaoCRUD.listarManifestacaoProtocolo(protocolo);
      List<Resposta> respostas = respostaCRUD.listarRespostas(protocolo);
      int pagina = 0;
      boolean sair = false;
      while (!sair) {
          for (int i = pagina * 2; i < (pagina + 1) * 2 && i < respostas.size(); i++) {
              Resposta r = respostas.get(i);
              Manifestacao m = manifestacao.get(0);
              System.out.println("----------------------------------");
              System.out.println("Manifestação: " + m.getTitulo() + "\nResposta: " + r.getDescricao() + "\nAutor: " + r.getAutor() + "\nData de resposta: " + r.getDataResposta() + "\n");
              System.out.println("----------------------------------");
          }
          if (respostas.size() > (pagina + 1) * 2) {
              System.out.println("----------------------------------");
              System.out.println("1. Próxima página");
              System.out.println("2. Página anterior");
              System.out.println("3. Voltar menu anterior");
              System.out.println("4. Sair");
              System.out.println("----------------------------------");
              System.out.println("Página " + (pagina + 1) + " de " + (respostas.size() / 2));
              System.out.println("----------------------------------");
              int input = sc.nextInt();
              if (input == 1) {
                  Menu.limparTela();
                  pagina++;
              } else if (input == 2 && pagina > 0) {
                  Menu.limparTela();
                  pagina--;
              } else if (input == 3) {
                  Menu.limparTela();
                  Menu.menuManifestacao(protocolo);
              } 
              else{
                  sair = true;
              }
          } else {
              System.out.println("----------------------------------");
              System.out.println("1. Página anterior");
              System.out.println("2. Voltar menu anterior");
              System.out.println("3. Sair");
              System.out.println("----------------------------------");
              System.out.println("Página " + (pagina + 1) + " de " + (respostas.size() / 2));
              System.out.println("----------------------------------");
              int input = sc.nextInt();
              if (input == 1 && pagina > 0) {
                  Menu.limparTela();
                  pagina--;
              } else if (input == 2) {
                  Menu.limparTela();
                  Menu.menuManifestacao(protocolo);
              }
              else{
                  sair = true;
              }
          }
      }
      sc.close();
  }
    //getters e setters
    public String getAutor() {
        return autor;
    }
    public int getIdResposta() {
        return idResposta;
    }
    public int getProtocolo() {
        return protocolo;
    }
    public int getIdOuvidor() {
        return idOuvidor;
    }
    public String getNomeOuvidor() {
        return nomeOuvidor;
    }
    public String getDescricao() {
        return descricao;
    }

    public Timestamp getDataResposta() {
        return dataResposta;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setIdResposta(int idResposta) {
        this.idResposta = idResposta;
    }
    public void setProtocolo(int protocolo) {
        this.protocolo = protocolo;
    }
    public void setIdOuvidor(int idOuvidor) {
        this.idOuvidor = idOuvidor;
    }
    public void setNomeOuvidor(String nomeOuvidor) {
        this.nomeOuvidor = nomeOuvidor;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setDataResposta(Timestamp dataResposta) {
        this.dataResposta = dataResposta;
    }
}
