package br.com.ada.agenda;

import br.com.ada.MainGui;

import javax.swing.*;
import java.awt.event.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class AddContactGui extends JFrame {
    private JPanel pnlAddContact;
    private JTextField tfNome;
    private JTextField tfSobrenome;
    private JTextField tfEmail;
    private JTextField tfEmpresa;
    private JButton btnSalvar;
    private JButton btnVoltar;

    public AddContactGui(Agenda agenda) {

        setContentPane(pnlAddContact);
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        btnVoltar.addActionListener(e -> {
            dispose();
            MainGui.agendaGui.setVisible(true);
        });

        btnSalvar.addActionListener(e -> {
            if (EntradaDados.validarEmail(tfEmail.getText())) {
                showMessageDialog(null,
                        agenda.saveContact(),
                        "Contato Salvo",
                        JOptionPane.PLAIN_MESSAGE);
            } else if (tfNome.getText().equals("") ||
                    tfSobrenome.getText().equals("") ||
                    tfEmpresa.getText().equals("") ||
                    tfEmail.getText().equals("")
            ) {
                showMessageDialog(null,
                        "Campos não podem ser vazios",
                        "Erro ao Salvar",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                showMessageDialog(null,
                        "formato de email inválido",
                        "Erro ao Salvar",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JTextField getTfNome() {
        return tfNome;
    }

    public JTextField getTfSobrenome() {
        return tfSobrenome;
    }

    public JTextField getTfEmail() {
        return tfEmail;
    }

    public JTextField getTfEmpresa() {
        return tfEmpresa;
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            dispose();
            MainGui.agendaGui.setVisible(true);
        }
    }
}
