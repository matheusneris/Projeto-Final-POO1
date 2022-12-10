package br.com.ada.agenda;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Arquivo {

    private static Path fileName = Path.of("banco_dados_agenda.txt");
    private static List<String> dadosGravarFile = new ArrayList<>();
    private static List<String> dadosLidosFile = new ArrayList<>();

    private static boolean verificarArquivo() {
        return Files.exists(fileName);
    }

    public static void obterDadosArquivo(){
        if (!verificarArquivo()){
            System.out.println("\nEsse arquivo não existe\n");
        } else{
            try{
                dadosLidosFile =  Files.readAllLines(fileName);
            } catch (IOException exception){
                exception.printStackTrace();
            }

        }
    }

    public static List<Contato> atualizarAgenda(){
        List<Contato> contatos = new ArrayList<>();
        Contato contato = new Contato();
        List<Telefone> telefones = new ArrayList<>();
        List<Endereco> enderecos = new ArrayList<>();
        for (String linha : dadosLidosFile) {

            if(linha.startsWith("C")){
                String[] dadosContato = linha.split(";");
                String nome = dadosContato[1];
                String sobrenome = dadosContato[2];
                String empresa = dadosContato[3];
                String email = dadosContato[3];
                contato = new Contato(nome, sobrenome,empresa,email);
            }
            if(linha.startsWith("T")){
                String[] dadosTelefone = linha.split(";");
                TipoTelefone tipoTelefone = TipoTelefone.valueOf(dadosTelefone[1]);
                String ddi = dadosTelefone[2];
                String ddd = dadosTelefone[3];
                String numero = dadosTelefone[4];
                Telefone telefone = new Telefone(tipoTelefone,ddi,ddd,numero);
                telefones.add(telefone);
            }
            if(linha.startsWith("E")){
                String[] dadosEndereco = linha.split(";");
                TipoEndereco tipoEndereco = TipoEndereco.valueOf(dadosEndereco[1]);
                String logradouro = dadosEndereco[2];
                String bairro = dadosEndereco[3];
                String cep = dadosEndereco[4];
                String numeroEndereco = dadosEndereco[5];
                String complemento = dadosEndereco[6];
                String cidade = dadosEndereco[7];
                Estado estado = Estado.valueOf(dadosEndereco[8]);
                Endereco endereco = new Endereco(tipoEndereco,logradouro,bairro,cep,numeroEndereco,complemento,cidade,estado);
                enderecos.add(endereco);
            }
            if(linha.equals(";")){
                contato.setTelefones(telefones);
                contato.setEnderecos(enderecos);
                contatos.add(contato);
                telefones = new ArrayList<>();
                enderecos = new ArrayList<>();
            }

        }

        return contatos;
    }

    public static void dadosAGravarArquivo(Agenda agenda){
        List<Contato> contatos = agenda.getContatos();
        List<String> dados = new ArrayList<>();

        for (Contato contato : contatos){
            String dadoContato =
                    "C;" +
                            contato.getNome() + ";" +
                            contato.getSobreNome() + ";" +
                            contato.getEmpresa() + ";" +
                            contato.getEmail();
            List<Telefone> telefones = contato.getTelefones();
            dadosGravarFile.add(dadoContato);

            if(!telefones.isEmpty())
                for (Telefone telefone : telefones){
                    String dadoTelefone =
                            "T;" +
                                    telefone.getTipo() + ";" +
                                    telefone.getDdi() + ";" +
                                    telefone.getDdd() + ";" +
                                    telefone.getNumero();
                    dadosGravarFile.add(dadoTelefone);
                }

            List<Endereco> enderecos = contato.getEnderecos();
            if(!enderecos.isEmpty())
                for (Endereco endereco : enderecos){
                    String dadoEndereco =
                            "E;" +
                                    endereco.getTipo() + ";" +
                                    endereco.getLogradouro() + ";" +
                                    endereco.getBairro() + ";" +
                                    endereco.getCep() + ";" +
                                    endereco.getNumero() + ";" +
                                    endereco.getComplemento() + ";" +
                                    endereco.getCidade() + ";" +
                                    endereco.getUf();
                    dadosGravarFile.add(dadoEndereco);
                }

            dadosGravarFile.add(";");

        }

    }

    public static void salvarArquivo(Agenda agenda) {
        try {
            dadosGravarFile.clear();
            dadosAGravarArquivo(agenda);
            Files.write(fileName, dadosGravarFile);

        } catch (FileAlreadyExistsException ex) {
            System.out.println("File já existe");
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }





}
