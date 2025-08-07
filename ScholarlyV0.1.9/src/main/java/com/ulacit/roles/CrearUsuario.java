/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulacit.roles;

/**
 *
 * @author penge
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class CrearUsuario extends JFrame {
    private gestorusuarios gestor;
    private ventanaadmin ventanaAdmin;

    public CrearUsuario(ventanaadmin ventanaAdmin, gestorusuarios gestor) {
        this.gestor = gestor;
        this.ventanaAdmin = ventanaAdmin;

        setTitle("Crear Usuario");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JTextField txtNombre = new JTextField();
        JTextField txtCorreo = new JTextField();
        JButton btnCrear = new JButton("Crear");

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Correo:"));
        panel.add(txtCorreo);
        panel.add(new JLabel());  // espacio vacío
        panel.add(btnCrear);
        
        add(panel);

        btnCrear.addActionListener(e -> {
        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (!nombre.isEmpty() && !correo.isEmpty()) {
            if (gestor.existeCorreo(correo)) {
                JOptionPane.showMessageDialog(this, "El correo ya está registrado. No se puede duplicar.");
            } else {
                gestor.agregarUsuario(new usuario(nombre, correo));
                ventanaAdmin.actualizarComboUsuarios();
                JOptionPane.showMessageDialog(this, "Usuario creado correctamente.");
                dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete ambos campos.");
        }
    });
        
    }
}