package com.ulacit.calificaciones;

import com.ulacit.menucurso.menucurso;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    private GestorCursos gestor;
    private JComboBox<String> comboCursos;
    private JComboBox<String> comboItems;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextArea historialArea;
    private JFrame ventanaAnterior;

    public VentanaPrincipal() {
        gestor = new GestorCursos();

        setTitle("Gestión de Calificaciones");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        // Panel superior con JComboBoxes
        JPanel panelSuperior = new JPanel(new FlowLayout());

        // Aquí corregido para cargar nombres de cursos
        comboCursos = new JComboBox<>(gestor.cursos.keySet().toArray(new String[0]));
        comboCursos.addActionListener(e -> actualizarItems());

        comboItems = new JComboBox<>();
        comboItems.addActionListener(e -> actualizarTabla());

        panelSuperior.add(new JLabel("Curso:"));
        panelSuperior.add(comboCursos);
        panelSuperior.add(new JLabel("Ítem:"));
        panelSuperior.add(comboItems);

        add(panelSuperior, BorderLayout.NORTH);

        // Tabla con modelo para mostrar notas y permitir editar solo columna nota
        modeloTabla = new DefaultTableModel(new String[]{"Estudiante", "ID", "Fecha", "Nota"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Solo columna Nota editable
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);
        JScrollPane scrollTabla = new JScrollPane(tabla);
        add(scrollTabla, BorderLayout.CENTER);

        // Panel inferior con botones y área de historial
        JPanel panelInferior = new JPanel(new BorderLayout());

        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());

        JButton btnHistorial = new JButton("Ver Historial");
        btnHistorial.addActionListener(e -> mostrarHistorial());
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            this.dispose(); // cierra esta ventana
            ventanaAnterior.setVisible(true); // vuelve a mostrar la anterior
        });

        JPanel botonesPanel = new JPanel(new FlowLayout());
        botonesPanel.add(btnGuardar);
        botonesPanel.add(btnHistorial);
        botonesPanel.add(btnVolver);

        panelInferior.add(botonesPanel, BorderLayout.NORTH);

        historialArea = new JTextArea(5, 20);
        historialArea.setEditable(false);
        JScrollPane scrollHistorial = new JScrollPane(historialArea);
        panelInferior.add(scrollHistorial, BorderLayout.CENTER);

        add(panelInferior, BorderLayout.SOUTH);

        actualizarItems();
    }

    private void actualizarItems() {
        String curso = (String) comboCursos.getSelectedItem();
        comboItems.removeAllItems();
        if (curso != null) {
            List<ItemEvaluativo> items = gestor.getItems(curso);
            for (ItemEvaluativo item : items) {
                comboItems.addItem(item.getNombre());
            }
            actualizarTabla();
        }
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        String curso = (String) comboCursos.getSelectedItem();
        String itemNombre = (String) comboItems.getSelectedItem();
        if (curso == null || itemNombre == null) return;

        List<ItemEvaluativo> items = gestor.getItems(curso);
        ItemEvaluativo item = null;
        for (ItemEvaluativo i : items) {
            if (i.getNombre().equals(itemNombre)) {
                item = i;
                break;
            }
        }
        if (item == null) return;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Estudiante est : gestor.getEstudiantes(curso)) {
            double nota = 0.0;
            List<Calificacion> cals = gestor.getCalificaciones(curso);
            for (Calificacion c : cals) {
                if (c.getEstudiante().getId().equals(est.getId()) &&
                    c.getItem().getNombre().equals(item.getNombre())) {
                    nota = c.getNota();
                    break;
                }
            }
            modeloTabla.addRow(new Object[]{est.getNombre(), est.getId(), sdf.format(item.getFecha()), nota});
        }
    }

    private void guardarCambios() {
        String curso = (String) comboCursos.getSelectedItem();
        String itemNombre = (String) comboItems.getSelectedItem();
        if (curso == null || itemNombre == null) return;

        List<ItemEvaluativo> items = gestor.getItems(curso);
        ItemEvaluativo item = null;
        for (ItemEvaluativo i : items) {
            if (i.getNombre().equals(itemNombre)) {
                item = i;
                break;
            }
        }
        if (item == null) return;

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String id = (String) modeloTabla.getValueAt(i, 1);
            double nuevaNota;
            try {
                nuevaNota = Double.parseDouble(modeloTabla.getValueAt(i, 3).toString());
                if (nuevaNota < 0 || nuevaNota > 100) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Nota inválida para estudiante con ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Estudiante est = null;
            for (Estudiante e : gestor.getEstudiantes(curso)) {
                if (e.getId().equals(id)) {
                    est = e;
                    break;
                }
            }
            if (est != null) {
                gestor.modificarNota(curso, est, item, nuevaNota);
            }
        }
        JOptionPane.showMessageDialog(this, "Notas guardadas correctamente.");
    }

    private void mostrarHistorial() {
        historialArea.setText("");
        for (HistorialCambio h : gestor.getHistorial()) {
            historialArea.append(h.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
