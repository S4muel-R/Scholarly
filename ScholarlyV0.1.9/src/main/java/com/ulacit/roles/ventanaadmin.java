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

public class ventanaadmin extends JFrame {
    private gestorusuarios gestor;
    private JComboBox<usuario> comboUsuarios;
    private JComboBox<rol> comboRoles;
    private JTextArea areaReporte;

    public ventanaadmin() {
        gestor = new gestorusuarios();
        setTitle("Gestión de Roles de Usuario");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Datos de ejemplo
        gestor.agregarUsuario(new usuario("Ana", "ana@mail.com"));
        gestor.agregarUsuario(new usuario("Carlos", "carlos@mail.com"));

        setLayout(new BorderLayout());

        // Panel de asignación
        JPanel panelAsignar = new JPanel(new FlowLayout());
        comboUsuarios = new JComboBox<>(gestor.getUsuarios().toArray(new usuario[0]));
        comboRoles = new JComboBox<>(rol.values());

        JButton btnAsignar = new JButton("Asignar Rol");
        btnAsignar.addActionListener(e -> asignarRol());

        panelAsignar.add(new JLabel("Usuario:"));
        panelAsignar.add(comboUsuarios);
        panelAsignar.add(new JLabel("Rol:"));
        panelAsignar.add(comboRoles);
        panelAsignar.add(btnAsignar);

        add(panelAsignar, BorderLayout.NORTH);

        // Área de reporte
        areaReporte = new JTextArea();
        areaReporte.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaReporte);
        add(scroll, BorderLayout.CENTER);

        JButton btnReporte = new JButton("Generar Reporte");
        btnReporte.addActionListener(e -> generarReporte());
        add(btnReporte, BorderLayout.SOUTH);
    }

    private void asignarRol() {
        usuario usuario = (usuario) comboUsuarios.getSelectedItem();
        rol rol = (rol) comboRoles.getSelectedItem();

        if (usuario != null && rol != null) {
            gestor.cambiarRolUsuario(usuario, rol);
            JOptionPane.showMessageDialog(this, "Rol asignado correctamente a " + usuario.getNombre());
        }
    }

    private void generarReporte() {
        areaReporte.setText("REPORTE DE USUARIOS ACTIVOS CON ROL\n\n");
        for (usuario u : gestor.getUsuariosConRol()) {
            areaReporte.append(u.getResumen() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ventanaadmin().setVisible(true));
    }
}
