package com.movieticketbooking;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MovieTicketFrame().setVisible(true));
    }
}