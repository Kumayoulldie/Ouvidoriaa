package helpers;

import java.util.List;
import java.util.Scanner;

import classes.Manifestacao;
import recursos.ManifestacaoCRUD;
import classes.Resposta;
import classes.Usuario;
import recursos.UsuarioCRUD;

public class Menu {
  public static void menuOuvidor() {
    limparTela();
    System.out.println("--------------------");
    System.out.println("Olá, " + SessionManager.getUsuarioAutenticado().getNome() + "!");
    System.out.println("--------------------");
    System.out.println("1. Manifestações ativas");
    System.out.println("2. Manifestações encerradas");
    System.out.println("3. Configurações de conta");
    System.out.println("5. Sair");
    System.out.println("--------------------");
    Scanner sc = new Scanner(System.in);
    switch (sc.nextInt()) {
      case 1:
        limparTela();
        System.out.println("Manifestações ativas");
        ManifestacaoCRUD manifestacaoCRUD = new ManifestacaoCRUD();
        List<Manifestacao> manifestacoes = manifestacaoCRUD.listarManifestacoes();
        int pagina = 0;
        boolean sair = false;
        while (!sair) {
          for (int i = pagina * 2; i < (pagina * 2) + 2 && i < manifestacoes.size(); i++) {
            Manifestacao m = manifestacoes.get(i);
            System.out.println("--------------------");
            System.out.println("Titulo: " + m.getTitulo() + "\nDescricao: " + m.getDescricao() + "\nProtocolo: " + m.getProtocolo() + "\nStatus: " + m.getStatus().getStatus() + "\nTipo: " + m.getTipo().getTipo() + "\n");
            System.out.println("--------------------");
          }
          if (manifestacoes.size() > (pagina + 1) * 2) {
            System.out.println("--------------------");
            System.out.println("1. Selecionar manifestação");
            System.out.println("2. Próxima página");
            System.out.println("3. Página anterior");
            System.out.println("4. Menu anterior");
            System.out.println("5. Sair");
            System.out.println("--------------------");
            System.out.println("Página " + (pagina + 1) + " de " + (manifestacoes.size() / 2));
            System.out.println("--------------------");
            int input = sc.nextInt();
            if (input == 2) {
              limparTela();
              pagina++;
            } else if (input == 4) {
              menuOuvidor();
            } else if (input == 1) {
              System.out.println("Digite o protocolo da manifestação: ");
              int protocolo = sc.nextInt();
              menuManifestacao(protocolo);
            } else if (input == 3) {
              limparTela();
              pagina--;
            }
            else {
              sair = true;
            }
          } else {
            System.out.println("--------------------");
            System.out.println("1. Selecionar manifestação");
            System.out.println("2. Página anterior");
            System.out.println("3. Menu anterior");
            System.out.println("4. Sair");
            System.out.println("--------------------");
            System.out.println("Página " + (pagina + 1) + " de " + (manifestacoes.size() / 2));
            System.out.println("--------------------");
            int input = sc.nextInt();
            if (input == 2) {
              limparTela();
              pagina--;
            } else if (input == 1) {
              System.out.println("Digite o protocolo da manifestação: ");
              int protocolo = sc.nextInt();
              menuManifestacao(protocolo);
            } else if (input == 3) {
              menuOuvidor();
            } else {
              sair = true;
            }
          }
        }
        sc.close();
        break;
      case 2:
        limparTela();
        System.out.println("Manifestações encerradas");
        ManifestacaoCRUD manifestacaoCRUD2 = new ManifestacaoCRUD();
        List<Manifestacao> manifestacoes2 = manifestacaoCRUD2.listarManifestacoesPorStatus(Manifestacao.statusManifestacao.FECHADA);
        int pagina2 = 0;
        boolean sair2 = false;
        while (!sair2) {
          for (int i = pagina2 * 2; i < (pagina2 * 2) + 2 && i < manifestacoes2.size(); i++) {
            Manifestacao m = manifestacoes2.get(i);
            System.out.println("--------------------");
            System.out.println("Titulo: " + m.getTitulo() + "\nDescricao: " + m.getDescricao() + "\nProtocolo: " + m.getProtocolo() + "\nStatus: " + m.getStatus().getStatus() + "\nTipo: " + m.getTipo().getTipo() + "\n");
            System.out.println("--------------------");
          }
          if (manifestacoes2.size() > (pagina2 + 1) * 2) {
            System.out.println("--------------------");
            System.out.println("1. Próxima Página");
            System.out.println("2. Página Anterior");
            System.out.println("3. Selecionar manifestação");
            System.out.println("5. Sair");
            System.out.println("--------------------");
            System.out.println("Página " + (pagina2 + 1) + " de " + (manifestacoes2.size() / 2 + 1));
            int input = sc.nextInt();
            if (input == 1) {
              limparTela();
              pagina2++;
            } else if (input == 2 && pagina2 > 0) {
              limparTela();
              pagina2--;
            } 
            else if (input == 3){
              System.out.println("Digite o protocolo da manifestação: ");
              int protocolo = sc.nextInt();
              menuManifestacao(protocolo);
            }
            else if (input == 4) {
              menuOuvidor();
            }
            else {
              sair2 = true;
            }
          } else if (pagina2 == 0 && manifestacoes2.size() > 0) {
            System.out.println("--------------------");
            System.out.println("1. Menu anterior");
            System.out.println("2. Atualizar status de manifestação");
            System.out.println("3. Sair");
            System.out.println("--------------------");
            System.out.println("Página " + (pagina2 + 1) + " de " + (manifestacoes2.size() / 2 + 1));
            int input = sc.nextInt();
            if (input == 1) {
              menuOuvidor();
            }
            else if (input == 2) {
              Manifestacao.atualizarStatus();
            }
            else {
              sair2 = true;
            }
          }
          else {
          System.out.println("--------------------");
          System.out.println("Nenhuma manifestação encerrada.");
          System.out.println("1. Menu anterior");
          System.out.println("2. Sair");
          System.out.println("--------------------");
          int input = sc.nextInt();
          if (input == 1) {
            menuOuvidor();
          }
          else {
            sair2 = true;
          }
        }
        }
        break;
      case 3:
        limparTela();
        System.out.println("Configurações de conta");
        System.out.println("--------------------");
        System.out.println("1. Editar dados");
        System.out.println("2. Excluir conta");
        System.out.println("3. Menu anterior");
        System.out.println("--------------------");
        int input = sc.nextInt();
        if (input == 1) {
          System.out.println("wip");
        } else if (input == 2) {
          deletarUsuario();
        } else if (input == 3) {
          menuOuvidor();
        } else {
          System.out.println("Opção inválida");
        }
        break;
      default:
        System.out.println("Erro");
        break;
    }
    sc.close();
  }
  public static void menuUsuario() {
    limparTela();
    System.out.println("--------------------");
    System.out.println("Olá, " + SessionManager.getUsuarioAutenticado().getNome() + "!");
    System.out.println("--------------------");
    System.out.println("1. Criar manifestação");
    System.out.println("2. Listar manifestações em andamento");
    System.out.println("3. Listar manifestações encerradas");
    System.out.println("4. Configurações de conta");
    System.out.println("5. Sair");
    System.out.println("--------------------");
    Scanner sc = new Scanner(System.in);

    switch (sc.nextInt()) {
      case 1:
        limparTela();
        sc.nextLine();
        Manifestacao.criarManifestacao();
        break;
      case 2:
        limparTela();
        Manifestacao.listarManifestacoesAbertas();
      case 3:
        limparTela();
        Manifestacao.listarManifestacoesFechadas();
      case 4:
        limparTela();
        if (SessionManager.getUsuarioAutenticado().getTipo() == Usuario.tipoUsuario.PACIENTE) {
          menuPaciente();
        } else {
          menuFamiliar();
        }
        break;
      case 5:
        System.out.println("Sair");
        break;
      default:
        System.out.println("Erro");
        break;
    }
    sc.close();
  }
  public static void menuPaciente() {
    System.out.println("--------------------");
    System.out.println("1. Editar dados");
    System.out.println("2. Excluir conta");
    System.out.println("3. Sair");
    System.out.println("--------------------");
    Scanner sc = new Scanner(System.in);
    switch (sc.nextInt()) {
      case 1:
        System.out.println("WIP");
        menuUsuario();
        break;
      case 2:
        limparTela();
        deletarUsuario();
        break;
      case 3:
        System.out.println("Sair");
        break;
      default:
        System.out.println("Erro");
        break;
    }
    sc.close();
  }
  public static void menuFamiliar() {
    // wip
    System.out.println("Digite o tipo de operação: ");
    System.out.println("1. Editar dados");
    System.out.println("2. Excluir conta");
    System.out.println("3. Sair");
    Scanner sc = new Scanner(System.in);
    if (sc.nextInt() == 1) {
      System.out.println("WIP");
      menuUsuario();
    } else {
      deletarUsuario();
    }
    sc.close();
  }
  public static void menuManifestacao(int protocolo) {
    Scanner sc = new Scanner(System.in); // também não sei como fechar esse scanner sem matar o resto do programa
    ManifestacaoCRUD manifestacaoCRUD = new ManifestacaoCRUD();
    List<Manifestacao> manifestacoes = manifestacaoCRUD.listarManifestacaoProtocolo(protocolo);
    if (manifestacoes.size() == 0) {
      System.out.println("Manifestação não encontrada.");
    } else {
      limparTela();
      Manifestacao m = manifestacoes.get(0);
      System.out.println("--------------------");
      System.out.println("Titulo: " + m.getTitulo() + "\nDescricao: " + m.getDescricao() + "\nProtocolo: " + m.getProtocolo() + "\nStatus: " + m.getStatus().getStatus() + "\nTipo: " + m.getTipo().getTipo() + "\n");
      System.out.println("--------------------");
      System.out.println("1. Responder manifestação");
      System.out.println("2. Encerrar manifestação");
      System.out.println("3. Ver respostas");
      System.out.println("4. Menu anterior");
      System.out.println("5. Sair");
      int input = sc.nextInt();
      switch (input) {
        case 1:
          Resposta.criarResposta(protocolo);
        case 2:
          System.out.println("Encerrando manifestação");
          m.setStatus(Manifestacao.statusManifestacao.FECHADA);
          manifestacaoCRUD.atualizarManifestacao(m);
          menuOuvidor();
          break;
        case 3:
          limparTela();
          Resposta.listarRespostas(protocolo);
          break;
        case 4:
          menuOuvidor();
          break;
        case 5:
          System.out.println("Sair");
          break;
        default:
          System.out.println("Erro");
          break;
      }
    }
  }
  public static void limparTela() {
  System.out.print("\033[H\033[2J");
  System.out.flush();
}
  public static void deletarUsuario(){
    limparTela();
    Scanner sc = new Scanner(System.in);
    System.out.println("--------------------");
    System.out.println("Tem certeza que deseja excluir sua conta?");
    System.out.println("1. Sim");
    System.out.println("2. Não");
    System.out.println("--------------------");
    if (sc.nextInt() == 1) {
      UsuarioCRUD usuarioCRUD = new UsuarioCRUD();
      usuarioCRUD.excluirCadastro(SessionManager.getUsuarioAutenticado());
      System.out.println("Conta excluída com sucesso!");
      System.exit(0);
      } else {
        menuUsuario();
      }
    sc.close();
  }
}
