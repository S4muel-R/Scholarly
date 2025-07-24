/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.asignacion_tareas;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
/**
 *
 * @author Anderson M
 */
public class Asignacion_Tareas extends JFrame {

    private JTextField txtNombreTarea;
    private JTextArea txtInstrucciones;
    private JTextField txtFechaPublicacion;
    private JTextField txtFechaLimite;
    private JTextField txtPuntaje;
    private JButton btnGuardar;
    private JButton btnVerTareas;

    public Asignacion_Tareas() {
        setTitle("Compartir Archivos y Actividades - Scholarly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 650);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(187, 222, 251));
        panelPrincipal.setBorder(new EmptyBorder(20, 40, 20, 40));
        setContentPane(panelPrincipal);

        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitulo.setBackground(new Color(187, 222, 251));
        JLabel lblTitulo = new JLabel("Scholarly");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(0, 51, 102));
        panelTitulo.add(lblTitulo);
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.Y_AXIS));
        panelCampos.setBackground(new Color(187, 222, 251));
        panelCampos.setBorder(new EmptyBorder(10, 0, 10, 0));

        panelCampos.add(crearEtiqueta("Nombre de la Tarea:"));
        txtNombreTarea = crearCampoTexto();
        panelCampos.add(txtNombreTarea);

        panelCampos.add(crearEtiqueta("Instrucciones:"));
        txtInstrucciones = crearAreaTexto();
        JScrollPane scrollInstrucciones = new JScrollPane(txtInstrucciones);
        scrollInstrucciones.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollInstrucciones.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollInstrucciones.getViewport().setOpaque(false);
        scrollInstrucciones.setBorder(null);
        scrollInstrucciones.setWheelScrollingEnabled(true);
        scrollInstrucciones.setPreferredSize(new Dimension(400, 120));
        panelCampos.add(scrollInstrucciones);

        panelCampos.add(crearEtiqueta("Fecha de Publicación (DÍA/MES/AÑO):"));
        txtFechaPublicacion = crearCampoTexto();
        panelCampos.add(txtFechaPublicacion);

        panelCampos.add(crearEtiqueta("Fecha Límite (DÍA/MES/AÑO):"));
        txtFechaLimite = crearCampoTexto();
        panelCampos.add(txtFechaLimite);

        panelCampos.add(crearEtiqueta("Puntaje:"));
        txtPuntaje = crearCampoTexto();
        panelCampos.add(txtPuntaje);

        panelPrincipal.add(panelCampos, BorderLayout.CENTER);

        // BOTONES
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(187, 222, 251));

        btnGuardar = new JButton("Guardar Tarea");
        btnGuardar.setBackground(new Color(30, 90, 160));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new Dimension(150, 40));
        btnGuardar.addActionListener(e -> guardarTarea());
        panelBoton.add(btnGuardar);

        btnVerTareas = new JButton("Ver Tareas Guardadas");
        btnVerTareas.setBackground(new Color(100, 149, 237));
        btnVerTareas.setForeground(Color.WHITE);
        btnVerTareas.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVerTareas.setFocusPainted(false);
        btnVerTareas.setPreferredSize(new Dimension(200, 40));
        btnVerTareas.addActionListener(e -> verTareasGuardadas());
        panelBoton.add(btnVerTareas);

        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);
    }

    private JTextField crearCampoTexto() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setPreferredSize(new Dimension(400, 40));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        return campo;
    }

    private JTextArea crearAreaTexto() {
        JTextArea area = new JTextArea(6, 20);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setOpaque(false);
        return area;
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 13));
        etiqueta.setBorder(new EmptyBorder(10, 0, 5, 0));
        return etiqueta;
    }

    private void guardarTarea() {
        String nombre = txtNombreTarea.getText().trim();
        String instrucciones = txtInstrucciones.getText().trim();
        String fechaPublicacion = txtFechaPublicacion.getText().trim();
        String fechaLimite = txtFechaLimite.getText().trim();
        String puntaje = txtPuntaje.getText().trim();

        if (nombre.isEmpty() || instrucciones.isEmpty() || fechaPublicacion.isEmpty()
                || fechaLimite.isEmpty() || puntaje.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tareas.txt", true))) {
            writer.write("Tarea: " + nombre);
            writer.newLine();
            writer.write("Instrucciones: " + instrucciones);
            writer.newLine();
            writer.write("Fecha de Publicación: " + fechaPublicacion);
            writer.newLine();
            writer.write("Fecha Límite: " + fechaLimite);
            writer.newLine();
            writer.write("Puntaje: " + puntaje);
            writer.newLine();
            writer.write("------------------------");
            writer.newLine();

            JOptionPane.showMessageDialog(this, "Tarea guardada correctamente.");

            // Mostrar resumen de la tarea guardada
            String resumen = "Tarea guardada:\n"
                    + "Nombre: " + nombre + "\n"
                    + "Instrucciones: " + instrucciones + "\n"
                    + "Fecha de Publicación: " + fechaPublicacion + "\n"
                    + "Fecha Límite: " + fechaLimite + "\n"
                    + "Puntaje: " + puntaje;
            JOptionPane.showMessageDialog(this, resumen, "Resumen de Tarea", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos
            txtNombreTarea.setText("");
            txtInstrucciones.setText("");
            txtFechaPublicacion.setText("");
            txtFechaLimite.setText("");
            txtPuntaje.setText("");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar la tarea.");
        }
    }

    private void verTareasGuardadas() {
        try {
            File archivo = new File("tareas.txt");
            if (!archivo.exists()) {
                JOptionPane.showMessageDialog(this, "No hay tareas guardadas todavía.");
                return;
            }

            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = lector.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
            lector.close();

            JTextArea areaTexto = new JTextArea(contenido.toString());
            areaTexto.setEditable(false);
            areaTexto.setLineWrap(true);
            areaTexto.setWrapStyleWord(true);
            JScrollPane scroll = new JScrollPane(areaTexto);
            scroll.setPreferredSize(new Dimension(500, 300));

            JOptionPane.showMessageDialog(this, scroll, "Tareas Guardadas", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer las tareas.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Asignacion_Tareas ventana = new Asignacion_Tareas();
            ventana.setVisible(true);
        });
    }
}
 