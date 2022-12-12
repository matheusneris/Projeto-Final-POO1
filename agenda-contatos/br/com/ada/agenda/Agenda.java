package br.com.ada.agenda;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agenda {
    private List<Contato> contatos;
    private AddContactGui addContactGui;

    public Agenda() {
        this.contatos = new ArrayList<>();
        this.addContactGui = new AddContactGui();
    }

    public void showAddContactGui() {
        addContactGui.setVisible(true);
    }

    public void adicionarContato() {

        String nome = addContactGui.getTfNome().getText();
        String sobreNome = addContactGui.getTfSobrenome().getText();
        String email = addContactGui.getTfEmail().getText();
        String empresa = addContactGui.getTfEmpresa().getText();
        Contato novoContato = new Contato(nome, sobreNome, empresa, email);

        addContactGui.getTfNome().setText("");
        addContactGui.getTfSobrenome().setText("");
        addContactGui.getTfEmail().setText("");
        addContactGui.getTfEmpresa().setText("");


        if (!contatoJaExiste(novoContato)) {
            this.contatos.add(novoContato);
            System.out.println("\nContato adicionado com sucesso!\n");
        } else
            System.out.println("\nO contato já existe\n");
    }

    public boolean contatoJaExiste(Contato novoContato) {
        return this.contatos
                .stream().anyMatch(contato -> contato.equals(novoContato));
    }

    public void listarContatos() {
        JTextArea taListContact = new ListContactGui().getTaListContacts();

        if (!this.contatos.isEmpty()) {
            taListContact.append(String.format("%-5s %-15s %-15s %-25s\n", "ID", "Nome", "Sobrenome", "E-mail"));
            this.contatos.forEach(contato ->
                    taListContact.append(
                            String.format("%-5s %-15s %-15s %-25s\n",
                                    this.contatos.indexOf(contato) + 1,
                                    contato.getNome(),
                                    contato.getSobreNome(),
                                    contato.getEmail())
                    ));
        } else {
            taListContact.append("\nNão há contatos\n");
        }
    }

    public void buscarContato() {
        String contatoAPesquisar = EntradaDados.obterNomePesquisa();

        List<Contato> contatosFiltrados = this.contatos.stream()
                .filter(contato -> {
                    String nomeSobrenome = contato.getNome().toUpperCase().concat(contato.getSobreNome().toUpperCase());
                    return nomeSobrenome.contains(contatoAPesquisar.toUpperCase());
                })
                .toList();

        if (contatosFiltrados.isEmpty()) {
            System.out.println("\nNenhum contato encontrado\n");
        } else {
            Menu.exibirCabecalhoContatos();
            contatosFiltrados.forEach(contato -> System.out.printf("%-5s %-15s %-15s %-25s\n",
                    this.contatos.indexOf(contato) + 1,
                    contato.getNome(),
                    contato.getSobreNome(),
                    contato.getEmail()));
        }
    }

    public void removerContato() {
        if (contatos.isEmpty()) {
            System.out.println("Não há contatos.");
        } else {
            listarContatos();
            System.out.print("Informe o número do ID do contato: ");
            int idContato = EntradaDados.obterNumeroInteiro();
            if (idContato > contatos.size() || idContato - 1 < 0) {
                System.out.println("Não existe nenhum contato com esse ID. Tente novamente!");
            } else {
                contatos.remove(idContato - 1);
                System.out.println("Contato removido!");
            }
        }
    }

    public void removerTodosContatos() {
        contatos.clear();
        System.out.println("Lista de contatos esvaziada.");
    }

    public void exibirInformacoesContato() {
        if (contatos.isEmpty()) {
            System.out.println("Não há contatos.");
        } else {
            listarContatos();
            System.out.print("Informe o número do ID do contato: ");
            int idContato = EntradaDados.obterNumeroInteiro();
            final var contatoFound = getContatoPeloCodigo(idContato);
            if (contatoFound == null) {
                System.out.println("Não existe contato com esse ID");
            } else {
                System.out.println(contatoFound);
            }
        }
    }

    public boolean listaPossuiContatos() {
        return !this.contatos.isEmpty();
    }

    public void listarTelefone() {
        if (Menu.agenda.listaPossuiContatos()) {
            Menu.agenda.listarContatos();
            Menu.agenda.obterContato().listarTelefones();
        } else
            System.out.println("\nNão há contatos\n");
    }

    public void removerTelefone() {
        if (Menu.agenda.listaPossuiContatos()) {
            Menu.agenda.listarContatos();
            Contato contato = Menu.agenda.obterContato();
            if (!contato.listaTelefonesVazia()) {
                contato.listarTelefones();
                int idTelefone = EntradaDados.obterId(contato.getTelefones());
                Telefone telefone = contato.getTelefonePeloCodigo(idTelefone);
                contato.removerTelefone(telefone);
            } else {
                System.out.println("\nSem telefones cadastrados\n");
            }
        } else
            System.out.println("\nNão há contatos\n");
    }

    public Contato getContatoPeloCodigo(int codigoContato) {
        try {
            return this.contatos.get(codigoContato - 1);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("ID inválido");
            return null;
        }
    }

    public void adicionarTelefone() {
        listarContatos();
        System.out.print("Informe o número do ID do contato: ");
        final var idContato = EntradaDados.obterNumeroInteiro();

        final var contatoFound = getContatoPeloCodigo(idContato);
        if (contatoFound == null) {
            System.out.println("Não existe contato com esse ID");
        } else {
            contatoFound.adicionaTelefone();
        }
    }

    public void adicionarEndereco() {
        listarContatos();
        System.out.print("Informe o número do ID do contato: ");
        final var idContato = EntradaDados.obterNumeroInteiro();

        final var contatoFound = getContatoPeloCodigo(idContato);
        if (contatoFound == null) {
            System.out.println("Não existe contato com esse ID");
        } else {
            contatoFound.adicionaEndereco();
        }
    }

    public Contato obterContato() {

        int idContato = EntradaDados.obterId(this.contatos);
        return getContatoPeloCodigo(idContato);

    }

    public void exibirInformacoesTelefone() {

        if (Menu.agenda.listaPossuiContatos()) {
            Menu.agenda.listarContatos();
            Contato contato = Menu.agenda.obterContato();
            if (!contato.listaTelefonesVazia()) {
                contato.listarTelefones();
                int idTelefone = EntradaDados.obterId(contato.getTelefones());
                System.out.println(contato.getTelefonePeloCodigo(idTelefone));
            } else {
                System.out.println("\nSem telefones cadastrados\n");
            }
        } else
            System.out.println("\nNão há contatos\n");
    }

    public void removerEndereco() {
        if (Menu.agenda.listaPossuiContatos()) {
            Menu.agenda.listarContatos();
            Contato contato = Menu.agenda.obterContato();
            if (!contato.listaEnderecosVazia()) {
                contato.listarEnderecos();
                int idEndereco = EntradaDados.obterId(contato.getEnderecos());
                Endereco endereco = contato.getEnderecoPeloCodigo(idEndereco);
                contato.removerEndereco(endereco);
            } else {
                System.out.println("\nSem endereços cadastrados\n");
            }
        } else
            System.out.println("\nNão há contatos\n");

    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Contato> getContatos() {
        return Collections.unmodifiableList(contatos);
    }
}