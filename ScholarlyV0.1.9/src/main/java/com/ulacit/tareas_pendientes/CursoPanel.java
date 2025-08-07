package com.ulacit.tareas_pendientes;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CursoPanel extends JFrame {

    private Estudiante estudiante;
    private Curso curso;

    public CursoPanel(Estudiante estudiante, Curso curso) {
        this.estudiante = estudiante;
        this.curso = curso;

        setTitle("Panel del Curso: " + curso.getNombre());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarUI();
    }

    private void inicializarUI() {
        List<Tarea> tareasPendientes = GestorTareas.obtenerTareasPendientes(curso, estudiante);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel tituloCurso = new JLabel("Curso: " + curso.getNombre());
        tituloCurso.setFont(new Font("Arial", Font.BOLD, 18));
        tituloCurso.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(tituloCurso);

        panel.add(Box.createVerticalStrut(15));

        if (tareasPendientes.isEmpty()) {
            panel.add(new JLabel("No tienes tareas pendientes activas."));
        } else {
            JLabel contadorLabel = new JLabel("Tareas pendientes: " + tareasPendientes.size());
            contadorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            panel.add(contadorLabel);

            for (Tarea tarea : tareasPendientes) {
                panel.add(new JLabel("- " + tarea.getNombre()));
            }
        }

        getContentPane().add(panel);
    }
}
