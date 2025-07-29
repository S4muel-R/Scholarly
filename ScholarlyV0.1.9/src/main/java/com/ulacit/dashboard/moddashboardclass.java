/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulacit.dashboard;

import com.ulacit.login.Login;
import com.ulacit.academico.Plataforma;
import com.ulacit.asistencia.RegistroAsistencias;
import com.ulacit.foros.Foro;
import com.ulacit.gestiondegrupos.ModuloGestiondeGrupos;
import com.ulacit.chat.ChatGUI;
import com.ulacit.academico.ModuloAdministracionCursos;
import com.ulacit.dashboard.moddashboardclass;

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
        
        
        // Crear botones
        JButton btnCursos = new JButton("Cursos");
        JButton btnAnuncios = new JButton("Anuncios");
        JButton btnChat = new JButton("Chat");
        JButton btnCalendario = new JButton("Calendario");
        JButton btnCerrar = new JButton("Cerrar sesion");

        // Agregar acciones
        //btnForos.addActionListener(e -> new Foro().setVisible(true));
        btnCursos.addActionListener(e -> new ModuloAdministracionCursos());
        btnChat.addActionListener(e -> new ChatGUI());

        

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

    private void manejarClick(String opcion) {
        switch (opcion) {
            case "Cursos":
                new ModuloAdministracionCursos().showAdminCursosUI();
                break;
            case "Anuncios":
                JOptionPane.showMessageDialog(this, "Abriendo el modulo nuncios");
                break;
            case "Chat":
                new ChatGUI();
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

  

