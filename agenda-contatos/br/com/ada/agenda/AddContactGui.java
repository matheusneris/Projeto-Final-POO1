package br.com.ada.agenda;

import br.com.ada.MainGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddContactGui extends JFrame {
    private JPanel pnlAddContact;
    private JLabel lblAddContact;
    private JTextField tfNome;
    private JTextField tfSobrenome;
    private JTextField tfEmail;
    private JTextField tfEmpresa;
    private JButton btnSalvar;
    private JButton btnVoltar;
    private JLabel lblStatus;

    public JTextField getTfNome() {
        return tfNome;
    }

    public JTextField getTfSobrenome() {
        return tfSobrenome;
    }

    public JTextField getTfEmail() {
        return tfEmail;
    }    public JTextField getTfEmpresa() {
        return tfEmpresa;
    }

    public AddContactGui() {


        setContentPane(pnlAddContact);
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setVisible(false);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGui.agendaGui.setVisible(true);
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGui.agendaGui.getAgenda().adicionarContato();
                dispose();
                MainGui.agendaGui.setVisible(true);
            }
        });

    }


}
