package com.ti2cc;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        DAO dao = new DAO();
        Scanner scanner = new Scanner(System.in);
        
        // Conectar ao banco de dados
        if (dao.conectar()) {
            System.out.println("Conexão com o banco de dados realizada com sucesso!");
        } else {
            System.out.println("Erro ao conectar com o banco de dados.");
            return;
        }

        boolean sair = false;
        while (!sair) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1) Listar usuários");
            System.out.println("2) Inserir usuário");
            System.out.println("3) Excluir usuário");
            System.out.println("4) Atualizar usuário");
            System.out.println("5) Sair");
            
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha em branco após o número

            switch (opcao) {
                case 1:
                    // Listar usuários
                    System.out.println("==== Mostrar usuários === ");
                    Usuario[] usuarios = dao.getUsuarios();
                    if (usuarios != null && usuarios.length > 0) {
                        for (Usuario usuario : usuarios) {
                            System.out.println(usuario.toString());
                        }
                    } else {
                        System.out.println("Nenhum usuário encontrado.");
                    }
                    break;

                case 2:
                    // Inserir usuário
                    System.out.println("Inserir usuário:");
                    System.out.print("Código: ");
                    int codigoInserir = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha em branco
                    System.out.print("Login: ");
                    String loginInserir = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senhaInserir = scanner.nextLine();
                    System.out.print("Sexo (M/F): ");
                    char sexoInserir = scanner.nextLine().charAt(0);
                    
                    Usuario usuarioInserir = new Usuario(codigoInserir, loginInserir, senhaInserir, sexoInserir);
                    if (dao.inserirUsuario(usuarioInserir)) {
                        System.out.println("Usuário inserido com sucesso!");
                    } else {
                        System.out.println("Erro ao inserir usuário.");
                    }
                    break;

                case 3:
                    // Excluir usuário
                    System.out.println("Excluir usuário:");
                    System.out.print("Código do usuário a excluir: ");
                    int codigoExcluir = scanner.nextInt();
                    
                    if (dao.excluirUsuario(codigoExcluir)) {
                        System.out.println("Usuário excluído com sucesso!");
                    } else {
                        System.out.println("Erro ao excluir usuário.");
                    }
                    break;

                case 4:
                    // Atualizar usuário
                    System.out.println("Atualizar usuário:");
                    System.out.print("Código do usuário a atualizar: ");
                    int codigoAtualizar = scanner.nextInt();
                    scanner.nextLine();  // Consumir a linha em branco
                    System.out.print("Novo login: ");
                    String loginAtualizar = scanner.nextLine();
                    System.out.print("Nova senha: ");
                    String senhaAtualizar = scanner.nextLine();
                    System.out.print("Novo sexo (M/F): ");
                    char sexoAtualizar = scanner.nextLine().charAt(0);

                    Usuario usuarioAtualizar = new Usuario(codigoAtualizar, loginAtualizar, senhaAtualizar, sexoAtualizar);
                    if (dao.atualizarUsuario(usuarioAtualizar)) {
                        System.out.println("Usuário atualizado com sucesso!");
                    } else {
                        System.out.println("Erro ao atualizar usuário.");
                    }
                    break;

                case 5:
                    // Sair
                    System.out.println("Saindo...");
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }

        // Fechar a conexão
        dao.close();
        scanner.close();
    }
}
