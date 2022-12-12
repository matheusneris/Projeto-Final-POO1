package br.com.ada.agenda;


import java.util.Objects;

public class Telefone {

    public TipoTelefone tipo;
    public String ddi;
    public String ddd;
    public String numero;

    public Telefone(TipoTelefone tipo, String ddi, String ddd, String numero){
        this.tipo = tipo;
        this.ddi = ddi;
        this.ddd = ddd;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return String.format("""               
                    Tipo: %s
                    DDI %s
                    DDD %s
                    Numero %s
                """, tipo, ddi, ddd, numero)
                ;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public String getDdi() {
        return ddi;
    }

    public String getDdd() {
        return ddd;
    }

    public String getNumero() {
        return numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone telefone = (Telefone) o;
        return tipo == telefone.tipo && ddi.equals(telefone.ddi) && ddd.equals(telefone.ddd) && numero.equals(telefone.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo, ddi, ddd, numero);
    }
}
