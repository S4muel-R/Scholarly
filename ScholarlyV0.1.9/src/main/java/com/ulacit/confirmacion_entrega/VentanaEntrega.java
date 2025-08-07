package com.ulacit.confirmacion_entrega;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;

public class VentanaEntrega extends JFrame {
    private Actividad actividad1;
    private Actividad actividad2;
    private Actividad actividadActual;

    private JLabel labelNombre;
    private JTextField campoRespuesta;
    private JButton botonEntregar;
    private JButton botonCambio;
    private JButton botonVolver;
    private JLabel labelEstado;
    private JLabel iconoConfirmacion;

    public VentanaEntrega() {
        setTitle("Entrega de Actividades");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        actividad1 = new Actividad("Tarea 1: Investigación", LocalDateTime.now().plusDays(2));
        actividad2 = new Actividad("Tarea 2: Ensayo Final", LocalDateTime.now().minusDays(1));
        actividadActual = actividad1;

        construirInterfaz();
        actualizarVista();
    }

    private void construirInterfaz() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        labelNombre = new JLabel();
        labelNombre.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        panel.add(labelNombre, gbc);

        campoRespuesta = new JTextField(25);
        campoRespuesta.setToolTipText("Ingresa el link o respuesta de la actividad");
        gbc.gridy = 1;
        panel.add(campoRespuesta, gbc);

        botonEntregar = new JButton("Entregar");
        gbc.gridy = 2; gbc.gridwidth = 1; gbc.gridx = 0;
        panel.add(botonEntregar, gbc);

        botonVolver = new JButton("Volver");
        gbc.gridx = 1;
        panel.add(botonVolver, gbc);

        botonCambio = new JButton("Ir a Tarea 2");
        gbc.gridx = 2;
        panel.add(botonCambio, gbc);

        labelEstado = new JLabel("");
        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 3;
        panel.add(labelEstado, gbc);

        iconoConfirmacion = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
        iconoConfirmacion.setVisible(false);
        gbc.gridy = 4;
        panel.add(iconoConfirmacion, gbc);

        add(panel, BorderLayout.CENTER);

        // Listeners
        botonEntregar.addActionListener(e -> manejarEntrega());
        botonCambio.addActionListener(e -> cambiarActividad());
        botonVolver.addActionListener(e -> cerrarVentana());
    }

    private void manejarEntrega() {
        if (actividadActual.isEntregada()) {
            JOptionPane.showMessageDialog(this, "Ya entregaste esta actividad.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String respuesta = campoRespuesta.getText().trim();

        if (respuesta.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La respuesta no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (LocalDateTime.now().isAfter(actividadActual.getFechaLimite())) {
            JOptionPane.showMessageDialog(this, "La fecha límite para esta actividad ya ha pasado.", "Entrega no permitida", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = actividadActual.entregar(respuesta);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Entrega realizada con éxito a las " + actividadActual.getFechaEntrega(), "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            actualizarVista();
        }
    }

    private void cambiarActividad() {
        actividadActual = (actividadActual == actividad1) ? actividad2 : actividad1;
        actualizarVista();
    }

    private void cerrarVentana() {
        dispose();
    }

    private void actualizarVista() {
        labelNombre.setText(actividadActual.getNombre());
        campoRespuesta.setText("");
        campoRespuesta.setEnabled(!actividadActual.isEntregada());
        botonEntregar.setEnabled(!actividadActual.isEntregada());

        if (actividadActual.isEntregada()) {
            labelEstado.setText("Entregado el: " + actividadActual.getFechaEntrega());
            iconoConfirmacion.setVisible(true);
        } else {
            labelEstado.setText("Fecha límite: " + actividadActual.getFechaLimite());
            iconoConfirmacion.setVisible(false);
        }

        if (actividadActual == actividad1) {
            botonCambio.setText("Ir a Tarea 2");
        } else {
            botonCambio.setText("Volver a Tarea 1");
        }
    }
}
