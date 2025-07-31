/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulacit.menucurso;

/**
 *
 * @author penge
 */
import com.ulacit.gestiondegrupos.ModuloGestiondeGrupos;
import com.ulacit.foros.Foro;
import com.ulacit.asistencia.

import java.awt.*;
import javax.swing.*;

public class menucurso extends JFrame {
    
    private static menucurso instance;

    public menucurso() {
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
        
        
        // Crear botones
        JButton btnGrupos = new JButton("Cursos");
        JButton btnForos = new JButton("Anuncios");
        JButton btnAsistencia = new JButton("Chat");
        JButton btnVolver = new JButton("Volver");

        // Agregar acciones
        //btnForos.addActionListener(e -> new Foro().setVisible(true));
        btnGrupos.addActionListener(e -> new ModuloGestiondeGrupos());
        btnForos.addActionListener(e -> new Foro());
        btnAsistencia.addActionListener(e -> new );

        

        String[] botones = {"Cursos", "Anuncios", "Chat", "Calendario", "Cerrar sesion"};

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
    
        public static menucurso getInstance() {
        if (instance == null || !instance.isDisplayable()) {
            instance = new menucurso();
        }
        return instance;
    }

    private void manejarClick(String opcion) {
        switch (opcion) {
            case "Cursos":
                new ModuloAdministracionCursos().showAdminCursosUI();
                dispose();
                break;
            case "Anuncios":
                JOptionPane.showMessageDialog(this, "Abriendo el modulo nuncios");
                break;
            case "Chat":
                new ChatGUI();
                dispose();
                break;
            case "Calendario":
                new calendario().showCalendarioUI();
                dispose();
                break;
            case "Volver":
            for (Window window : Window.getWindows()) {
                window.dispose();
            }
            new LoginApp().setVisible(true); // Abre una nueva instancia del login
        }
    }}

