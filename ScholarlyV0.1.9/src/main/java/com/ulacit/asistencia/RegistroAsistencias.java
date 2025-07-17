
package com.ulacit.asistencia;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
        
/**
 *
 * @author penge
 */
public class RegistroAsistencias extends JFrame {

    private final JComboBox<String> comboCursos;
    private final JComboBox<String> comboSemanas;
    private final JPanel panelLista;
    private final ArrayList<Estudiante> estudiantes;
    private final ArrayList<JComboBox<String>> combosAsistencia;

    public RegistroAsistencias() {
        setTitle("Registrar Asistencia");
        setSize(700, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("Edwin Peng", "1"));
        estudiantes.add(new Estudiante("Sebastian Vargas", "2"));

        combosAsistencia = new ArrayList<>();

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        comboCursos = new JComboBox<>(new String[]{"Matemáticas", "Ciencias", "Historia"});
        comboSemanas = new JComboBox<>(new String[]{"Semana 1", "Semana 2", "Semana 3", "Semana 4"});

        panelSuperior.add(new JLabel("Curso:"));
        panelSuperior.add(comboCursos);
        panelSuperior.add(new JLabel("Semana:"));
        panelSuperior.add(comboSemanas);

        panelLista = new JPanel();
        panelLista.setLayout(new GridLayout(0, 4, 10, 10));
        cargarListaEstudiantes();

        JButton btnRegistrar = new JButton("Registrar Estudiante");
        btnRegistrar.addActionListener(e -> registrarEstudiante());

        JButton btnGuardar = new JButton("Guardar Asistencia");
        btnGuardar.addActionListener(e -> guardarAsistencia());

        JButton btnVolver = new JButton("Volver al Menú del Curso");
        btnVolver.addActionListener(e -> JOptionPane.showMessageDialog(this, "Volviendo al menú del curso..."));

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnVolver);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(panelLista), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void cargarListaEstudiantes() {
        panelLista.removeAll();
        combosAsistencia.clear();

        panelLista.add(new JLabel("Estudiante"));
        panelLista.add(new JLabel("ID"));
        panelLista.add(new JLabel("Asistencia"));
        panelLista.add(new JLabel()); // Espacio vacío

        for (Estudiante e : estudiantes) {
            panelLista.add(new JLabel(e.getNombre()));
            panelLista.add(new JLabel(e.getId()));

            JComboBox<String> combo = new JComboBox<>(new String[]{"Presente", "Ausente", "Justificado"});
            combosAsistencia.add(combo);
            panelLista.add(combo);

            panelLista.add(new JLabel());
        }

        panelLista.revalidate();
        panelLista.repaint();
    }

    private void registrarEstudiante() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del estudiante:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        String id = JOptionPane.showInputDialog(this, "Ingrese el ID del estudiante:");
        if (id == null || id.trim().isEmpty()) return;

        for (Estudiante e : estudiantes) {
            if (e.getId().equalsIgnoreCase(id)) {
                JOptionPane.showMessageDialog(this, "Ya existe un estudiante con ese ID.");
                return;
            }
        }

        estudiantes.add(new Estudiante(nombre.trim(), id.trim()));
        cargarListaEstudiantes();
    }

    private void guardarAsistencia() {
        String curso = (String) comboCursos.getSelectedItem();
        String semana = (String) comboSemanas.getSelectedItem();

        StringBuilder sb = new StringBuilder();
        sb.append("Asistencia registrada para el curso ").append(curso)
          .append(" - ").append(semana).append(":\n\n");

        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante e = estudiantes.get(i);
            String estado = (String) combosAsistencia.get(i).getSelectedItem();
            sb.append(e.getNombre()).append(" (").append(e.getId())
              .append(") - ").append(estado).append("\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString(), "Resumen de Asistencia", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroAsistencias().setVisible(true));
    }
}