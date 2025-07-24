package com.ulacit.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private Runnable onSuccess;
    private JTextField usuario;
    private JPasswordField contrasena;
    private JButton ingresar;
    private JLabel mensaje;

    public Login(Runnable onSuccess) {
        this.onSuccess = onSuccess;
        setTitle("Login ULACIT");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 2, 10));

        usuario = new JTextField();
        contrasena = new JPasswordField();
        ingresar = new JButton("Ingresar");
        mensaje = new JLabel("", SwingConstants.CENTER);
        mensaje.setForeground(Color.RED);

        add(new JLabel("Correo institucional:", SwingConstants.CENTER));
        add(usuario);
        add(new JLabel("Contraseña:", SwingConstants.CENTER));
        add(contrasena);
        add(ingresar);
        add(mensaje);

        ingresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validarCampos();
            }
        });
    }

    private void validarCampos() {
        String correo = usuario.getText().trim();
        String clave = new String(contrasena.getPassword()).trim();

        if (correo.isEmpty() || clave.isEmpty()) {
            mensaje.setText("Todos los campos son obligatorios.");
            return;
        }

        if (!correo.matches("^[\\w.-]+@ulacit\\.ed\\.cr$")) {
            mensaje.setText("Debe usar un correo @ulacit.ed.cr.");
            return;
        }

        mensaje.setForeground(new Color(0, 128, 0));
        mensaje.setText("Inicio de sesión exitoso.");
        dispose();
        if (onSuccess != null) onSuccess.run();
    }

    public static boolean autenticar(String usuario, String clave) {
        return "test@ulacit.ed.cr".equals(usuario) && "1234".equals(clave);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login(() -> {
                // Aquí va lo que pasa después del login exitoso
                System.out.println("Login exitoso (desde test externo).");
            }).setVisible(true);
        });
    }
}
