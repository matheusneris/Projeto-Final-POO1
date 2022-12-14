package br.com.ada.agenda;

import br.com.ada.MainGui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class ListContactGui extends JFrame {
    private JPanel pnlListContact;
    private JButton btnVoltar;
    private JButton btnNext;
    private JButton btnPrevious;
    private JLabel lblPage;
    private JTable tblData;

    private final Agenda agenda;
    private final List<Contato> contatos;
    List<List<Contato>> pages;
    private int pageNumber;


    public ListContactGui(Agenda agenda, List<Contato> contatos) {
        setContentPane(pnlListContact);
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        this.agenda = agenda;
        this.contatos = contatos;
        loadData();

        btnVoltar.addActionListener(e -> {
            dispose();
            MainGui.agendaGui.setVisible(true);
        });
        btnPrevious.addActionListener(e -> {
            if (pageNumber > 0) showTable(--pageNumber);
        });
        btnNext.addActionListener(e -> {
            if (pageNumber < pages.size() - 1) showTable(++pageNumber);
        });
    }

    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            dispose();
            MainGui.agendaGui.setVisible(true);
        }
    }

    private void loadData() {
        int PAGE_SIZE = 5;
        pages = agenda.getListPages(contatos, PAGE_SIZE);
        if (pages.isEmpty()) {
            showMessageDialog(null,
                    "Lista está vazia",
                    "Erro ao Listar",
                    JOptionPane.ERROR_MESSAGE);
            dispose();
            MainGui.agendaGui.setVisible(true);
        }
        pageNumber = 0;
        showTable(pageNumber);
    }

    private void showTable(int pageNumber) {

        if (pageNumber >= 0 && pageNumber < pages.size()) {
            lblPage.setText((pageNumber + 1) + "/" + pages.size());
            String[] header = new String[]{"ID", "Nome", "Sobrenome", "E-mail"};
            String[][] data = pages.get(pageNumber).stream()
                    .map(contato -> new String[]{
                            String.valueOf(contatos.indexOf(contato) + 1),
                            contato.getNome(),
                            contato.getSobreNome(),
                            contato.getEmail()
                    })
                    .toArray(String[][]::new);

            DefaultTableModel model = new DefaultTableModel(data, header);
            tblData.setModel(model);

            JTableHeader tableHeader = tblData.getTableHeader();
            tableHeader.setBackground(Color.lightGray);
            Font fontHeader = new Font("FiraCode Nerd Font", Font.PLAIN, 14);
            tableHeader.setFont(fontHeader);
            tblData.getColumnModel().getColumn(0).setMaxWidth(50);
        }
    }
}
