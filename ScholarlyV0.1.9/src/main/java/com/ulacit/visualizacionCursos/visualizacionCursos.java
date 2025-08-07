
package com.ulacit.visualizacionCursos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import com.ulacit.academico.ModuloAdministracionCursos;

public class visualizacionCursos {
    // Referencia a la lista de cursos compartida
    private final List<String> cursos;
    private int cursoSeleccionado = -1;

    // Constructor que recibe el módulo de administración
    public visualizacionCursos(ModuloAdministracionCursos admCursos) {
        this.cursos = admCursos.getCursos();
    }

    // Constructor por defecto para pruebas (usa su propia lista)
    public visualizacionCursos() {
        this.cursos = new ArrayList<>();
        cursos.add("Matemática I | MAT101 | 2025-2 | Lunes 8-10am | Aula 101 | Prof. Juan Pérez | A1,A2,A3");
        cursos.add("Historia Universal | HIS201 | 2025-2 | Miércoles 10-12am | Aula 202 | Prof. Carla Gómez | B1,B2,B3,B4");
    }

    public void showVisualizacionCursosUI() {
        JFrame frame = new JFrame("Scholarly - Mis Cursos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel principal y de cursos
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(com.ulacit.tema.Tema.getColorFondo());

        JPanel panelCursos = new JPanel(new BorderLayout());
        panelCursos.setBackground(com.ulacit.tema.Tema.getColorFondo());
        panelCursos.setPreferredSize(new Dimension(900, 180));

        JPanel panelBarras = new JPanel();
        panelBarras.setLayout(new BoxLayout(panelBarras, BoxLayout.Y_AXIS));
        panelBarras.setBackground(com.ulacit.tema.Tema.getColorFondo());

        JScrollPane scrollBarras = new JScrollPane(panelBarras);
        scrollBarras.setBorder(null);
        scrollBarras.getViewport().setBackground(com.ulacit.tema.Tema.getColorFondo());
        scrollBarras.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBarras.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        JLabel sinCursos = new JLabel("Sin cursos para mostrar.", SwingConstants.CENTER);
        sinCursos.setFont(new Font("Arial", Font.BOLD, 24));

        // Panel de información inferior
        JPanel panelInfoWrapper = new JPanel(new BorderLayout());
        panelInfoWrapper.setBackground(com.ulacit.tema.Tema.getColorFondo());
        panelInfoWrapper.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBackground(com.ulacit.tema.Tema.getColorFondo());
        panelInfo.setPreferredSize(new Dimension(900, 400));
        panelInfoWrapper.add(panelInfo, BorderLayout.CENTER);


        // Usar un array para permitir referencias mutuas entre los Runnables
        final Runnable[] refrescarPanelInfo = new Runnable[1];
        final Runnable[] refrescarCursos = new Runnable[1];

        refrescarPanelInfo[0] = () -> {
            panelInfo.removeAll();
            if (cursos.isEmpty()) {
                panelInfo.setBackground(com.ulacit.tema.Tema.getColorFondo());
            } else if (cursoSeleccionado >= 0 && cursoSeleccionado < cursos.size()) {
                panelInfo.setBackground(com.ulacit.tema.Tema.getColorFondo());
                String[] partes = cursos.get(cursoSeleccionado).split("\\|");
                String nombre = partes.length > 0 ? partes[0].trim() : "";
                String codigo = partes.length > 1 ? partes[1].trim() : "";
                String periodo = partes.length > 2 ? partes[2].trim() : "";
                String horario = partes.length > 3 ? partes[3].trim() : "";
                String aula = partes.length > 4 ? partes[4].trim() : "";
                String profesor = partes.length > 5 ? partes[5].trim() : "";
                String estudiantes = partes.length > 6 ? partes[6].trim() : "";

                JPanel tarjetaPanel = new JPanel(new GridBagLayout());
                tarjetaPanel.setBackground(Color.WHITE);
                tarjetaPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(41, 99, 156), 3, true),
                        BorderFactory.createEmptyBorder(32, 48, 32, 48)));

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 20, 10, 20);
                gbc.anchor = GridBagConstraints.WEST;
                gbc.gridx = 0; gbc.gridy = 0;
                // Nombre
                JLabel labelNombre = new JLabel("Nombre del Curso:");
                labelNombre.setFont(new Font("Arial", Font.BOLD, 22));
                labelNombre.setForeground(new Color(41, 99, 156));
                tarjetaPanel.add(labelNombre, gbc);
                gbc.gridx = 1;
                JLabel valorNombre = new JLabel(nombre);
                valorNombre.setFont(new Font("Arial", Font.BOLD, 22));
                valorNombre.setForeground(new Color(25, 118, 210));
                tarjetaPanel.add(valorNombre, gbc);

