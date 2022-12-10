package br.com.ada.agenda;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contato> contatos;

    public Agenda(){
        this.contatos = new ArrayList<>();
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public void adicionarContato() {

        String nome = EntradaDados.obterNome();

        String sobreNome = EntradaDados.obterSobrenome();

        String email = EntradaDados.obterEmail();

        String empresa = EntradaDados.obterEmpresa();

        Contato novoContato = new Contato(nome, sobreNome, empresa, email);

        if(!verificarContato(novoContato)){
            this.contatos.add(novoContato);
            System.out.println("\nContato adicionado com sucesso!\n");
        }
        else
            System.out.println("\nO contato já existe\n");
    }

    public boolean verificarContato(Contato novoContato){
        return this.contatos
                .stream().anyMatch(contato -> contato.equals(novoContato));
    }

    public void listarContatos() {

        Menu.exibirCabecalhoContatos();

        if(!this.contatos.isEmpty()){

            this.contatos.forEach(contato -> {
                System.out.printf("%-5s %-15s %-15s %-25s\n",
                        this.contatos.indexOf(contato) + 1,
                        contato.getNome(),
                        contato.getSobreNome(),
                        contato.getEmail());
            });

        } else {
            System.out.println("\nNão há contatos\n");
        }
    }

    public void buscarContato(){
        String contatoAPesquisar = EntradaDados.obterNomePesquisa();

        List<Contato> contatosFiltrados = this.contatos.stream()
                .filter(contato -> {
                    String nomeSobrenome = contato.getNome().toUpperCase().concat(contato.getSobreNome().toUpperCase());
                    return nomeSobrenome.contains(contatoAPesquisar.toUpperCase());
                })
                .toList();

        if (contatosFiltrados.isEmpty()){
            System.out.println("\nNenhum contato encontrado\n");
        } else{
            Menu.exibirCabecalhoContatos();
            contatosFiltrados.forEach(contato -> {
                System.out.printf("%-5s %-15s %-15s %-25s\n",
                        this.contatos.indexOf(contato) + 1,
                        contato.getNome(),
                        contato.getSobreNome(),
                        contato.getEmail());
            });
        }
    }

    public void removerContato(){
        if(contatos.isEmpty()){
            System.out.println("Não há contatos.");
        }else {
            listarContatos();
            System.out.print("Informe o número do ID do contato: ");
            int idContato = EntradaDados.obterNumeroInteiro();
            if(idContato > contatos.size() || idContato-1 < 0){
                System.out.println("Não existe nenhum contato com esse ID. Tente novamente!");
            }else {
                contatos.remove(idContato - 1);
                System.out.println("Contato removido!");
            }
        }

    }

    public void removerTodosContatos(){
        contatos.clear();
        System.out.println("Lista de contatos esvaziada.");
    }

    public void exibirInformacoesContato(){
        if(contatos.isEmpty()){
            System.out.println("Não há contatos.");
        }else {
            listarContatos();
            System.out.print("Informe o número do ID do contato: ");
            int idContato = EntradaDados.obterNumeroInteiro();
            System.out.println(getContatoPeloCodigo(idContato));
        }
    }

    public boolean verificarListaContatos(){
        return this.contatos.isEmpty();
    }

    public Contato getContatoPeloCodigo(int codigoContato) {
        if (codigoContato > contatos.size() || codigoContato - 1 < 0) {
            System.out.println("Não existe nenhum contato com esse ID. Tente novamente!");
        }
        try {
            Contato contato = this.contatos.get(codigoContato - 1);
            return contato;
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("ID não existe");
            return null;
        }
    }

    public void adicionarTelefone() {
        listarContatos();
        System.out.print("Informe o número do ID do contato: ");
        final var contactId = EntradaDados.obterNumeroInteiro();
        final var selectedContact = getContatoPeloCodigo(contactId);
        selectedContact.adicionaTelefone();
    }

    public void adicionarEndereco() {
        listarContatos();
        System.out.print("Informe o número do ID do contato: ");
        final var contactId = EntradaDados.obterNumeroInteiro();
        final var selectedContact = getContatoPeloCodigo(contactId);
        selectedContact.adicionaEndereco();
    }
}
