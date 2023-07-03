package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import helpers.Menu;
import helpers.SessionManager;
import recursos.ManifestacaoCRUD;

public class Manifestacao {

  private String titulo;
  private String descricao;
  private int protocolo;
  private int id_usuario;

  public enum tipoManifestacao {
    RECLAMACAO("Reclamação"),
    ELOGIO("Elogio"),
    SUGESTAO("Sugestão"),
    SOLICITACAO("Solicitação");

    private String tipo;

    tipoManifestacao(String tipo) {
      this.tipo = tipo;
    }

    public String getTipo() {
      return tipo;
    }
  }
  public static void criarManifestacao(){
    Scanner sc = new Scanner(System.in);
    Menu.limparTela();
    ManifestacaoCRUD manifestacaoCRUD = new ManifestacaoCRUD();
    Manifestacao manifestacao = new Manifestacao();
    System.out.println("Digite o título da manifestação: ");
    manifestacao.setTitulo(sc.nextLine());
    System.out.println("Digite a descrição da manifestação: ");
    manifestacao.setDescricao(sc.nextLine());
    Map<Integer, Manifestacao.tipoManifestacao> tipoManifestacaoMap = new HashMap<>();
    tipoManifestacaoMap.put(1, Manifestacao.tipoManifestacao.RECLAMACAO);
    tipoManifestacaoMap.put(2, Manifestacao.tipoManifestacao.ELOGIO);
    tipoManifestacaoMap.put(3, Manifestacao.tipoManifestacao.SUGESTAO);
    tipoManifestacaoMap.put(4, Manifestacao.tipoManifestacao.SOLICITACAO);
    System.out.println("Digite o tipo de manifestação: ");
    System.out.println("1. Reclamação");
    System.out.println("2. Elogio");
    System.out.println("3. Sugestão");
    System.out.println("4. Solicitação");
    int tipoInput = sc.nextInt();
    Manifestacao.tipoManifestacao tipoManifestacao = tipoManifestacaoMap.get(tipoInput);
    if (tipoManifestacao == null) {
    System.out.println("Opção inválida.");
    Menu.menuUsuario();
    sc.close();
    }
    manifestacao.setTipo(tipoManifestacao);
    manifestacao.setStatus(Manifestacao.statusManifestacao.ABERTA);
    manifestacao.setId_usuario(SessionManager.getUsuarioAutenticado().getId());
    manifestacaoCRUD.criarManifestacao(manifestacao);
    System.out.println("Manifestação criada com sucesso!");
    Menu.menuUsuario();
  }

