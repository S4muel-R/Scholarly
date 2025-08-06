/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ulacit.chat;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Anderson M
 */
public class Mensaje_Fijado extends JFrame {
    public static String mensajeFijado = "No hay mensajes fijados"; 

    public Mensaje_Fijado() {
        setTitle("SCHOLARLY - Chat con Mensaje Fijado");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Lateral (simulación de tu menú)
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(new Color(102, 0, 102));
        sidePanel.setPreferredSize(new Dimension(200, 600));
        mainPanel.add(sidePanel, BorderLayout.WEST);

        // Panel central (chat)
        Pag1 chatPanel = new Pag1(); 
        chatPanel.setSize(600, 600);
        chatPanel.setLocation(0, 0);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(chatPanel, BorderLayout.CENTER);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Mensaje_Fijado().setVisible(true));
    }
}