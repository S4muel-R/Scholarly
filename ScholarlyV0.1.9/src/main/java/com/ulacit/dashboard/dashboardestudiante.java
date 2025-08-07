/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulacit.dashboard;


import com.ulacit.chat.ChatGUI;
import com.ulacit.academico.ModuloAdministracionCursos;
import com.ulacit.calendario.calendario;
import com.ulacit.login.LoginApp;
import com.ulacit.Notificaciones.PanelAnuncios;


import java.awt.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/**
 *
 * @author sebas
 */

public class dashboardestudiante extends JFrame {
    private static moddashboardclass instance;

    public dashboardestudiante() {
        setTitle("MENU DASHBOARD");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior para notificaciones
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        Color fondoDashboard = new Color(81, 0, 87);
        topPanel.setBackground(fondoDashboard);


        // Panel principal con color
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(fondoDashboard);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        String[] botones = {"Cursos", "Chat", "Calendario", "Cerrar sesion"};

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
    
        public static moddashboardclass getInstance() {
        if (instance == null || !instance.isDisplayable()) {
            instance = new moddashboardclass();
        }
        return instance;
    }

    private void manejarClick(String opcion) {
        switch (opcion) {
            case "Cursos":
                new ModuloAdministracionCursos().showAdminCursosUI();
                dispose();
                break;
                
            case "Chat":
                new ChatGUI();
                dispose();
                break;
            case "Calendario":
                new calendario().showCalendarioUI();
                dispose();
                break;
            case "Cerrar sesion":
                SwingUtilities.invokeLater(() -> {
                    new LoginApp().setVisible(true); // Mostrar la ventana de login
                });
                this.dispose(); // Cierra solo el dashboard actual
                break;

        }
    }}
