/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulacit.login;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import com.ulacit.dashboard.moddashboardclass;
import com.ulacit.login.Recuperar_Contrasena;
import com.ulacit.dashboard.dashboardestudiante;
import com.ulacit.dashboard.dashboardadmin;

/**
 *
 * @author Anderson M
 */
public class LoginApp extends JFrame {
    // Usuario autenticado global
    public static Usuario usuarioActual = null;

    public LoginApp() {
        setTitle("SCHOLARLY");
        setSize(800, 600); // Ventana inicial
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(187, 222, 251)); // Fondo solicitado

        // Panel del título
        JLabel lblTitulo = new JLabel("Bienvenido a SCHOLARLY", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(Color.DARK_GRAY);

        JLabel lblSub = new JLabel("Facilitando la enseñanza y el aprendizaje", SwingConstants.CENTER);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 18));
        lblSub.setForeground(new Color(70, 70, 70));

        JPanel panelTitulo = new JPanel();
        panelTitulo.setOpaque(false);
        panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelTitulo.add(lblTitulo);
        panelTitulo.add(Box.createVerticalStrut(10));
        panelTitulo.add(lblSub);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));

        Dimension botonSize = new Dimension(350, 100);

        JButton btnEstudiante = new JButton("Estudiante / Profesor");
        btnEstudiante.setPreferredSize(botonSize);
        btnEstudiante.setMaximumSize(botonSize);
        btnEstudiante.setMinimumSize(botonSize);
        btnEstudiante.setBackground(new Color(50, 70, 250));
        btnEstudiante.setForeground(Color.WHITE);
        btnEstudiante.setFont(new Font("Arial", Font.BOLD, 16));

        JButton btnAdmin = new JButton("Administrativo");
        btnAdmin.setPreferredSize(botonSize);
        btnAdmin.setMaximumSize(botonSize);
        btnAdmin.setMinimumSize(botonSize);
        btnAdmin.setBackground(new Color(0, 55, 100));
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setFont(new Font("Arial", Font.BOLD, 16));

        panelBotones.add(Box.createHorizontalGlue());
        panelBotones.add(btnEstudiante);
        panelBotones.add(Box.createHorizontalStrut(40)); // Espacio entre botones
        panelBotones.add(btnAdmin);
        panelBotones.add(Box.createHorizontalGlue());

        // Ensamblar todo
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(panelTitulo);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(panelBotones);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);

        // Acciones
        btnEstudiante.addActionListener(e -> {
            new LoginForm().setVisible(true);
            dispose();
        });
        btnAdmin.addActionListener(e -> {
            new LoginForm("Administrativo").setVisible(true);
            dispose();
        });
    }

    static class LoginForm extends JFrame {
        private JTextField txtCorreo;
        private JPasswordField txtClave;
        private String tipoUsuario;

        public LoginForm() { this(null); }

        public LoginForm(String tipoUsuario) {
            this.tipoUsuario = tipoUsuario;
            setTitle("Login" + (tipoUsuario != null ? " - " + tipoUsuario.toUpperCase() : ""));
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new GridLayout(6, 1));

            txtCorreo = new JTextField();
            txtClave = new JPasswordField();
            JButton btnLogin = new JButton("Ingresar");
            JLabel lblMsj = new JLabel("", SwingConstants.CENTER);
            
            JButton btnRecuperarContra = new JButton("Recuperar Contrasena");

            add(new JLabel("Correo:", SwingConstants.CENTER));
            add(txtCorreo);
            add(new JLabel("Contraseña:", SwingConstants.CENTER));
            add(txtClave);
            add(btnLogin);
            add(lblMsj);
            add(btnRecuperarContra);
            
            btnRecuperarContra.addActionListener(e-> {
                new Recuperar_Contrasena().setVisible(true);
                });

            btnLogin.addActionListener(e -> {
                String correo = txtCorreo.getText().trim();
                String clave = new String(txtClave.getPassword());

            if (LoginApp.usuarios.containsKey(correo)) {
                Usuario u = LoginApp.usuarios.get(correo);
                if (u.clave.equals(clave)) {
                    usuarioActual = u; // Guarda el usuario actual
                    dispose(); // Cierra ventana de login

                    // Redirección según el correo
                    if (correo.equalsIgnoreCase("admin@ulacit.ed.cr")) {
                        new dashboardadmin().setVisible(true);
                    } else if (correo.equalsIgnoreCase("profesor@ulacit.ed.cr")) {
                        new moddashboardclass().setVisible(true);
                    } else if (correo.equalsIgnoreCase("estudiante@ulacit.ed.cr")) {
                        new dashboardestudiante().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Correo no tiene asignado un menú.");
                        new LoginApp().setVisible(true); // Regresa al login
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
            }
            });
        }
    }

    public static class Usuario {
        public String correo, clave, tipo;
        Usuario(String correo, String clave, String tipo) {
            this.correo = correo; this.clave = clave; this.tipo = tipo;
        }
    }

    public static HashMap<String, Usuario> usuarios = new HashMap<>();
    static {
        usuarios.put("estudiante@ulacit.ed.cr", new Usuario("estudiante@ulacit.ed.cr", "1234", "Estudiante"));
        usuarios.put("profesor@ulacit.ed.cr", new Usuario("profesor@ulacit.ed.cr", "1234", "Profesor"));
        usuarios.put("admin@ulacit.ed.cr", new Usuario("admin@ulacit.ed.cr", "1234", "Administrativo"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginApp().setVisible(true));
    }
}