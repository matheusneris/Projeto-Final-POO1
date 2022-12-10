package br.com.ada.agenda;

import java.util.Scanner;

public class EntradaDados {

    static Scanner entrada = new Scanner(System.in);

    public static String obterOpcao() {
        return entrada.nextLine();
    }

    public static String continuarNoPrograma() {
        while (true) {
            System.out.println("\nDeseja continuar? 1 - Sim, 2 - Não\n");
            String resposta = entrada.nextLine();
            if (resposta.equals("1") || resposta.equals("2")) {
                return resposta;
            } else {
                System.out.println("\nDigite apenas 1 ou 2\n");
            }
        }
    }

    public static int obterNumeroInteiro() {
        while (true) {
            try {
                return Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Informe apenas números inteiros");
            }
        }
    }

    public static String obterNome() {
        System.out.print("\nDigite o nome do contato: ");
        return entrada.nextLine();
    }

    public static String obterSobrenome() {
        System.out.print("\nDigite o sobrenome do contato: ");
        return entrada.nextLine();
    }

    public static String obterEmail() {
        System.out.print("\nDigite o email: ");
        return entrada.nextLine();
    }

    public static String obterEmpresa() {
        System.out.print("\nDigite o nome da empresa: ");
        return entrada.nextLine();
    }

    public static String obterNomePesquisa() {
        System.out.print("\nDigite um nome a pesquisar: ");
        return entrada.nextLine();
    }

    public static String askSimpleInput(String message) {
        System.out.printf("%s%n> ", message);
        return entrada.nextLine().trim();
    }
}
