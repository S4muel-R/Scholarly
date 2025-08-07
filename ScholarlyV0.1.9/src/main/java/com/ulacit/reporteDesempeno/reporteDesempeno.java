package com.ulacit.reporteDesempeno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class reporteDesempeno extends JFrame {
    // Modo: true = profesor (reporte grupal), false = estudiante (solo su propio reporte)
    private boolean esProfesor;
    private String cursoSeleccionado;
    private String estudianteId; // solo para modo estudiante
    private java.util.List<String> idsEstudiantes; // ids reales del curso

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JLabel infoLabel;

    // Simulación de datos locales (luego se integrará con calificaciones y asistencias reales)
    private List<Map<String, Object>> datosEstudiantes;

    // Nuevo constructor para modo profesor con lista real de estudiantes
    public reporteDesempeno(String curso, java.util.List<String> idsEstudiantes) {
        this.esProfesor = true;
        this.cursoSeleccionado = curso;
        this.idsEstudiantes = idsEstudiantes;
        this.estudianteId = null;
        inicializar();
    }

    // Constructor para modo estudiante (solo su propio reporte)
    public reporteDesempeno(String curso, String estudianteId) {
        this.esProfesor = false;
        this.cursoSeleccionado = curso;
        this.estudianteId = estudianteId;
        this.idsEstudiantes = null;
        inicializar();
    }

    private void inicializar() {
        setTitle("Reporte de Desempeño - " + cursoSeleccionado);
        setSize(740, 460);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color azulFondo = new Color(187, 222, 251);
        getContentPane().setBackground(azulFondo);

        infoLabel = new JLabel("", SwingConstants.CENTER);
        infoLabel.setOpaque(true);
        infoLabel.setBackground(azulFondo);
        add(infoLabel, BorderLayout.NORTH);

        // Panel central para la tabla, con fondo azul
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(azulFondo);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // margen de 10px en todos los lados

        // Columnas: Nombre, ID, Nota, Asistencia (%)
        modeloTabla = new DefaultTableModel(new String[]{"Nombre", "ID", "Nota", "Asistencia (%)"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false; // No editable
            }
        };
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(24);
        tabla.setBackground(Color.WHITE);
        tabla.setFillsViewportHeight(true);

        JScrollPane scrollTabla = new JScrollPane(tabla);
        scrollTabla.getViewport().setBackground(Color.WHITE);
        scrollTabla.setBackground(azulFondo);
        panelCentral.add(scrollTabla, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        cargarDatosSimulados(); // Luego se reemplaza por integración real
        mostrarDatos();
    }

    // Simulación de datos (luego se reemplaza por integración real)
    private void cargarDatosSimulados() {
        datosEstudiantes = new ArrayList<>();
        if (cursoSeleccionado == null) return;

        // Si se pasa la lista real de ids, mostrar solo esos estudiantes
        if (idsEstudiantes != null && !idsEstudiantes.isEmpty()) {
            // Simulación: asociar nombres y notas a los ids conocidos
            for (String id : idsEstudiantes) {
                switch (id.trim()) {
                    case "A1": datosEstudiantes.add(crearEstudiante("Ana", "A1", 80, 95)); break;
                    case "A2": datosEstudiantes.add(crearEstudiante("Luis", "A2", 70, 90)); break;
                    case "A3": datosEstudiantes.add(crearEstudiante("María", "A3", 85, 100)); break;
                    case "B1": datosEstudiantes.add(crearEstudiante("Pedro", "B1", 88, 92)); break;
                    case "B2": datosEstudiantes.add(crearEstudiante("Carla", "B2", 75, 85)); break;
                    case "B3": datosEstudiantes.add(crearEstudiante("Sofía", "B3", 95, 98)); break;
                    case "B4": datosEstudiantes.add(crearEstudiante("Laura", "B4", 90, 97)); break;
                }
            }
        } else {
            // Fallback: simulación antigua
            if (cursoSeleccionado.equals("Curso A")) {
                datosEstudiantes.add(crearEstudiante("Ana", "A1", 80, 95));
                datosEstudiantes.add(crearEstudiante("Luis", "A2", 70, 90));
                datosEstudiantes.add(crearEstudiante("María", "A3", 85, 100));
            } else if (cursoSeleccionado.equals("Curso B")) {
                datosEstudiantes.add(crearEstudiante("Pedro", "B1", 88, 92));
                datosEstudiantes.add(crearEstudiante("Carla", "B2", 75, 85));
                datosEstudiantes.add(crearEstudiante("Sofía", "B3", 95, 98));
            }
        }
        // Si no hay datos, la lista queda vacía
    }

    private Map<String, Object> crearEstudiante(String nombre, String id, double nota, double asistencia) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombre);
        map.put("id", id);
        map.put("nota", nota);
        map.put("asistencia", asistencia);
        return map;
    }

    private void mostrarDatos() {
        modeloTabla.setRowCount(0);
        if (datosEstudiantes == null || datosEstudiantes.isEmpty()) {
            infoLabel.setText("No hay calificaciones registradas para este curso.");
            return;
        }

        if (esProfesor) {
            // Validación: solo profesores del curso pueden ver el reporte grupal
            // (Validación real se hará al integrar con datos de usuario y curso)
            infoLabel.setText("Reporte grupal del curso: " + cursoSeleccionado);
            for (Map<String, Object> est : datosEstudiantes) {
                modeloTabla.addRow(new Object[]{
                        est.get("nombre"),
                        est.get("id"),
                        est.get("nota"),
                        est.get("asistencia")
                });
            }
        } else {
            // Solo mostrar el estudiante correspondiente
            boolean encontrado = false;
            for (Map<String, Object> est : datosEstudiantes) {
                if (est.get("id").equals(estudianteId)) {
                    modeloTabla.addRow(new Object[]{
                            est.get("nombre"),
                            est.get("id"),
                            est.get("nota"),
                            est.get("asistencia")
                    });
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                infoLabel.setText("No hay reporte disponible para este estudiante.");
            } else {
                infoLabel.setText("Reporte individual para estudiante: " + estudianteId);
            }
        }
    }

    // Ejemplo de uso local actualizado (opcional, solo para pruebas)
    /*
    public static void main(String[] args) {
        // Modo profesor (reporte grupal)
        java.util.List<String> ids = java.util.Arrays.asList("A1", "A2", "A3");
        SwingUtilities.invokeLater(() -> new reporteDesempeno("Curso A", ids).setVisible(true));

        // Modo estudiante (solo su propio reporte)
        // SwingUtilities.invokeLater(() -> new reporteDesempeno("Curso A", "A2").setVisible(true));
    }
    */
}
