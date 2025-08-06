package com.ulacit.editarPerfil;

import com.ulacit.login.LoginApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class editarPerfil extends JFrame {
    private JTextField txtCorreo, txtNombre;
    private JPasswordField txtClaveActual, txtClaveNueva, txtClaveConfirmar;
    private JButton btnGuardar, btnCancelar;

    public editarPerfil() {
        setTitle("Editar Perfil");
        setSize(520, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(187, 222, 251));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 18, 12, 18);
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int fieldWidth = 28;

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panel.add(new JLabel("Nombre*:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtNombre = new JTextField(fieldWidth);
        panel.add(txtNombre, gbc);

        // Correo
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panel.add(new JLabel("Correo*:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtCorreo = new JTextField(fieldWidth);
        panel.add(txtCorreo, gbc);

        // Contraseña actual
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panel.add(new JLabel("Contraseña actual*:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtClaveActual = new JPasswordField(fieldWidth);
        panel.add(txtClaveActual, gbc);

        // Nueva contraseña
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0;
        panel.add(new JLabel("Nueva contraseña:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtClaveNueva = new JPasswordField(fieldWidth);
        panel.add(txtClaveNueva, gbc);

        // Confirmar nueva contraseña
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0;
        panel.add(new JLabel("Confirmar nueva contraseña:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1;
        txtClaveConfirmar = new JPasswordField(fieldWidth);
        panel.add(txtClaveConfirmar, gbc);


        // Botones
        JPanel panelBotones = new JPanel(new BorderLayout());
        panelBotones.setBackground(new Color(187, 222, 251));

        // Botón Volver a la izquierda
        JButton btnVolver = new JButton("Volver");
        JPanel panelIzq = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelIzq.setBackground(new Color(187, 222, 251));
        panelIzq.add(btnVolver);
        panelBotones.add(panelIzq, BorderLayout.WEST);

        // Botones Guardar y Cancelar a la derecha
        JPanel panelDer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelDer.setBackground(new Color(187, 222, 251));
        btnGuardar = new JButton("Guardar");
        panelDer.add(btnGuardar);
        panelBotones.add(panelDer, BorderLayout.EAST);

        // Cambiar fondo del frame principal
        getContentPane().setBackground(new Color(187, 222, 251));

        add(panel, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción del botón Volver
        btnVolver.addActionListener(e -> dispose());

        cargarDatosUsuario();
        btnGuardar.addActionListener(e -> guardarPerfil());
    }

    private void cargarDatosUsuario() {
        if (LoginApp.usuarioActual != null) {
            txtCorreo.setText(LoginApp.usuarioActual.correo);
            txtNombre.setText(LoginApp.usuarioActual.tipo); // Usando 'tipo' como nombre por ahora
        }
    }

    private void guardarPerfil() {
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();
        String correoAnterior = LoginApp.usuarioActual.correo;
        String claveActual = new String(txtClaveActual.getPassword());
        String claveNueva = new String(txtClaveNueva.getPassword());
        String claveConfirmar = new String(txtClaveConfirmar.getPassword());

        // Validación de campos obligatorios
        if (nombre.isEmpty() || correo.isEmpty() || claveActual.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios.", "Campos requeridos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validación de correo
        if (!validarCorreo(correo)) {
            JOptionPane.showMessageDialog(this, "Formato de correo inválido.", "Correo inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar contraseña actual
        if (!claveActual.equals(LoginApp.usuarioActual.clave)) {
            JOptionPane.showMessageDialog(this, "La contraseña actual es incorrecta.", "Contraseña incorrecta", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Si se quiere cambiar la contraseña
        if (!claveNueva.isEmpty() || !claveConfirmar.isEmpty()) {
            if (!claveNueva.equals(claveConfirmar)) {
                JOptionPane.showMessageDialog(this, "La nueva contraseña y la confirmación no coinciden.", "Error de confirmación", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (claveNueva.length() < 8) {
                JOptionPane.showMessageDialog(this, "La nueva contraseña debe tener al menos 8 caracteres.", "Política de seguridad", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Guardar nueva contraseña
            LoginApp.usuarioActual.clave = claveNueva;
        }

        // Si el correo fue cambiado, actualizar el HashMap de usuarios
        if (!correoAnterior.equals(correo)) {
            // Remover el usuario con el correo anterior
            LoginApp.usuarios.remove(correoAnterior);
            // Actualizar el correo en el objeto usuario
            LoginApp.usuarioActual.correo = correo;
            // Agregar el usuario con el nuevo correo
            LoginApp.usuarios.put(correo, LoginApp.usuarioActual);
        } else {
            LoginApp.usuarioActual.correo = correo;
        }
        LoginApp.usuarioActual.tipo = nombre; // Usando 'tipo' como nombre por ahora

        JOptionPane.showMessageDialog(this, "Perfil actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }

    private boolean validarCorreo(String correo) {
        // Solo correos institucionales válidos
        String regex = "^[A-Za-z0-9+_.-]+@ulacit\\.ed\\.cr$";
        return Pattern.matches(regex, correo);
    }

    public static void main(String[] args) {
        // Demo
        SwingUtilities.invokeLater(() -> new editarPerfil().setVisible(true));
    }
}
