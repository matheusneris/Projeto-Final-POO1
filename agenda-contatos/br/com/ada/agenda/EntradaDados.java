package br.com.ada.agenda;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static int obterId(List<?> lista){

        while (true){
            int id = -1;
            try{
                System.out.print("\nDigite o id: ");
                id = entrada.nextInt();
            } catch (InputMismatchException exception){
                System.out.println("\nDigite apenas números\n");
            } finally {
                entrada.nextLine();
            }
            if (id < 1 || id > lista.size()){
                System.out.println("Id inválido");
            } else {
                return id;
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
        while (true) {
            System.out.print("\nDigite o email: ");
            String emailDigitado = entrada.nextLine();
            boolean valido = validarEmail(emailDigitado);
            if(valido){
                return emailDigitado;
            } else {
                System.out.println("Email informado é inválido. Digite novamente.");
            }
        }
    }

    public static boolean validarEmail(String email){
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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

    public static void encerrarPrograma(){
        entrada.close();
        System.out.println("\nFim do programa!\n");
        System.exit(0);
    }
}
