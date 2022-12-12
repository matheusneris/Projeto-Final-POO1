package br.com.ada.agenda;

import java.util.Objects;

public class Endereco {

    private final TipoEndereco tipo;
    private final String logradouro;
    private final String bairro;
    private final String cep;
    private final String numero;
    private final String complemento;
    private final String cidade;
    private final Estado uf;

    public Endereco(TipoEndereco tipo, String logradouro, String bairro, String cep, String numero, String complemento, String cidade, Estado uf) {
        this.tipo = tipo;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.uf = uf;
    }

    public TipoEndereco getTipo() {
        return tipo;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Estado getUf() {
        return uf;
    }

    @Override
    public String toString() {
        return String.format("""
                Tipo: %s
                Cep: %s
                UF: %s
                Cidade: %s
                Bairro: %s
                Logradouro: %s
                Número: %s
                Complemento: %s
            """, tipo, cep, uf, cidade, bairro, logradouro, numero, complemento)
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return tipo == endereco.tipo && logradouro.equals(endereco.logradouro) && bairro.equals(endereco.bairro) && cep.equals(endereco.cep) && numero.equals(endereco.numero) && Objects.equals(complemento, endereco.complemento) && cidade.equals(endereco.cidade) && uf == endereco.uf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, logradouro, bairro, cep, numero, complemento, cidade, uf);
    }
}
