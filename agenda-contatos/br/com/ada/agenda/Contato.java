package br.com.ada.agenda;

import java.util.*;
import java.util.stream.Collectors;

public class Contato {

    private String nome;
    private String sobreNome;
    private String empresa;
    private String email;
    private List<Telefone> telefones;

    private List<Endereco> enderecos;

    public Contato() {
    }

    public Contato(String nome, String sobreNome, String empresa, String email) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.empresa = empresa;
        this.email = email;
        this.telefones = new ArrayList<>();
        this.enderecos = new ArrayList<>();
    }

    public Contato(String nome, String sobreNome, String empresa, List<Telefone> telefones, List<Endereco> enderecos) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.empresa = empresa;
        this.telefones = telefones;
        this.enderecos = enderecos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Telefone> getTelefones() {
        return Collections.unmodifiableList(telefones);
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Endereco> getEnderecos() {
        return Collections.unmodifiableList(enderecos);
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(nome, contato.nome) && Objects.equals(sobreNome, contato.sobreNome) && Objects.equals(empresa, contato.empresa) && Objects.equals(email, contato.email) && Objects.equals(telefones, contato.telefones) && Objects.equals(enderecos, contato.enderecos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobreNome, empresa, email, telefones, enderecos);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("""
                            
                Nome: %s
                Sobrenome: %s
                Empresa: %s
                """, nome, sobreNome, empresa
        ));
        sb.append("Telefones:\n\n");
        telefones.forEach(telefones -> sb.append(telefones.toString()).append("\n"));
        sb.append("Endereços:\n\n");
        enderecos.forEach(enderecos -> sb.append(enderecos.toString()).append("\n"));
        return sb.toString();
    }

    public void listarTelefones() {
        if (verificarListaTelefones()) {
            System.out.println("\nSem Telefones cadastrados\n");
        } else {
            Menu.exibirCabecalhoTelefones();
            this.telefones.forEach(telefone -> {
                System.out.printf("%-5s %-20s %-15s %-15s %-15s\n",
                        this.telefones.indexOf(telefone) + 1,
                        telefone.getTipo(),
                        telefone.getDdi(),
                        telefone.getDdd(),
                        telefone.getNumero());
            });
        }
    }

    public boolean verificarListaTelefones() {
        return this.telefones.isEmpty();
    }

    public void addEndereco(Endereco novoEndereco) {
        if (verificaEnderecoExiste(novoEndereco)) {
            throw new RuntimeException("Endereço já cadastrado!");
        }
        this.enderecos.add(novoEndereco);
    }

    private boolean verificaEnderecoExiste(Endereco endereco) {
        return this.enderecos.stream()
                .anyMatch(enderecoCadastrado -> enderecoCadastrado.equals(endereco));
    }

    public void addTelefone(Telefone novoTelefone) {
        if (verificaTelefoneExiste(novoTelefone)) {
            throw new RuntimeException("Telefone já cadastrado!");
        }
        this.telefones.add(novoTelefone);
    }

    private boolean verificaTelefoneExiste(Telefone telefone) {
        return this.telefones.stream()
                .anyMatch(telefoneCadastrado -> telefoneCadastrado.equals(telefone));
    }

    public void adicionaTelefone() {
        List<TipoTelefone> tipoTelefones = Arrays.stream(TipoTelefone.values())
                .collect(Collectors.toList());

        String menuTipos = tipoTelefones.stream()
                .map(tipoTelefone -> String.format("%n%s - %s", tipoTelefone.ordinal() + 1, tipoTelefone.name()))
                .reduce("", String::concat);

        String tipoTelefone = EntradaDados.askSimpleInput(String.format("Tipo do Telefone%s", menuTipos));
        TipoTelefone tipo = tipoTelefones.get(Integer.parseInt(tipoTelefone) - 1);

        String ddi = EntradaDados.askSimpleInput("DDI do Telefone");
        String ddd = EntradaDados.askSimpleInput("DDD do Telefone");
        String numero = EntradaDados.askSimpleInput("Número do Telefone");

        Telefone telefone = new Telefone(tipo, ddi, ddd, numero);

        try {
            addTelefone(telefone);
        } catch (RuntimeException exception) {
            System.out.printf("Erro ao cadastrar: %s %n", exception.getMessage());
        }
    }

    public void adicionaEndereco() {
        List<TipoEndereco> tipoEnderecos = Arrays.stream(TipoEndereco.values())
                .collect(Collectors.toList());

        String menuTiposEnderecos = tipoEnderecos.stream()
                .map(tipoEndereco -> String.format("%n%s - %s", tipoEndereco.ordinal() + 1, tipoEndereco.name()))
                .reduce("", String::concat);

        String tipoEndereco = EntradaDados.askSimpleInput(String.format("Tipo do Endereço%s", menuTiposEnderecos));
        TipoEndereco enumTipoEndereco = tipoEnderecos.get(Integer.parseInt(tipoEndereco) - 1);


        String logradouro = EntradaDados.askSimpleInput("Logradouro do Endereço");
        String bairro = EntradaDados.askSimpleInput("Bairro do Endereço");
        String cep = EntradaDados.askSimpleInput("CEP do Endereço");
        String numero = EntradaDados.askSimpleInput("Número do Endereço");
        String complemento = EntradaDados.askSimpleInput("Complemento do Endereço");
        String cidade = EntradaDados.askSimpleInput("Cidade do Endereço");

        List<Estado> estados = Arrays.stream(Estado.values())
                .collect(Collectors.toList());

        String menuEstados = estados.stream()
                .map(estado -> String.format("%n%s - %s", estado.ordinal() + 1, estado.name()))
                .reduce("", String::concat);

        String estado = EntradaDados.askSimpleInput(String.format("Estado%s", menuEstados));
        Estado enumTipoEstado = estados.get(Integer.parseInt(estado) - 1);

        Endereco endereco = new Endereco(enumTipoEndereco, logradouro, bairro, cep, numero, complemento, cidade, enumTipoEstado);

        try {
            addEndereco(endereco);
        } catch (RuntimeException exception) {
            System.out.printf("Erro ao cadastrar: %s %n", exception.getMessage());
        }
    }

    public Telefone getTelefonePeloCodigo(int codigo){
        return this.telefones.get(codigo-1);
    }

    public void removerTelefone(Telefone telefone){
        this.telefones.remove(telefone);
        System.out.println("\nTelefone removido com sucesso\n");
    }
}
