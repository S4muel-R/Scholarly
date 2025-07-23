
package com.ulacit.gestiondegrupos;

import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class ModuloGestiondeGrupos extends JFrame {
    private final Sistema plataforma = new Sistema();
    private final DefaultComboBoxModel<Estudiante> modeloEstudiantes = new DefaultComboBoxModel<>();
    private final DefaultComboBoxModel<Grupos> modeloGrupos = new DefaultComboBoxModel<>();

    private final JComboBox<Estudiante> comboEstudiantes = new JComboBox<>(modeloEstudiantes);
    private final JComboBox<Grupos> comboGrupos = new JComboBox<>(modeloGrupos);
    
    private JFrame ventanaAnterior;


    public ModuloGestiondeGrupos() {
        super("Plataforma de Grupos de Trabajo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con tabs
        add(crearTabs(), BorderLayout.CENTER);

        // Panel inferior con el botón volver
        JPanel panelBotones = new JPanel();
        JButton btnVolver = new JButton("Volver al menú del curso");
        btnVolver.addActionListener(e -> {
            this.dispose(); // cierra esta ventana
            ventanaAnterior.setVisible(true); // vuelve a mostrar la anterior
        });
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);
    }
   

    private JTabbedPane crearTabs() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Registrar estudiante", panelRegistrarEstudiante());
        tabs.add("Crear grupo", panelCrearGrupo());
        tabs.add("Modificar grupos", panelModificarGrupo());
        return tabs;
    }

    private JPanel panelRegistrarEstudiante() {
        JPanel p = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField txtNombre = new JTextField();
        JTextField txtId = new JTextField();
        JButton btn = new JButton("Registrar");
        btn.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String id = txtId.getText().trim();
            if (!nombre.isEmpty() && !id.isEmpty()) {
                if (plataforma.buscarEstudiante(id) == null) {
                    plataforma.registrarEstudiante(new Estudiante(nombre, id));
                    actualizarCombos();
                    mensaje("Estudiante registrado");
                    txtNombre.setText("");
                    txtId.setText("");
                } else {
                    mensaje("Ya existe un estudiante con ese ID");
                }
            } else mensaje("Complete ambos campos");
        });
        p.add(new JLabel("Nombre:")); p.add(txtNombre);
        p.add(new JLabel("ID:")); p.add(txtId);
        p.add(new JLabel()); p.add(btn);
        return p;
    }

    private JPanel panelCrearGrupo() {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        JTextField txtGrupo = new JTextField();
        JButton btn = new JButton("Crear grupo");
        btn.addActionListener(e -> {
            String nombre = txtGrupo.getText().trim();
            if (!nombre.isEmpty()) {
                if (plataforma.buscarGrupo(nombre) == null) {
                    plataforma.crearGrupo(nombre);
                    actualizarCombos();
                    mensaje("Grupo creado");
                    txtGrupo.setText("");
                } else {
                    mensaje("Ya existe un grupo con ese nombre");
                }
            } else mensaje("Ingrese el nombre del grupo");
        });
        p.add(new JLabel("Nombre del grupo:"), BorderLayout.WEST);
        p.add(txtGrupo, BorderLayout.CENTER);
        p.add(btn, BorderLayout.EAST);
        return p;
    }

    private JPanel panelModificarGrupo() {
        JPanel p = new JPanel(new BorderLayout(10,10));
        JTextArea area = new JTextArea();
        area.setEditable(false);

        JPanel controles = new JPanel(new GridLayout(4,2,5,5));
        JButton asignar = new JButton("Asignar estudiante al grupo");
        JButton quitar = new JButton("Quitar estudiante del grupo");
        JButton eliminarGrupo = new JButton("Eliminar grupo");
        JButton actualizar = new JButton("Actualizar vista");

        asignar.addActionListener(e -> {
            Estudiante est = (Estudiante) comboEstudiantes.getSelectedItem();
            Grupos grupo = (Grupos) comboGrupos.getSelectedItem();
            if (est != null && grupo != null) {
                if (est.getGrupo() == null) {
                    grupo.agregarEstudiante(est);
                    mensaje("Estudiante asignado al grupo");
                } else {
                    mensaje("El estudiante ya pertenece a un grupo");
                }
            }
        });

        quitar.addActionListener(e -> {
            Estudiante est = (Estudiante) comboEstudiantes.getSelectedItem();
            Grupos grupo = (Grupos) comboGrupos.getSelectedItem();
            if (est != null && grupo != null) {
                grupo.eliminarEstudiante(est);
                mensaje("Estudiante quitado del grupo");
            }
        });

        eliminarGrupo.addActionListener(e -> {
            Grupos grupo = (Grupos) comboGrupos.getSelectedItem();
            if (grupo != null) {
                plataforma.eliminarGrupo(grupo.getNombreGrupo());
                actualizarCombos();
                mensaje("Grupo eliminado");
            }
        });

        actualizar.addActionListener(e -> area.setText(verGruposTexto()));

        controles.add(new JLabel("Estudiantes:"));
        controles.add(comboEstudiantes);
        controles.add(new JLabel("Grupos:"));
        controles.add(comboGrupos);
        controles.add(asignar);
        controles.add(quitar);
        controles.add(eliminarGrupo);
        controles.add(actualizar);

        p.add(new JScrollPane(area), BorderLayout.CENTER);
        p.add(controles, BorderLayout.SOUTH);
        return p;
    }

    private void actualizarCombos() {
        modeloEstudiantes.removeAllElements();
        for (Estudiante e : plataforma.getEstudiantes()) modeloEstudiantes.addElement(e);

        modeloGrupos.removeAllElements();
        for (Grupos g : plataforma.getGrupos()) modeloGrupos.addElement(g);

        comboEstudiantes.updateUI();
        comboGrupos.updateUI();
    }

    private String verGruposTexto() {
        if (plataforma.getGrupos().isEmpty()) return "Sin grupos";
        StringBuilder sb = new StringBuilder();
        for (Grupos g : plataforma.getGrupos()) {
            sb.append("Grupo: ").append(g.getNombreGrupo()).append("\n");
            if (g.getIntegrantes().isEmpty()) {
                sb.append("  (sin estudiantes)\n");
            } else {
                for (Estudiante e : g.getIntegrantes()) {
                    sb.append("  - ").append(e.getNombre()).append(" (").append(e.getId()).append(")\n");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void mensaje(String m) {
        JOptionPane.showMessageDialog(this, m);
    }
    
    public ModuloGestiondeGrupos(JFrame ventanaAnterior) {
        this(); // llama al constructor sin parámetros
        this.ventanaAnterior = ventanaAnterior;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModuloGestiondeGrupos().setVisible(true));
    }
}