                // Código
                gbc.gridx = 0; gbc.gridy++;
                JLabel labelCodigo = new JLabel("Código:");
                labelCodigo.setFont(new Font("Arial", Font.PLAIN, 18));
                labelCodigo.setForeground(new Color(41, 99, 156));
                tarjetaPanel.add(labelCodigo, gbc);
                gbc.gridx = 1;
                JLabel valorCodigo = new JLabel(codigo);
                valorCodigo.setFont(new Font("Arial", Font.PLAIN, 18));
                valorCodigo.setForeground(new Color(25, 118, 210));
                tarjetaPanel.add(valorCodigo, gbc);

                // Periodo
                gbc.gridx = 0; gbc.gridy++;
                JLabel labelPeriodo = new JLabel("Periodo:");
                labelPeriodo.setFont(new Font("Arial", Font.PLAIN, 18));
                labelPeriodo.setForeground(new Color(41, 99, 156));
                tarjetaPanel.add(labelPeriodo, gbc);
                gbc.gridx = 1;
                JLabel valorPeriodo = new JLabel(periodo);
                valorPeriodo.setFont(new Font("Arial", Font.PLAIN, 18));
                valorPeriodo.setForeground(new Color(25, 118, 210));
                tarjetaPanel.add(valorPeriodo, gbc);

                // Horario
                gbc.gridx = 0; gbc.gridy++;
                JLabel labelHorario = new JLabel("Horario:");
                labelHorario.setFont(new Font("Arial", Font.PLAIN, 18));
                labelHorario.setForeground(new Color(41, 99, 156));
                tarjetaPanel.add(labelHorario, gbc);
                gbc.gridx = 1;
                JLabel valorHorario = new JLabel(horario);
                valorHorario.setFont(new Font("Arial", Font.PLAIN, 18));
                valorHorario.setForeground(new Color(25, 118, 210));
                tarjetaPanel.add(valorHorario, gbc);

                // Aula
                gbc.gridx = 0; gbc.gridy++;
                JLabel labelAula = new JLabel("Aula:");
                labelAula.setFont(new Font("Arial", Font.PLAIN, 18));
                labelAula.setForeground(new Color(41, 99, 156));
                tarjetaPanel.add(labelAula, gbc);
                gbc.gridx = 1;
                JLabel valorAula = new JLabel(aula);
                valorAula.setFont(new Font("Arial", Font.PLAIN, 18));
                valorAula.setForeground(new Color(25, 118, 210));
                tarjetaPanel.add(valorAula, gbc);

                // Profesor
                gbc.gridx = 0; gbc.gridy++;
                JLabel labelProfesor = new JLabel("Profesor:");
                labelProfesor.setFont(new Font("Arial", Font.PLAIN, 18));
                labelProfesor.setForeground(new Color(41, 99, 156));
                tarjetaPanel.add(labelProfesor, gbc);
                gbc.gridx = 1;
                JLabel valorProfesor = new JLabel(profesor);
                valorProfesor.setFont(new Font("Arial", Font.PLAIN, 18));
                valorProfesor.setForeground(new Color(25, 118, 210));
                tarjetaPanel.add(valorProfesor, gbc);

                // Estudiantes
                gbc.gridx = 0; gbc.gridy++;
                JLabel labelEstudiantes = new JLabel("Estudiantes:");
                labelEstudiantes.setFont(new Font("Arial", Font.PLAIN, 18));
                labelEstudiantes.setForeground(new Color(41, 99, 156));
                tarjetaPanel.add(labelEstudiantes, gbc);

                // Panel de alumnos
                String[] alumnosArr = estudiantes.split(",");
                List<String> listaAlumnos = new ArrayList<>();
                for (String alumno : alumnosArr) {
                    String a = alumno.trim();
                    if (!a.isEmpty()) listaAlumnos.add(a);
                }
                int cantidadAlumnos = listaAlumnos.size();

                JPanel alumnosPanel = new JPanel();
                alumnosPanel.setLayout(new BoxLayout(alumnosPanel, BoxLayout.Y_AXIS));
                alumnosPanel.setBackground(Color.WHITE);
                alumnosPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                JLabel cantidadLabel = new JLabel("(" + cantidadAlumnos + ")");
                cantidadLabel.setFont(new Font("Arial", Font.BOLD, 16));
                cantidadLabel.setForeground(new Color(25, 118, 210));
                cantidadLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                alumnosPanel.add(cantidadLabel);
                for (String alumno : listaAlumnos) {
                    JLabel alumnoLabel = new JLabel("- " + alumno);
                    alumnoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                    alumnoLabel.setForeground(new Color(25, 118, 210));
                    alumnoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    alumnosPanel.add(alumnoLabel);
                }
                gbc.gridx = 1;
                tarjetaPanel.add(alumnosPanel, gbc);

                // Panel contenedor para tarjeta
                JPanel contenedorFinal = new JPanel(new BorderLayout());
                contenedorFinal.setBackground(Color.WHITE);
                contenedorFinal.add(tarjetaPanel, BorderLayout.CENTER);

                JScrollPane scrollInfo = new JScrollPane(contenedorFinal);
                scrollInfo.setBorder(null);
                scrollInfo.setBackground(Color.WHITE);
                scrollInfo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollInfo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                scrollInfo.getViewport().setBackground(Color.WHITE);

