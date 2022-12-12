package br.com.ada.agenda;

import br.com.ada.MainGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListContactGui extends JFrame {
    private JPanel pnlListContact;
    private JButton btnVoltar;
    private JLabel lblTitle;
    private JTextArea taListContacts;




    public JTextArea getTaListContacts() {
        return taListContacts;
    }

    public ListContactGui() {

        setContentPane(pnlListContact);
        setSize(900, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGui.agendaGui.setVisible(true);
            }
        });
    }
}
