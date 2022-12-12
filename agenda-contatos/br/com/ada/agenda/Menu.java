package br.com.ada.agenda;


public class Menu {
    static Agenda agenda = new Agenda();
    private static boolean salvar;

    public static void iniciarMenu() {

        //Arquivo.obterDadosArquivo();
        //agenda.setContatos(Arquivo.atualizarAgenda());

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
                    15 - Exibir a lista de contatos com paginação
                    16 - Exibir a lista de telefones com paginação
                    17 - Exibir a lista de endereços com paginação
                    18 - Exportar dados para um arquivo texto
                    19 - Importar dados de um arquivo texto
                    20 - Importar e salvar os dados automaticamente 
                    0 - Encerrar programa
                    
                    """);
            System.out.printf(">");

            Menu.direcionarOpcao(EntradaDados.obterOpcao());

            if(salvar)
                Arquivo.salvarArquivo(agenda);

            continuar = EntradaDados.continuarNoPrograma();

        } while (continuar.equals("1"));

        EntradaDados.encerrarPrograma();
    }

    public static void direcionarOpcao(String opcao) {

        switch (opcao) {
            case "0" -> EntradaDados.encerrarPrograma();
            case "1" -> agenda.adicionarContato();
            case "2" -> agenda.listarContatos();
            case "3" -> agenda.buscarContato();
            case "4" -> agenda.removerContato();
            case "5" -> agenda.removerTodosContatos();
            case "6" -> agenda.adicionarTelefone();
            case "7" -> agenda.adicionarEndereco();
            case "8" -> agenda.removerTelefone();
            case "9" -> agenda.removerEndereco();
            case "10" -> agenda.exibirInformacoesContato();
            case "11" -> agenda.listarTelefone();
            case "12" -> agenda.listarEndereco();
            case "13" -> agenda.exibirInformacoesTelefone();
            case "14" -> agenda.exibirInformacoesEndereco();
            case "15" -> {}
            case "16" -> {}
            case "17" -> {}
            case "18" -> {
                Arquivo.atualizarNomeArquivo();
                Arquivo.salvarArquivo(agenda);
            }
            case "19" -> {
                Arquivo.atualizarNomeArquivo();
                Arquivo.obterDadosArquivo();
                agenda.setContatos(Arquivo.atualizarAgenda());
            }
            case "20" ->{
                Arquivo.atualizarNomeArquivo();
                Arquivo.obterDadosArquivo();
                agenda.setContatos(Arquivo.atualizarAgenda());
                salvar = true;
            }
            default -> System.out.println("Opção inválida!\n");
        }
    }

    public static void exibirCabecalhoContatos() {
        System.out.printf("%-5s %-15s %-15s %-25s\n", "ID", "Nome", "Sobrenome", "E-mail");
    }

    public static void exibirCabecalhoTelefones() {
        System.out.printf("%-5s %-15s %-15s\n", "ID", "DDD", "Numero");
    }

    public static void exibirCabecalhoEnderecos() {
        System.out.printf("%-5s %-20s %-20s %-10s %-20s %-5s\n",
                "ID",  "Logradouro",  "Bairro ",  "Número",  "Cidade ",  "UF");
    }

}
