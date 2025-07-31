package com.ulacit.asistenciav2;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class AsistenciaManager {

    public static void mostrarAsistenciaProfesor(Profesor prof, List<Curso> cursos, List<String> semanas) {
        JFrame frame = new JFrame("Asistencia - Profesor: " + prof.getNombre());
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Panel superior
        JPanel seleccionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        seleccionPanel.setBorder(BorderFactory.createTitledBorder("Seleccionar curso y semana"));

        JComboBox<String> cbCurso = new JComboBox<>();
        for (Curso c : cursos) {
            cbCurso.addItem(c.getNombre());
        }

        JComboBox<String> cbSemana = new JComboBox<>(semanas.toArray(new String[0]));

        seleccionPanel.add(new JLabel("Curso:"));
        seleccionPanel.add(cbCurso);
        seleccionPanel.add(new JLabel("Semana:"));
        seleccionPanel.add(cbSemana);

        // Panel de lista de estudiantes
        JPanel listaPanel = new JPanel();
        listaPanel.setLayout(new GridLayout(0, 3, 10, 10));
        listaPanel.setBorder(BorderFactory.createTitledBorder("Lista de estudiantes"));

        JScrollPane scroll = new JScrollPane(listaPanel);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botón Volver
        JPanel botonesInferiores = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.addActionListener(e -> frame.dispose());
        botonesInferiores.add(btnVolver);

        // Función para cargar estudiantes
        Runnable cargarEstudiantes = () -> {
            listaPanel.removeAll();
            String cursoSeleccionado = (String) cbCurso.getSelectedItem();
            String semanaSeleccionada = (String) cbSemana.getSelectedItem();

            Curso curso = cursos.stream()
                    .filter(c -> c.getNombre().equals(cursoSeleccionado))
                    .findFirst().orElse(null);

            if (curso == null) return;

            for (Estudiante est : curso.getEstudiantes()) {
                listaPanel.add(new JLabel(est.getNombre() + " (" + est.getId() + ")"));

                JComboBox<String> estado = new JComboBox<>(new String[]{"Presente", "Ausente", "Justificado"});
                String actual = est.getHistorialAsistencia().getOrDefault(semanaSeleccionada, "Presente");
                estado.setSelectedItem(actual);
                listaPanel.add(estado);

                JButton guardar = new JButton("Guardar");
                guardar.addActionListener(ev -> {
                    est.registrarAsistencia(semanaSeleccionada, (String) estado.getSelectedItem());
                    JOptionPane.showMessageDialog(frame, "Asistencia guardada para " + est.getNombre());
                });
                listaPanel.add(guardar);
            }

            listaPanel.revalidate();
            listaPanel.repaint();
        };

        cbCurso.addActionListener(e -> cargarEstudiantes.run());
        cbSemana.addActionListener(e -> cargarEstudiantes.run());

        frame.add(seleccionPanel, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);
        frame.add(botonesInferiores, BorderLayout.SOUTH);

        // Cargar inicial
        cargarEstudiantes.run();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
