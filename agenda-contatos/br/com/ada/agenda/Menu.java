package br.com.ada.agenda;

import java.util.Scanner;

public class Menu {
    static Agenda agenda = new Agenda();

    public static void iniciarMenu(){

        String continuar = "";

        do {
            System.out.println("""
                ####### AGENDA CONTATOS #########
                
                Escolha uma opção:
                
                1 - Adicionar Contato
                2 - Listar Contato
                3 - Buscar um Contato
                4 - Remover um Contato
                5 - Remover todos os Contatos
                6 - Adicionar um telefone a um Contato
                7 - Adicionar um endereço a um Contato
                8 - Remover um telefone de um Contato
                9 - Remover um endereço de um Contato
                10 - Exibir todas as informações de um Contato
                11 - Listar todos os telefones de um Contato
                12 - Listar todos os endereços de um Contato
                13 - Exibir todas as informações de um telefone de um Contato
                14 - Exibir todas as informações de um endereço de um Contato
                
                """);

            Menu.direcionarOpcao(EntradaDados.obterOpcao());

            continuar = EntradaDados.continuarNoPrograma();

        }while(continuar.equals("1"));
    }

    public static void direcionarOpcao(String opcao){

        switch (opcao){
            case "1" -> agenda.adicionarContato();
            case "2" -> agenda.listarContatos();
            case "3" -> agenda.buscarContato();
            case "4" -> agenda.removerContato();
            case "5" -> agenda.removerTodosContatos();
            case "6" -> agenda.adicionarContato();
            case "7" -> agenda.adicionarContato();
            case "8" -> agenda.adicionarContato();
            case "9" -> agenda.adicionarContato();
            case "10" -> agenda.exibirInformacoesContato();
            case "11" -> {
                if(!agenda.verificarListaContatos()){
                    agenda.listarContatos();
                    System.out.print("\nDigite o id do contato: \n");
                    int idContato = EntradaDados.obterNumeroInteiro();
                    Contato contato = agenda.getContatoPeloCodigo(idContato);
                    contato.listarTelefones();
                } else
                    System.out.println("\nNão há contatos cadastrados\n");
            }
            case "12" -> agenda.adicionarContato();
            case "13" -> agenda.adicionarContato();
            case "14" -> agenda.adicionarContato();
            default -> System.out.println("Opção inválida!\n");
        }
    }

    public static void exibirCabecalhoContatos(){
        System.out.printf("%-5s %-15s %-15s %-25s\n", "ID", "Nome", "Sobrenome", "E-mail");
    }

    public static void exibirCabecalhoTelefones(){
        System.out.printf("%-5s %-20s %-15s %-15s %-15s\n", "ID", "Tipo Telefone", "DDI", "DDD", "Numero");
    }
}