                panelInfo.add(scrollInfo, BorderLayout.CENTER);
            } else {
                panelInfo.setBackground(new Color(41, 99, 156));
                JLabel label = new JLabel("Seleccione un curso", SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 28));
                label.setForeground(Color.WHITE);
                panelInfo.add(label, BorderLayout.CENTER);
            }
            panelInfo.revalidate();
            panelInfo.repaint();
        };

        refrescarCursos[0] = () -> {
            panelBarras.removeAll();
            if (cursos.isEmpty()) {
                panelCursos.removeAll();
                panelCursos.add(sinCursos, BorderLayout.CENTER);
            } else {
                int idx = 1;
                for (int i = 0; i < cursos.size(); i++) {
                    String c = cursos.get(i);
                    String[] partes = c.split("\\|");
                    String nombre = partes.length > 0 ? partes[0].trim() : "";
                    String codigo = partes.length > 1 ? partes[1].trim() : "";
                    String horario = partes.length > 3 ? partes[3].trim() : "";
                    String textoBarra = idx + ". " + nombre + " - " + codigo + " (" + horario + ")";
                    JPanel barra = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    barra.setBackground(cursoSeleccionado == i ? new Color(144, 202, 249) : Color.WHITE);
                    Color azulBorde = new Color(25, 118, 210);
                    barra.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createMatteBorder(2, 2, 2, 2, azulBorde),
                            BorderFactory.createEmptyBorder(6, 16, 6, 16)));
                    barra.setPreferredSize(new Dimension(900, 36));
                    barra.setMaximumSize(new Dimension(900, 36));
                    barra.setAlignmentX(Component.LEFT_ALIGNMENT);
                    JLabel label = new JLabel(textoBarra);
                    label.setFont(new Font("Arial", Font.PLAIN, 15));
                    barra.add(label);
                    final int idxBarra = i;
                    barra.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            cursoSeleccionado = idxBarra;
                            refrescarCursos[0].run();
                            refrescarPanelInfo[0].run();
                        }
                    });
                    panelBarras.add(barra);
                    idx++;
                }
                panelCursos.removeAll();
                panelCursos.add(scrollBarras, BorderLayout.CENTER);
            }
            panelCursos.revalidate();
            panelCursos.repaint();
        };

        refrescarCursos[0].run();
        refrescarPanelInfo[0].run();

        // Panel de botones (solo cerrar)
        JButton botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> frame.dispose());
        JButton botonVerTareas = new JButton("Ver tareas");
        botonVerTareas.setFont(new Font("Arial", Font.BOLD, 14));
        botonVerTareas.setBackground(new Color(25, 118, 210));
        botonVerTareas.setForeground(Color.WHITE);
        botonVerTareas.setFocusPainted(false);
        botonVerTareas.setPreferredSize(new Dimension(140, 36));
        botonVerTareas.addActionListener(e -> {
            if (cursoSeleccionado < 0 || cursoSeleccionado >= cursos.size()) {
                JOptionPane.showMessageDialog(frame, "Seleccione un curso para ver sus tareas.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Mostrar tareas usando el getter de Asignacion_Tareas
            com.ulacit.tareas.Asignacion_Tareas asignacionTareas = new com.ulacit.tareas.Asignacion_Tareas();
            java.util.List<String> tareas = asignacionTareas.getTareasGuardadas();
            if (tareas.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No hay tareas guardadas para mostrar.", "Tareas", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            JTextArea area = new JTextArea();
            area.setEditable(false);
            area.setFont(new Font("Arial", Font.PLAIN, 14));
            StringBuilder sb = new StringBuilder();
            int idx = 1;
            for (String tarea : tareas) {
                sb.append("Tarea ").append(idx++).append(":\n").append(tarea).append("\n\n");
            }
            area.setText(sb.toString());
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(500, 350));
            JOptionPane.showMessageDialog(frame, scroll, "Tareas del curso", JOptionPane.INFORMATION_MESSAGE);
        });
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BorderLayout());
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panelBotones.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        panelBotones.setPreferredSize(new Dimension(Integer.MAX_VALUE, 48));
        JPanel panelDer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        panelDer.setOpaque(false);
        panelDer.add(botonVerTareas);
        panelDer.add(botonCerrar);
        panelDer.setMaximumSize(new Dimension(400, 48));
        panelBotones.add(panelDer, BorderLayout.EAST);

        // Agregar paneles al mainPanel
        mainPanel.add(panelCursos);
        mainPanel.add(panelInfoWrapper);
        mainPanel.add(panelBotones);
        // Agregar mainPanel al frame
        frame.add(mainPanel, BorderLayout.CENTER);
        // Mostrar la ventana
        frame.setVisible(true);
    }

    // Método main para pruebas visuales
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModuloAdministracionCursos adm = new ModuloAdministracionCursos();
            visualizacionCursos vista = new visualizacionCursos(adm);
            vista.showVisualizacionCursosUI();
        });
    }
}
