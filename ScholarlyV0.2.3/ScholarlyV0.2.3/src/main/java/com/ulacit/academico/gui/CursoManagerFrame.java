package com.ulacit.academico.gui;

import com.ulacit.academico.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collections;

public class CursoManagerFrame extends JFrame {
    private CursoService cursoService = new CursoService();
    private JTable tablaCursos;
    private DefaultTableModel modeloTabla;

    public CursoManagerFrame() {
        setTitle("Administración de Cursos");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();

        System.out.println("CursoManagerFrame inicializado");
    }

    private void initComponents() {
        modeloTabla = new DefaultTableModel(new Object[]{"Nombre", "Código", "Periodo", "Profesor", "Horario", "Aula"}, 0);
        tablaCursos = new JTable(modeloTabla);
        tablaCursos.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tablaCursos);
        scrollPane.setPreferredSize(new Dimension(750, 250));

        JButton btnCrear = new JButton("Crear Curso");
        JButton btnEditar = new JButton("Editar Curso");
        JButton btnCerrar = new JButton("Cerrar sesión");

        btnCrear.addActionListener(e -> abrirFormulario(null));
        btnEditar.addActionListener(e -> {
            int row = tablaCursos.getSelectedRow();
            if (row >= 0) {
                Curso curso = cursoService.obtenerCursos().get(row);
                abrirFormulario(curso);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un curso para editar.");
            }
        });
        btnCerrar.addActionListener(e -> dispose());

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.add(btnCrear);
        panelBotones.add(btnEditar);
        panelBotones.add(btnCerrar);

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);

        // Agregar curso de ejemplo para probar visualmente
        cursoService.agregarCurso(new Curso(
            "Ejemplo", "CURS001", "2025-2",
            new Profesor("Prof. Demo", "PROF001"),
            Collections.singletonList(new Estudiante("Alumno Demo", "EST001")),
            "Lun 8am", "Aula 1"
        ));

        cargarCursos();
    }

    private void cargarCursos() {
        modeloTabla.setRowCount(0);
        for (Curso c : cursoService.listarCursosOrdenados()) {
            modeloTabla.addRow(new Object[]{
                c.getNombre(), c.getCodigo(), c.getPeriodo(),
                c.getProfesor().getNombre(), c.getHorario(), c.getAula()
            });
        }
    }

    private void abrirFormulario(Curso cursoEditar) {
        CursoFormDialog form = new CursoFormDialog(this, cursoEditar, cursoService);
        form.setVisible(true);
        cargarCursos();
    }
}
