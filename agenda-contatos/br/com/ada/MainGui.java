package br.com.ada;

import br.com.ada.agenda.AgendaGui;

import javax.swing.*;

public class MainGui {
    public static AgendaGui agendaGui;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    private static void createGUI() {
        agendaGui = new AgendaGui();
    }
}
