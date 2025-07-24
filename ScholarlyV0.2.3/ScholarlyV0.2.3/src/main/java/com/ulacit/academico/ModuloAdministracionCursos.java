

package com.ulacit.academico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Módulo de administración de cursos para Scholarly.
 * Permite crear, editar y visualizar cursos en una interfaz moderna.
 */
public class ModuloAdministracionCursos {
    // Lista de cursos (cada curso es un String con campos separados por '|')
    private final List<String> cursos = new ArrayList<>();
    private int cursoSeleccionado = -1;

    // Runnables para refrescar paneles
    private Runnable refrescarCursos;
    private Runnable refrescarPanelInfo;

    /**
     * Método main para pruebas locales.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModuloAdministracionCursos().showAdminCursosUI());
    }

    /**
     * Muestra la interfaz principal de administración de cursos.
     */
    public void showAdminCursosUI() {
        JFrame frame = new JFrame("Scholarly - Administración de Cursos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel principal y de cursos
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(187, 222, 251));

        JPanel panelCursos = new JPanel(new BorderLayout());
        panelCursos.setBackground(new Color(187, 222, 251));
        panelCursos.setPreferredSize(new Dimension(900, 180));

        JPanel panelBarras = new JPanel();
        panelBarras.setLayout(new BoxLayout(panelBarras, BoxLayout.Y_AXIS));
        panelBarras.setBackground(new Color(187, 222, 251));

        JScrollPane scrollBarras = new JScrollPane(panelBarras);
        scrollBarras.setBorder(null);
        scrollBarras.getViewport().setBackground(new Color(187, 222, 251));
        scrollBarras.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBarras.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        JLabel sinCursos = new JLabel("Sin cursos para mostrar.", SwingConstants.CENTER);
        sinCursos.setFont(new Font("Arial", Font.BOLD, 24));

        // Panel de información inferior
        JPanel panelInfoWrapper = new JPanel(new BorderLayout());
        panelInfoWrapper.setBackground(new Color(187, 222, 251));
        panelInfoWrapper.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBackground(new Color(187, 222, 251));
        panelInfo.setPreferredSize(new Dimension(900, 400));
        panelInfoWrapper.add(panelInfo, BorderLayout.CENTER);

        // Refresca el panel de información del curso seleccionado
        refrescarPanelInfo = () -> {
            panelInfo.removeAll();
            if (cursos.isEmpty()) {
                panelInfo.setBackground(new Color(187, 222, 251));
            } else if (cursoSeleccionado >= 0 && cursoSeleccionado < cursos.size()) {
                panelInfo.setBackground(new Color(187, 222, 251));
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

                // Scroll para la tarjeta
                JScrollPane scrollInfo = new JScrollPane(tarjetaPanel);
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

        // Refresca el panel de barras de cursos
        refrescarCursos = () -> {
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
                            refrescarCursos.run();
                            refrescarPanelInfo.run();
                        }
                    });
                    JPanel contenedor = new JPanel(new BorderLayout());
                    contenedor.setOpaque(false);
                    int margenTop = 10, margenLados = 20, margenBottom = 0;
                    int alturaBarra = 36;
                    int alturaContenedor = margenTop + alturaBarra + margenBottom;
                    contenedor.setBorder(BorderFactory.createEmptyBorder(margenTop, margenLados, margenBottom, margenLados));
                    contenedor.setPreferredSize(new Dimension(940, alturaContenedor));
                    contenedor.setMaximumSize(new Dimension(940, alturaContenedor));
                    contenedor.add(barra, BorderLayout.CENTER);
                    panelBarras.add(contenedor);
                    idx++;
                }
                panelCursos.removeAll();
                panelCursos.add(scrollBarras, BorderLayout.CENTER);
            }
            panelCursos.revalidate();
            panelCursos.repaint();
        };

        refrescarCursos.run();
        refrescarPanelInfo.run();

        // Panel de botones
        JButton botonCrear = new JButton("Crear Curso");
        botonCrear.addActionListener(e -> mostrarDialogoCrearCurso(frame));
        JButton botonEditar = new JButton("Editar Curso");
        botonEditar.addActionListener(e -> mostrarDialogoEditarCurso(frame));
        JButton botonVolver = new JButton("Volver");
        // Sin funcionalidad por ahora

        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        botonCerrarSesion.addActionListener(e -> {
            frame.dispose();
            // Volver al login de Main.java
            javax.swing.SwingUtilities.invokeLater(() -> {
                new com.ulacit.login.Login(() -> {
                    com.ulacit.app.Main.mostrarMenu();
                }).setVisible(true);
            });
        });

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BorderLayout());
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        panelBotones.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        panelBotones.setPreferredSize(new Dimension(Integer.MAX_VALUE, 48));

        // Panel izquierdo para 'Cerrar Sesión'
        JPanel panelIzq = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panelIzq.setOpaque(false);
        panelIzq.add(botonCerrarSesion);
        panelIzq.setMaximumSize(new Dimension(200, 48));

        // Panel derecho para el resto de botones
        JPanel panelDer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        panelDer.setOpaque(false);
        panelDer.add(botonVolver);
        panelDer.add(botonEditar);
        panelDer.add(botonCrear);
        panelDer.setMaximumSize(new Dimension(400, 48));

        panelBotones.add(panelIzq, BorderLayout.WEST);
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

    /**
     * Muestra el diálogo para editar el curso seleccionado.
     */
    private void mostrarDialogoEditarCurso(JFrame parent) {
        if (cursoSeleccionado < 0 || cursoSeleccionado >= cursos.size()) {
            JOptionPane.showMessageDialog(parent, "Seleccione un curso para editar.", "Sin selección", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String[] partes = cursos.get(cursoSeleccionado).split("\\|");
        String nombre = partes.length > 0 ? partes[0].trim() : "";
        String codigo = partes.length > 1 ? partes[1].trim() : "";
        String periodo = partes.length > 2 ? partes[2].trim() : "";
        String horario = partes.length > 3 ? partes[3].trim() : "";
        String aula = partes.length > 4 ? partes[4].trim() : "";
        String profesor = partes.length > 5 ? partes[5].trim() : "";
        String estudiantes = partes.length > 6 ? partes[6].trim() : "";

        JDialog editarCursoDialog = new JDialog(parent, "Editar Curso", true);
        editarCursoDialog.setSize(650, 500);
        editarCursoDialog.setLocationRelativeTo(parent);
        editarCursoDialog.getContentPane().setBackground(new Color(187, 222, 251));
        editarCursoDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.WEST;

        // Nombre, código y periodo (solo lectura)
        gbc.gridx = 0; gbc.gridy = 0;
        editarCursoDialog.add(new JLabel("Nombre del curso:"), gbc);
        gbc.gridx = 1;
        JTextField fieldNombre = new JTextField(nombre, 28);
        fieldNombre.setEditable(false);
        editarCursoDialog.add(fieldNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        editarCursoDialog.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        JTextField fieldCodigo = new JTextField(codigo, 28);
        fieldCodigo.setEditable(false);
        editarCursoDialog.add(fieldCodigo, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        editarCursoDialog.add(new JLabel("Periodo:"), gbc);
        gbc.gridx = 1;
        JTextField fieldPeriodo = new JTextField(periodo, 28);
        fieldPeriodo.setEditable(false);
        editarCursoDialog.add(fieldPeriodo, gbc);

        // Horario (editable)
        gbc.gridx = 0; gbc.gridy = 3;
        editarCursoDialog.add(new JLabel("Horario:"), gbc);
        gbc.gridx = 1;
        JTextField fieldHorario = new JTextField(horario, 28);
        editarCursoDialog.add(fieldHorario, gbc);

        // Aula (editable)
        gbc.gridx = 0; gbc.gridy = 4;
        editarCursoDialog.add(new JLabel("Aula:"), gbc);
        gbc.gridx = 1;
        JTextField fieldAula = new JTextField(aula, 28);
        editarCursoDialog.add(fieldAula, gbc);

        // Profesor (editable)
        gbc.gridx = 0; gbc.gridy = 5;
        editarCursoDialog.add(new JLabel("Profesor:"), gbc);
        gbc.gridx = 1;
        JTextField fieldProfesor = new JTextField(profesor, 28);
        editarCursoDialog.add(fieldProfesor, gbc);

        // Estudiantes (editable)
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        editarCursoDialog.add(new JLabel("Estudiantes (separados por coma):"), gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextArea areaEstudiantes = new JTextArea(estudiantes, 3, 28);
        areaEstudiantes.setLineWrap(true);
        areaEstudiantes.setWrapStyleWord(true);
        JScrollPane scrollEstudiantes = new JScrollPane(areaEstudiantes);
        editarCursoDialog.add(scrollEstudiantes, gbc);

        // Panel de botones
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        JPanel panelBotonesForm = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotonesForm.setOpaque(false);
        JButton guardarBtn = new JButton("Guardar cambios");
        JButton cancelarBtn = new JButton("Cancelar");
        panelBotonesForm.add(guardarBtn);
        panelBotonesForm.add(cancelarBtn);
        editarCursoDialog.add(panelBotonesForm, gbc);

        cancelarBtn.addActionListener(ev -> editarCursoDialog.dispose());

        guardarBtn.addActionListener(ev -> {
            String nuevoHorario = fieldHorario.getText().trim();
            String nuevaAula = fieldAula.getText().trim();
            String nuevoProfesor = fieldProfesor.getText().trim();
            String nuevosEstudiantes = areaEstudiantes.getText().trim();

            if (nuevoHorario.isEmpty() || nuevaAula.isEmpty() || nuevoProfesor.isEmpty() || nuevosEstudiantes.isEmpty()) {
                JOptionPane.showMessageDialog(
                        editarCursoDialog,
                        "Por favor, complete todos los campos antes de guardar.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    editarCursoDialog,
                    "¿Está seguro que desea guardar los cambios?",
                    "Confirmar edición",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                String resumen = nombre + " | " + codigo + " | " + periodo + " | " + nuevoHorario + " | " + nuevaAula + " | " + nuevoProfesor + " | " + nuevosEstudiantes;
                cursos.set(cursoSeleccionado, resumen);
                refrescarCursos.run();
                refrescarPanelInfo.run();
                JOptionPane.showMessageDialog(editarCursoDialog, "Curso editado exitosamente.");
                editarCursoDialog.dispose();
            }
        });

        editarCursoDialog.setVisible(true);
    }

    /**
     * Muestra el diálogo para crear un nuevo curso.
     */
    private void mostrarDialogoCrearCurso(JFrame parent) {
        JDialog crearCursoDialog = new JDialog(parent, "Crear Curso", true);
        crearCursoDialog.setSize(650, 600);
        crearCursoDialog.setLocationRelativeTo(parent);
        crearCursoDialog.getContentPane().setBackground(new Color(187, 222, 251));
        crearCursoDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.anchor = GridBagConstraints.WEST;

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        crearCursoDialog.add(new JLabel("Nombre del curso:"), gbc);
        gbc.gridx = 1;
        JTextField fieldNombre = new JTextField(28);
        crearCursoDialog.add(fieldNombre, gbc);

        // Código
        gbc.gridx = 0; gbc.gridy = 1;
        crearCursoDialog.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        JTextField fieldCodigo = new JTextField(28);
        crearCursoDialog.add(fieldCodigo, gbc);

        // Periodo
        gbc.gridx = 0; gbc.gridy = 2;
        crearCursoDialog.add(new JLabel("Periodo:"), gbc);
        gbc.gridx = 1;
        JTextField fieldPeriodo = new JTextField(28);
        crearCursoDialog.add(fieldPeriodo, gbc);

        // Horario
        gbc.gridx = 0; gbc.gridy = 3;
        crearCursoDialog.add(new JLabel("Horario:"), gbc);
        gbc.gridx = 1;
        JTextField fieldHorario = new JTextField(28);
        crearCursoDialog.add(fieldHorario, gbc);

        // Aula
        gbc.gridx = 0; gbc.gridy = 4;
        crearCursoDialog.add(new JLabel("Aula:"), gbc);
        gbc.gridx = 1;
        JTextField fieldAula = new JTextField(28);
        crearCursoDialog.add(fieldAula, gbc);

        // Profesor
        gbc.gridx = 0; gbc.gridy = 5;
        crearCursoDialog.add(new JLabel("Profesor:"), gbc);
        gbc.gridx = 1;
        JTextField fieldProfesor = new JTextField(28);
        crearCursoDialog.add(fieldProfesor, gbc);

        // Estudiantes (label)
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        crearCursoDialog.add(new JLabel("Estudiantes (separados por coma):"), gbc);

        // Estudiantes (área de texto)
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextArea areaEstudiantes = new JTextArea(3, 28);
        areaEstudiantes.setLineWrap(true);
        areaEstudiantes.setWrapStyleWord(true);
        JScrollPane scrollEstudiantes = new JScrollPane(areaEstudiantes);
        crearCursoDialog.add(scrollEstudiantes, gbc);

        // Panel de botones
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        JPanel panelBotonesForm = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panelBotonesForm.setOpaque(false);
        JButton guardarBtn = new JButton("Guardar");
        JButton cancelarBtn = new JButton("Cancelar");
        panelBotonesForm.add(guardarBtn);
        panelBotonesForm.add(cancelarBtn);
        crearCursoDialog.add(panelBotonesForm, gbc);

        cancelarBtn.addActionListener(ev -> crearCursoDialog.dispose());

        guardarBtn.addActionListener(ev -> {
            String nombre = fieldNombre.getText().trim();
            String codigo = fieldCodigo.getText().trim();
            String periodo = fieldPeriodo.getText().trim();
            String horario = fieldHorario.getText().trim();
            String aula = fieldAula.getText().trim();
            String profesor = fieldProfesor.getText().trim();
            String estudiantes = areaEstudiantes.getText().trim();

            if (nombre.isEmpty() || codigo.isEmpty() || periodo.isEmpty() || horario.isEmpty() || aula.isEmpty() || profesor.isEmpty() || estudiantes.isEmpty()) {
                JOptionPane.showMessageDialog(
                        crearCursoDialog,
                        "Por favor, complete todos los campos antes de guardar.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    crearCursoDialog,
                    "¿Está seguro que desea guardar este curso?",
                    "Confirmar guardado",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                String resumen = nombre + " | " + codigo + " | " + periodo + " | " + horario + " | " + aula + " | " + profesor + " | " + estudiantes;
                cursos.add(resumen);
                refrescarCursos.run();
                refrescarPanelInfo.run();
                JOptionPane.showMessageDialog(crearCursoDialog, "Curso guardado exitosamente.");
                crearCursoDialog.dispose();
            }
        });

        crearCursoDialog.setVisible(true);
    }
}