  public static void atualizarStatus(){
    Scanner sc = new Scanner(System.in);
    System.out.print("Digite o protocolo da manifestação: ");
    int protocolo = sc.nextInt();
    ManifestacaoCRUD manifestacaoCRUD3 = new ManifestacaoCRUD();
    Manifestacao manifestacao = manifestacaoCRUD3.listarManifestacoes().get(protocolo - 1);
    System.out.println("Digite o novo status da manifestação: ");
    System.out.println("1. Aberta");
    System.out.println("2. Em andamento");
    System.out.println("3. Fechada");
    switch (sc.nextInt()) {
      case 1:
        manifestacao.setStatus(Manifestacao.statusManifestacao.ABERTA);
        manifestacaoCRUD3.atualizarManifestacao(manifestacao);
        Menu.menuOuvidor();
        break;
      case 2:
        manifestacao.setStatus(Manifestacao.statusManifestacao.EM_ANDAMENTO);
        manifestacaoCRUD3.atualizarManifestacao(manifestacao);
        Menu.menuOuvidor();
        break;
      case 3:
        manifestacao.setStatus(Manifestacao.statusManifestacao.FECHADA);
        manifestacaoCRUD3.atualizarManifestacao(manifestacao);
        Menu.menuOuvidor();
        break;
      default:
        System.out.println("Erro");
        break;
      }
    sc.close();
  }
  public static void listarManifestacoesAbertas(){
    Scanner sc = new Scanner(System.in);
    ManifestacaoCRUD manifestacaoCRUD = new ManifestacaoCRUD();
    System.out.println("Listar manifestações em andamento");
    manifestacaoCRUD = new ManifestacaoCRUD();
    List<Manifestacao> manifestacoes = manifestacaoCRUD.listarManifestacoesPorUsuario(SessionManager.getUsuarioAutenticado().getId());
    List<Manifestacao> manifestacoesEmAndamento = new ArrayList<>();
    for (Manifestacao m : manifestacoes) {
      if (m.getStatus() == Manifestacao.statusManifestacao.EM_ANDAMENTO || m.getStatus() == Manifestacao.statusManifestacao.ABERTA) {
        manifestacoesEmAndamento.add(m);
      }
    }
    int pagina = 0;
    boolean sair = false;
    while (!sair) {
      for (int i = pagina * 2; i < (pagina * 2) + 2 && i < manifestacoesEmAndamento.size(); i++){
        Manifestacao m = manifestacoesEmAndamento.get(i);
        System.out.println("-----------------");
        System.out.println("Titulo: " + m.getTitulo() + "\nDescricao: " + m.getDescricao() + "\nProtocolo: " + m.getProtocolo() + "\nStatus: " + m.getStatus().getStatus() + "\nTipo: " + m.getTipo().getTipo() + "\n");
        System.out.println("-----------------");
      }
      if (manifestacoesEmAndamento.size() > (pagina + 1) * 2) {
        System.out.println("-----------------");
        System.out.println("1. Selecionar manifestação");
        System.out.println("2. Próxima página");
        System.out.println("3. Página anterior");
        System.out.println("4. Menu anterior");
        System.out.println("5. Sair");
        System.out.println("-----------------");
        System.out.println("Página " + (pagina + 1) + " de " + (manifestacoesEmAndamento.size() / 2));
        System.out.println("-----------------");
        int input = sc.nextInt();
        if (input == 1) {
          System.out.println("Digite o protocolo da manifestação: ");
          int protocolo = sc.nextInt();
          Menu.menuManifestacao(protocolo);
        } else if (input == 2) {
          Menu.limparTela();
          pagina++;
        } else if (input == 3) {
          Menu.limparTela();
          pagina--;
        } else if (input == 4) {
          Menu.limparTela();
          Menu.menuUsuario();
        } else {
          sair = true;
        }
      } else {
        System.out.println("-----------------");
        System.out.println("1. Selecionar manifestação");
        System.out.println("2. Próxima página");
        System.out.println("3. Página anterior");
        System.out.println("4. Menu anterior");
        System.out.println("5. Sair");
        System.out.println("-----------------");
        System.out.println("Página " + (pagina + 1) + " de " + (manifestacoesEmAndamento.size() / 2));
        System.out.println("-----------------");
        int input = sc.nextInt();
        if (input == 1) {
          System.out.println("Digite o protocolo da manifestação: ");
          int protocolo = sc.nextInt();
          Menu.menuManifestacao(protocolo);
        } else if (input == 2 && pagina > 0) {
          Menu.limparTela();
          pagina--;
        } else if (input == 3) {
          Menu.limparTela();
          Menu.menuUsuario();
        } else {
          sair = true;
        }
      }
    }
    sc.close();
  }
  public static void listarManifestacoesFechadas(){
    System.out.println("Listar manifestações encerradas");
    Scanner sc = new Scanner(System.in);
    ManifestacaoCRUD manifestacaoCRUD = new ManifestacaoCRUD();
    manifestacaoCRUD = new ManifestacaoCRUD();
    List<Manifestacao> manifestacoes = manifestacaoCRUD.listarManifestacoesPorUsuario(SessionManager.getUsuarioAutenticado().getId());
    List<Manifestacao> manifestacoesEncerradas = new ArrayList<>();
    for (Manifestacao m : manifestacoes) {
      if (m.getStatus() == Manifestacao.statusManifestacao.FECHADA) {
         manifestacoesEncerradas.add(m);}
        }
        int pagina = 0;
        boolean sair = false;
        while (!sair) {
          for (int i = pagina * 2; i < (pagina * 2) + 2 && i < manifestacoesEncerradas.size(); i++){
            Manifestacao m = manifestacoesEncerradas.get(i);
            System.out.println("Titulo: " + m.getTitulo() + "\nDescricao: " + m.getDescricao() + "\nProtocolo: " + m.getProtocolo() + "\nStatus: " + m.getStatus().getStatus() + "\nTipo: " + m.getTipo().getTipo() + "\n");
          }
        if (manifestacoesEncerradas.size() > (pagina + 1) * 2) {
            System.out.println("-----------------");
            System.out.println("1. Menu anterior");
            System.out.println("2. Próxima página");
            System.out.println("3. Página anterior");
            System.out.println("4. Sair");
            System.out.println("-----------------");
            System.out.println("Página " + (pagina + 1) + " de " + (manifestacoesEncerradas.size() / 2 + 1));
            int input = sc.nextInt();
            if (input == 1){
              Menu.limparTela();
              Menu.menuUsuario();
            }
            else if (input == 2) {
              Menu.limparTela();
              pagina++;
            } else if (input == 3 && pagina > 0) {
              Menu.limparTela();
              pagina--;
            } else{
              sair = true;
            }
          } else {
            System.out.println("-----------------");
            System.out.println("1. Menu anterior");
            System.out.println("2. Página anterior");
            System.out.println("3. Sair");
            System.out.println("-----------------");
            System.out.println("Página " + (pagina + 1) + " de " + (manifestacoesEncerradas.size() / 2 + 1));
            int input = sc.nextInt();
            if (input == 1){
              Menu.limparTela();
              Menu.menuUsuario();
            }
            else if (input == 2 && pagina > 0) {
            Menu.limparTela();
            pagina--;
            } else{
              sair = true;
            }
          }
        }
        sc.close();
  }
    // getters e setters
  public enum statusManifestacao {
    ABERTA("Aberta"),
    EM_ANDAMENTO("Em andamento"),
    FECHADA("Fechada");

    private String status;

    statusManifestacao(String status) {
      this.status = status;
    }

    public String getStatus() {
      return status;
    }
  }

  private tipoManifestacao tipo;
  private statusManifestacao status;

  public statusManifestacao getStatus() {
    return status;
  }

  public int getId_usuario() {
    return id_usuario;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescricao() {
    return descricao;
  }

  public int getProtocolo() {
    return protocolo;
  }

  public tipoManifestacao getTipo() {
    return tipo;
  }

  public void setId_usuario(int id_usuario) {
    this.id_usuario = id_usuario;
  }
  
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public void setProtocolo(int protocolo) {
    this.protocolo = protocolo;
  }

  public void setTipo(tipoManifestacao tipo) {
    this.tipo = tipo;
  }
  public void setStatus(statusManifestacao status) {
    this.status = status;
  }
}
