/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulacit.roles;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author penge
 */
public class EliminarUsuario extends JFrame {
    private gestorusuarios gestor;
    private ventanaadmin ventanaAdmin;

    public EliminarUsuario(ventanaadmin ventanaAdmin, gestorusuarios gestor) {
        this.gestor = gestor;
        this.ventanaAdmin = ventanaAdmin;

        setTitle("Eliminar Usuario");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        JComboBox<String> comboUsuarios = new JComboBox<>();
        JButton btnEliminar = new JButton("Eliminar");

        // Llenar el combo con correos de usuarios
        for (usuario u : gestor.getUsuarios()) {
            comboUsuarios.addItem(u.getCorreo());
        }

        panel.add(new JLabel("Seleccionar usuario:"));
        panel.add(comboUsuarios);
        panel.add(new JLabel());  // espacio vacío
        panel.add(btnEliminar);

        add(panel);

        btnEliminar.addActionListener(e -> {
            String correoSeleccionado = (String) comboUsuarios.getSelectedItem();
            if (correoSeleccionado != null) {
                gestor.eliminarUsuarioPorCorreo(correoSeleccionado);
                ventanaAdmin.actualizarComboUsuarios();
                JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No hay usuario seleccionado.");
            }
        });
    }
}
