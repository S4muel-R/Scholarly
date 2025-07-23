package com.ulacit.app;

import com.ulacit.login.Login;
import com.ulacit.academico.Plataforma;
import com.ulacit.asistencia.RegistroAsistencias;
import com.ulacit.foros.Foro;
import com.ulacit.gestiondegrupos.ModuloGestiondeGrupos;
import com.ulacit.mensajeria.Chatmensajes;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        setTitle("Menú Principal");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear botones
        JButton btnPlataforma = new JButton("Plataforma Académica");
        JButton btnGrupos = new JButton("Gestión de Grupos");
        JButton btnChat = new JButton("Chat Interno");
        JButton btnAsistencias = new JButton("Registro de Asistencias");
        JButton btnForos = new JButton("Foros Académicos");
        JButton btnSalir = new JButton("Salir");

        // Agregar acciones
        btnPlataforma.addActionListener(e -> Plataforma.main(new String[]{}));
        btnGrupos.addActionListener(e -> {
            this.setVisible(false);
            new ModuloGestiondeGrupos(this).setVisible(true);
        });

        btnChat.addActionListener(e -> Chatmensajes.main(new String[]{}));
        btnAsistencias.addActionListener(e -> new RegistroAsistencias().setVisible(true));
        btnForos.addActionListener(e -> new Foro().setVisible(true));
        btnSalir.addActionListener(e -> System.exit(0));

        // Organizar en un panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(btnPlataforma);
        panel.add(btnGrupos);
        panel.add(btnChat);
        panel.add(btnAsistencias);
        panel.add(btnForos);
        panel.add(btnSalir);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login(() -> {
                new Main().setVisible(true); // esta clase con botones
            }).setVisible(true);
        });
    }
}
