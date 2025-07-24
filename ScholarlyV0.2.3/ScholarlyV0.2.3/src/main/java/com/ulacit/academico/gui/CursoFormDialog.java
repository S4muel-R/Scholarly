package com.ulacit.academico.gui;

import com.ulacit.academico.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CursoFormDialog extends JDialog {

    private JTextField txtNombre, txtCodigo, txtPeriodo, txtHorario, txtAula;
    private JTextField txtProfesorNombre, txtProfesorId;
    private JTextArea txtEstudiantes;
    private JButton btnGuardar, btnCancelar;

    private Curso cursoOriginal;
    private CursoService cursoService;

    public CursoFormDialog(JFrame parent, Curso curso, CursoService service) {
        super(parent, true);
        this.cursoOriginal = curso;
        this.cursoService = service;
        setTitle(curso == null ? "Crear Curso" : "Editar Curso");
        setSize(500, 600);
        setLocationRelativeTo(parent);
        initComponents();
        if (curso != null) cargarDatos(curso);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(10, 2, 5, 5));

        txtNombre = new JTextField();
        txtCodigo = new JTextField();
        txtPeriodo = new JTextField();
        txtProfesorId = new JTextField();
        txtProfesorNombre = new JTextField();
        txtHorario = new JTextField();
        txtAula = new JTextField();
        txtEstudiantes = new JTextArea(5, 30);

        form.add(new JLabel("Nombre del curso:"));
        form.add(txtNombre);
        form.add(new JLabel("Código:"));
        form.add(txtCodigo);
        form.add(new JLabel("Periodo:"));
        form.add(txtPeriodo);
        form.add(new JLabel("ID Profesor:"));
        form.add(txtProfesorId);
        form.add(new JLabel("Nombre Profesor:"));
        form.add(txtProfesorNombre);
        form.add(new JLabel("Horario:"));
        form.add(txtHorario);
        form.add(new JLabel("Aula:"));
        form.add(txtAula);
        form.add(new JLabel("Estudiantes (uno por línea, ID:Nombre):"));
        form.add(new JScrollPane(txtEstudiantes));

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> guardarCurso());
        btnCancelar.addActionListener(e -> dispose());

        JPanel acciones = new JPanel();
        acciones.add(btnGuardar);
        acciones.add(btnCancelar);

        add(form, BorderLayout.CENTER);
        add(acciones, BorderLayout.SOUTH);
    }

    private void cargarDatos(Curso curso) {
        txtNombre.setText(curso.getNombre());
        txtCodigo.setText(curso.getCodigo());
        txtPeriodo.setText(curso.getPeriodo());
        txtProfesorId.setText(curso.getProfesor().getId());
        txtProfesorNombre.setText(curso.getProfesor().getNombre());
        txtHorario.setText(curso.getHorario());
        txtAula.setText(curso.getAula());

        StringBuilder sb = new StringBuilder();
        for (Estudiante e : curso.getEstudiantes()) {
            sb.append(e.getId()).append(":").append(e.getNombre()).append("\n");
        }
        txtEstudiantes.setText(sb.toString());
        txtCodigo.setEnabled(false);
        txtNombre.setEnabled(false);
        txtPeriodo.setEnabled(false);
    }

    private void guardarCurso() {
        String nombre = txtNombre.getText().trim();
        String codigo = txtCodigo.getText().trim();
        String periodo = txtPeriodo.getText().trim();
        String horario = txtHorario.getText().trim();
        String aula = txtAula.getText().trim();
        String profId = txtProfesorId.getText().trim();
        String profNombre = txtProfesorNombre.getText().trim();

        Profesor profesor = new Profesor(profNombre, profId);

        List<Estudiante> estudiantes = new ArrayList<>();
        for (String linea : txtEstudiantes.getText().split("\n")) {
            if (linea.trim().isEmpty()) continue;
            String[] partes = linea.split(":");
            if (partes.length == 2) {
                estudiantes.add(new Estudiante(partes[1].trim(), partes[0].trim()));
            }
        }

        Curso nuevo = new Curso(nombre, codigo, periodo, profesor, estudiantes, horario, aula);

        if (cursoOriginal == null) {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Desea guardar este curso?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String resultado = cursoService.agregarCurso(nuevo);
                if (resultado.equals("OK")) {
                    JOptionPane.showMessageDialog(this, "Curso guardado correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error: " + resultado);
                }
            }
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Guardar cambios al curso?", "Confirmar edición", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                cursoService.actualizarCurso(cursoOriginal, nuevo);
                JOptionPane.showMessageDialog(this, "Curso actualizado.");
                dispose();
            }
        }
    }
}
