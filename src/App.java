import classes.*;
import helpers.auth;
import recursos.UsuarioCRUD;

import java.util.Scanner;

import java.util.HashMap;
import java.util.Map;
import helpers.Menu;

public class App {
  public static void main(String[] args) throws Exception {

    Scanner sc = new Scanner(System.in);
    Menu.limparTela();
    System.out.println("Digite o tipo de operação: ");
    System.out.println("1. Login");
    System.out.println("2. Cadastro");
    switch (sc.nextInt()) {
      case 1:
        Menu.limparTela();
        loginUsuario();
        break;
      case 2:
        Menu.limparTela();
        cadastroUsuario();
        break;
    }
    sc.close();
  }

  public static void cadastroUsuario() {
    Scanner sc = new Scanner(System.in);
    Usuario usuario = new Usuario();
    System.out.println("Digite seu nome completo: ");
    usuario.setNome(sc.nextLine());
    System.out.println("Digite seu CPF: ");
    usuario.setCpf(sc.nextLine());
    System.out.println("Digite seu email: ");
    usuario.setEmail(sc.nextLine());
    System.out.println("Digite sua senha: ");
    usuario.setSenha(sc.nextLine());
    System.out.println("Digite seu tipo de usuário: ");
    System.out.println("1. Paciente");
    System.out.println("2. Familiar");
    System.out.println("3. Ouvidor");
    Map<Integer, Usuario.tipoUsuario> tipoUsuarioMap = new HashMap<>();
    tipoUsuarioMap.put(1, Usuario.tipoUsuario.PACIENTE);
    tipoUsuarioMap.put(2, Usuario.tipoUsuario.FAMILIAR);
    tipoUsuarioMap.put(3, Usuario.tipoUsuario.OUVIDOR);
    int tipoInput = sc.nextInt();
    Usuario.tipoUsuario tipoUsuario = tipoUsuarioMap.get(tipoInput);
    if (tipoUsuario == null) {
      System.out.println("Opção inválida.");
      cadastroUsuario();
      sc.close();
      return;
    }
    usuario.setTipo(tipoUsuario);
    UsuarioCRUD usuarioCRUD = new UsuarioCRUD();
    usuarioCRUD.cadastrarUsuario(usuario);
    System.out.println("Usuário criado com sucesso! Faça login para continuar.");
    sc.close();
  }
  
  
  
  public static void loginUsuario() {
    Scanner sc = new Scanner(System.in); // não tenho ideia de como fechar isso sem zoar os outros scanners
    Usuario usuario = new Usuario();
    System.out.println("Digite seu email: ");
    usuario.setEmail(sc.nextLine());
    System.out.println("Digite sua senha: ");
    usuario.setSenha(sc.nextLine());
    auth.autenticar(usuario);
    if (auth.autenticar(usuario)) {
      System.out.println("Login realizado com sucesso!");
      switch (usuario.getTipo()) {
        case PACIENTE:
          Menu.menuUsuario();
          break;
        case FAMILIAR:
          Menu.menuUsuario();
          break;
        case OUVIDOR:
          Menu.menuOuvidor();
          break;
        default:
          System.out.println("Erro");
          break;
      }
    } else {
      Menu.limparTela();
      System.out.println("Email ou senha incorretos.");
      System.out.println("Tente novamente.");
      loginUsuario();
    }
  }

}
