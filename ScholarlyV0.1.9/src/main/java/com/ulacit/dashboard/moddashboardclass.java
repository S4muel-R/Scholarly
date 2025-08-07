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
import com.ulacit.tema.Tema;

import java.awt.*;
import javax.swing.*;

public class moddashboardclass extends JFrame {
    private static moddashboardclass instance;
    private JPanel mainPanel;
    private JPanel topPanel;

    public moddashboardclass() {
        setTitle("MENU DASHBOARD");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con dos subpaneles: izq (notificaciones, perfil), der (tema)
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Tema.getColorFondo());

        // Panel izquierdo (notificaciones y perfil)
        JPanel panelIzq = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelIzq.setOpaque(false);
        JButton btnNotificaciones = new JButton("Notificaciones");
        btnNotificaciones.setPreferredSize(new Dimension(130, 28));
        btnNotificaciones.setToolTipText("Notificaciones");
        btnNotificaciones.setFocusable(false);
        btnNotificaciones.setMargin(new Insets(2, 10, 2, 10));
        btnNotificaciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnNotificaciones.setBackground(Color.WHITE);
        btnNotificaciones.addActionListener(e -> {
            JFrame frame = new JFrame("Gestión de Anuncios");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 500);
            frame.setLocationRelativeTo(this);
            String usuario = "admin";
            String cursoId = "CURSO-TEST";
            frame.setContentPane(new PanelAnuncios(usuario, cursoId));
            frame.setVisible(true);
        });

        JButton btnEditarPerfil = new JButton("Editar Perfil");
        btnEditarPerfil.setPreferredSize(new Dimension(130, 28));
        btnEditarPerfil.setToolTipText("Editar Perfil");
        btnEditarPerfil.setFocusable(false);
        btnEditarPerfil.setMargin(new Insets(2, 10, 2, 10));
        btnEditarPerfil.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnEditarPerfil.setBackground(Color.WHITE);
        btnEditarPerfil.addActionListener(e -> {
            new com.ulacit.editarPerfil.editarPerfil().setVisible(true);
        });

        panelIzq.add(btnNotificaciones);
        panelIzq.add(btnEditarPerfil);

        // Panel derecho (cambiar tema)
        JPanel panelDer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panelDer.setOpaque(false);
        JButton btnCambiarTema = new JButton("Cambiar Tema");
        btnCambiarTema.setPreferredSize(new Dimension(130, 28));
        btnCambiarTema.setToolTipText("Cambiar Tema");
        btnCambiarTema.setFocusable(false);
        btnCambiarTema.setMargin(new Insets(2, 10, 2, 10));
        btnCambiarTema.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnCambiarTema.setBackground(Color.WHITE);
        btnCambiarTema.addActionListener(e -> {
            Tema.cambiarTema();
            aplicarTema();
        });
        panelDer.add(btnCambiarTema);

        topPanel.add(panelIzq, BorderLayout.WEST);
        topPanel.add(panelDer, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Panel principal con color
        mainPanel = new JPanel();
        mainPanel.setBackground(Tema.getColorFondo());
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

    private void aplicarTema() {
        Color colorFondo = Tema.getColorFondo();
        mainPanel.setBackground(colorFondo);
        topPanel.setBackground(colorFondo);
        mainPanel.repaint();
        topPanel.repaint();
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
                calendario.showCalendarioUI();
                dispose();
                break;
            case "Cerrar sesion":
                SwingUtilities.invokeLater(() -> {
                    new LoginApp().setVisible(true); // Mostrar la ventana de login
                });
                this.dispose(); // Cierra solo el dashboard actual
                break;

        }
    }
}

  

