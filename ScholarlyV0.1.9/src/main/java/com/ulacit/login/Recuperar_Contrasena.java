/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


package com.mycompany.recuperar_contrasena;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;
/**
 *
 * @author Anderson M
 */
public class Recuperar_Contrasena extends JFrame {

    private static HashMap<String, String> usuarios = new HashMap<>();
    private static HashMap<String, String> clavesTemporales = new HashMap<>();

    static {
        usuarios.put("estudiante@ulacit.ed.cr", "1234");
        usuarios.put("profesor@ulacit.ed.cr", "1234");
        usuarios.put("admin@ulacit.ed.cr", "1234");
    }

    public Recuperar_Contrasena() {
        setTitle("SCHOLARLY - Recuperar Contraseña");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(187, 222, 251));

        // Panel del título
        JLabel lblTitulo = new JLabel("Recuperar Contraseña", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(Color.DARK_GRAY);

        JLabel lblSub = new JLabel("Ingrese su correo para generar una clave temporal", SwingConstants.CENTER);
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

        // Panel del formulario
        JPanel panelForm = new JPanel();
        panelForm.setOpaque(false);
        panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));

        JLabel lblCorreo = new JLabel("Correo:", SwingConstants.CENTER);
        lblCorreo.setFont(new Font("Arial", Font.BOLD, 18));
        lblCorreo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField txtCorreo = new JTextField();
        txtCorreo.setMaximumSize(new Dimension(400, 40));
        txtCorreo.setFont(new Font("Arial", Font.PLAIN, 16));
        txtCorreo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnEnviar = new JButton("Enviar Clave Temporal");
        btnEnviar.setBackground(new Color(50, 70, 250));
        btnEnviar.setForeground(Color.WHITE);
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 18));
        btnEnviar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEnviar.setMaximumSize(new Dimension(300, 50));

        JLabel lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        lblMensaje.setForeground(Color.RED);
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelForm.add(Box.createVerticalStrut(20));
        panelForm.add(lblCorreo);
        panelForm.add(Box.createVerticalStrut(10));
        panelForm.add(txtCorreo);
        panelForm.add(Box.createVerticalStrut(20));
        panelForm.add(btnEnviar);
        panelForm.add(Box.createVerticalStrut(20));
        panelForm.add(lblMensaje);

        // Ensamblar
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(panelTitulo);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(panelForm);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);

        // Acción botón
        btnEnviar.addActionListener(e -> {
            String correo = txtCorreo.getText().trim();
            if (correo.isEmpty()) {
                lblMensaje.setText("El campo no puede estar vacío.");
                return;
            }
            if (!usuarios.containsKey(correo)) {
                lblMensaje.setText("Correo no registrado.");
                return;
            }

            // Generar clave temporal
            String claveTemp = generarClaveTemporal();
            clavesTemporales.put(correo, claveTemp);

            JOptionPane.showMessageDialog(this,
                "Clave temporal generada: " + claveTemp + "\n(En un sistema real se enviaría por correo).");
            new NuevaContrasena(correo).setVisible(true);
            dispose();
        });
    }

    private String generarClaveTemporal() {
        Random r = new Random();
        return String.valueOf(100000 + r.nextInt(900000));
    }

    // Pantalla de nueva contraseña
    static class NuevaContrasena extends JFrame {
        public NuevaContrasena(String correo) {
            setTitle("SCHOLARLY - Nueva Contraseña");
            setSize(600, 400);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBackground(new Color(187, 222, 251));

            JLabel lblTitulo = new JLabel("Definir Nueva Contraseña", SwingConstants.CENTER);
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
            lblTitulo.setForeground(Color.DARK_GRAY);

            JPanel panelTitulo = new JPanel();
            panelTitulo.setOpaque(false);
            panelTitulo.setLayout(new BoxLayout(panelTitulo, BoxLayout.Y_AXIS));
            lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelTitulo.add(lblTitulo);

            JPanel panelForm = new JPanel();
            panelForm.setOpaque(false);
            panelForm.setLayout(new BoxLayout(panelForm, BoxLayout.Y_AXIS));

            JLabel lblClaveTemp = new JLabel("Clave Temporal:", SwingConstants.CENTER);
            lblClaveTemp.setFont(new Font("Arial", Font.BOLD, 18));
            lblClaveTemp.setAlignmentX(Component.CENTER_ALIGNMENT);

            JTextField txtClaveTemp = new JTextField();
            txtClaveTemp.setMaximumSize(new Dimension(400, 40));
            txtClaveTemp.setFont(new Font("Arial", Font.PLAIN, 16));
            txtClaveTemp.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel lblNuevaClave = new JLabel("Nueva Contraseña:", SwingConstants.CENTER);
            lblNuevaClave.setFont(new Font("Arial", Font.BOLD, 18));
            lblNuevaClave.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPasswordField txtNuevaClave = new JPasswordField();
            txtNuevaClave.setMaximumSize(new Dimension(400, 40));
            txtNuevaClave.setFont(new Font("Arial", Font.PLAIN, 16));
            txtNuevaClave.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton btnGuardar = new JButton("Guardar Nueva Contraseña");
            btnGuardar.setBackground(new Color(0, 55, 100));
            btnGuardar.setForeground(Color.WHITE);
            btnGuardar.setFont(new Font("Arial", Font.BOLD, 18));
            btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnGuardar.setMaximumSize(new Dimension(350, 50));

            JLabel lblMensaje = new JLabel("", SwingConstants.CENTER);
            lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
            lblMensaje.setForeground(Color.RED);
            lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);

            panelForm.add(Box.createVerticalStrut(20));
            panelForm.add(lblClaveTemp);
            panelForm.add(Box.createVerticalStrut(10));
            panelForm.add(txtClaveTemp);
            panelForm.add(Box.createVerticalStrut(20));
            panelForm.add(lblNuevaClave);
            panelForm.add(Box.createVerticalStrut(10));
            panelForm.add(txtNuevaClave);
            panelForm.add(Box.createVerticalStrut(20));
            panelForm.add(btnGuardar);
            panelForm.add(Box.createVerticalStrut(20));
            panelForm.add(lblMensaje);

            mainPanel.add(Box.createVerticalStrut(30));
            mainPanel.add(panelTitulo);
            mainPanel.add(Box.createVerticalStrut(30));
            mainPanel.add(panelForm);
            mainPanel.add(Box.createVerticalGlue());

            add(mainPanel);

            btnGuardar.addActionListener(e -> {
                String claveTempIngresada = txtClaveTemp.getText().trim();
                String nuevaClave = new String(txtNuevaClave.getPassword());

                if (!clavesTemporales.containsKey(correo) || !clavesTemporales.get(correo).equals(claveTempIngresada)) {
                    lblMensaje.setText("Clave temporal incorrecta.");
                    return;
                }
                if (nuevaClave.length() < 4) {
                    lblMensaje.setText("La contraseña debe tener al menos 4 caracteres.");
                    return;
                }

                usuarios.put(correo, nuevaClave);
                clavesTemporales.remove(correo);
                JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente.");
                dispose();
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Recuperar_Contrasena().setVisible(true));
    }
}
