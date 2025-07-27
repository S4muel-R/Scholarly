/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulacit.dashboard;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author sebas
 */

public class moddashboardclass extends JFrame {

    public moddashboardclass() {
        setTitle("MENU DASHBOARD");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal con color
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(81, 0, 87));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        String[] botones = {"Cursos", "Anuncios", "Chat", "Calendario", "Cerrar sesión"};

        for (String texto : botones) {
            JButton boton = new JButton(texto);
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            boton.setBackground(Color.WHITE);
            boton.setFont(new Font("Segoe UI", Font.PLAIN, 16));

            // Acción al hacer clic
            boton.addActionListener(e -> manejarClick(texto));
            mainPanel.add(Box.createVerticalStrut(20));  // Espaciado
            mainPanel.add(boton);
        }

        add(mainPanel, BorderLayout.CENTER);
    }

    private void manejarClick(String opcion) {
        switch (opcion) {
            case "Cursos":
                JOptionPane.showMessageDialog(this, "Abriendo el modulo cursos");
                break;
            case "Anuncios":
                JOptionPane.showMessageDialog(this, "Abriendo el modulo nuncios");
                break;
            case "Chat":
                JOptionPane.showMessageDialog(this, "Abriendo el modulo Chat");
                break;
            case "Calendario":
                JOptionPane.showMessageDialog(this, "Abriendo el modulo Calendario");
                break;
            case "Cerrar sesión":
                JOptionPane.showMessageDialog(this, "Sesion terminada");
                dispose(); // Cierra esta ventana
                break;
        }
    }}

  

