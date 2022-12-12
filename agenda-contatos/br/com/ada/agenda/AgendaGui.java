package br.com.ada.agenda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AgendaGui extends JFrame {
    private Agenda agenda;
    private JPanel jpMain;
    private JButton btnExit;
    private JButton btnOpt01;
    private JButton btnOpt02;
    private JButton btnOpt03;
    private JButton btnOpt04;
    private JButton btnOpt05;
    private JButton btnOpt06;
    private JButton btnOpt07;
    private JButton btnOpt08;
    private JButton btnOpt09;
    private JButton btnOpt10;
    private JButton btnOpt11;
    private JButton btnOpt12;
    private JButton btnOpt13;
    private JButton btnOpt14;

    private JLabel lblOpt1;
    private JLabel lblOpt2;
    private JLabel lblOpt3;
    private JLabel lblOpt4;
    private JLabel lblOpt5;
    private JLabel lblOpt6;
    private JLabel lblOpt7;
    private JLabel lblOpt8;
    private JLabel lblOpt9;
    private JLabel lblOpt10;
    private JLabel lblOpt11;
    private JLabel lblOpt12;
    private JLabel lblOpt13;
    private JLabel lblOpt14;
    private JLabel lblTitle;

    private List<JLabel> labels = List.of(
            lblOpt1,
            lblOpt2,
            lblOpt3,
            lblOpt4,
            lblOpt5,
            lblOpt6,
            lblOpt7,
            lblOpt8,
            lblOpt9,
            lblOpt10,
            lblOpt11,
            lblOpt12,
            lblOpt13,
            lblOpt14
    );

    private List<String> labelsText = List.of(
            "Adicionar Contato",
            "Listar Contato",
            "Buscar um Contato",
            "Remover um Contato",
            "Remover todos os Contatos",
            "Adicionar um telefone a um Contato",
            "Adicionar um endereço a um Contato",
            "Remover um telefone de um Contato",
            "Remover um endereço de um Contato",
            "Exibir todas as informações de um Contato",
            "Listar todos os telefones de um Contato",
            "Listar todos os endereços de um Contato",
            "Exibir todas as informações de um telefone de um Contato",
            "Exibir todas as informações de um endereço de um Contato"
    );


    public Agenda getAgenda() {
        return agenda;
    }

    public AgendaGui() {
        agenda = new Agenda();

        setContentPane(jpMain);
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        int i = 0;
        for (JLabel label : labels) {
            label.setText(labelsText.get(i));
            i++;
        }

        setVisible(true);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnOpt01.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                agenda.showAddContactGui();
            }
        });

        btnOpt02.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                agenda.listarContatos();
            }
        });


    }
}
