/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulacit.roles;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 *
 * @author penge
 */

public class ventanaadmin extends JFrame {
    private gestorusuarios gestor;
    private JComboBox<usuario> comboUsuarios;
    private JComboBox<rol> comboRoles;
    private JTextArea areaReporte;
    private JFrame ventanaAnterior;

    public ventanaadmin() {
        gestor = new gestorusuarios();
        setTitle("Gestión de Roles de Usuario");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Datos de ejemplo
        gestor.agregarUsuario(new usuario("Edwin", "edwin@gmail.com"));
        gestor.agregarUsuario(new usuario("Carlos", "carlos@gmail.com"));

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


        
        JButton btnCrearUsuario = new JButton("Crear Usuario");
        btnCrearUsuario.addActionListener(e -> {
         CrearUsuario ventanaCrear = new CrearUsuario(this, gestor); // this es para volver
            ventanaCrear.setVisible(true);
        });
        
        JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
        btnEliminarUsuario.addActionListener(e -> {
            new EliminarUsuario(this, gestor).setVisible(true);
        });
        
        JPanel panelBotones = new JPanel(new GridLayout(2, 1));
        panelBotones.add(btnCrearUsuario);
        panelBotones.add(btnEliminarUsuario);
        add(panelBotones, BorderLayout.WEST);

        // Área de reporte
        areaReporte = new JTextArea();
        areaReporte.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaReporte);
        add(scroll, BorderLayout.CENTER);

        JButton btnReporte = new JButton("Generar Reporte");
        btnReporte.addActionListener(e -> generarReporte());
        add(btnReporte, BorderLayout.SOUTH);
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            this.dispose(); // cierra esta ventana
            ventanaAnterior.setVisible(true); // vuelve a mostrar la anterior
        });
        add(btnVolver, BorderLayout.EAST);
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
    
    public void actualizarComboUsuarios() {
        comboUsuarios.removeAllItems();
        for (usuario u : gestor.getUsuarios()) {
            comboUsuarios.addItem(u);
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ventanaadmin().setVisible(true));
    }
}
