
package com.ulacit.foros;

/**
 *
 * @author penge
 */
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Foro extends JFrame {
    private DefaultListModel<String> modeloForos = new DefaultListModel<>();
    private JList<String> listaForos = new JList<>(modeloForos);
    private ArrayList<String> descripciones = new ArrayList<>();
    private JFrame ventanaAnterior;

    // Almacena respuestas y subrespuestas
    private Map<String, List<Comentario>> comentariosPorForo = new HashMap<>();

    public Foro() {
        setTitle("Gestión de Foros Académicos - Profesor");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Botones principales
        JButton btnCrear = new JButton("Crear foro");
        JButton btnEliminar = new JButton("Eliminar foro");
        JButton btnVolver = new JButton("Volver");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCrear);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);

        JScrollPane scrollPane = new JScrollPane(listaForos);
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnCrear.addActionListener(e -> crearForo());
        btnEliminar.addActionListener(e -> eliminarForo());
         btnVolver.addActionListener(e -> {
            this.dispose(); // cierra esta ventana
            ventanaAnterior.setVisible(true); // vuelve a mostrar la anterior
        });
        panelBotones.add(btnVolver);

        listaForos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = listaForos.locationToIndex(evt.getPoint());
                    if (index != -1) {
                        String titulo = modeloForos.get(index);
                        String descripcion = descripciones.get(index);
                        abrirForo(titulo, descripcion);
                    }
                }
            }
        });
    }

    private void crearForo() {
        JTextField tituloField = new JTextField();
        JTextArea descripcionArea = new JTextArea(5, 20);
        JScrollPane scroll = new JScrollPane(descripcionArea);

        Object[] inputs = {
                "Título del foro:", tituloField,
                "Descripción:", scroll
        };

        int result;
        boolean datosValidos = false;

        do {
            result = JOptionPane.showConfirmDialog(
                    this,
                    inputs,
                    "Nuevo Foro",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION) {
                return; // Canceló la creación
            }

            String titulo = tituloField.getText().trim();
            String descripcion = descripcionArea.getText().trim();

            if (titulo.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Ambos campos son obligatorios.\nPor favor, completa el título y la descripción.",
                        "Campos requeridos",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                // Agregar foro válido
                modeloForos.addElement(titulo);
                descripciones.add(descripcion);
                comentariosPorForo.put(titulo, new ArrayList<>());
                datosValidos = true;
            }

        } while (!datosValidos);
    }

    private void eliminarForo() {
        int index = listaForos.getSelectedIndex();
        if (index != -1) {
            String foro = modeloForos.get(index);

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estás seguro que deseas eliminar el foro:\n\"" + foro + "\"?",
                    "Confirmar eliminación de foro",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                modeloForos.remove(index);
                descripciones.remove(index);
                comentariosPorForo.remove(foro);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un foro para eliminar.");
        }
    }

    private void abrirForo(String titulo, String descripcion) {
        JFrame ventanaForo = new JFrame("Foro: " + titulo);
        ventanaForo.setSize(550, 400);
        ventanaForo.setLocationRelativeTo(null);

        DefaultListModel<Comentario> modeloComentarios = new DefaultListModel<>();
        JList<Comentario> listaComentarios = new JList<>(modeloComentarios);
        listaComentarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaComentarios.setCellRenderer(new ComentarioRenderer());

        // Cargar comentarios
        List<Comentario> comentarios = comentariosPorForo.get(titulo);
        comentarios.forEach(modeloComentarios::addElement);

        JTextField campoNuevoComentario = new JTextField(30);
        JButton btnComentar = new JButton("Responder al foro");

        btnComentar.addActionListener(e -> {
            String texto = campoNuevoComentario.getText().trim();
            if (!texto.isEmpty()) {
                Comentario nuevo = new Comentario(texto);
                comentarios.add(nuevo);
                modeloComentarios.addElement(nuevo);
                campoNuevoComentario.setText("");
            }
        });

        JButton btnResponderComentario = new JButton("Responder a comentario");
        JButton btnEliminarComentario = new JButton("Eliminar comentario");

        btnResponderComentario.addActionListener(e -> {
            int index = listaComentarios.getSelectedIndex();
            if (index != -1) {
                Comentario seleccionado = listaComentarios.getModel().getElementAt(index);

                String respuesta = JOptionPane.showInputDialog(this, "Respuesta al comentario:");
                if (respuesta != null && !respuesta.trim().isEmpty()) {
                    seleccionado.subcomentarios.add(new Comentario(respuesta.trim()));

                    // Forzar la actualización visual inmediata del comentario modificado
                    modeloComentarios.setElementAt(seleccionado, index);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un comentario para responder.");
            }
        });


        btnEliminarComentario.addActionListener(e -> {
            int index = listaComentarios.getSelectedIndex();
            if (index != -1) {
                Comentario seleccionado = listaComentarios.getSelectedValue();

                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "¿Deseas eliminar este comentario?\n\"" + seleccionado.texto + "\"",
                        "Confirmar eliminación de comentario",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    comentarios.remove(seleccionado);
                    modeloComentarios.remove(index);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un comentario para eliminar.");
            }
        });

        JPanel panelInferior = new JPanel();
        panelInferior.add(campoNuevoComentario);
        panelInferior.add(btnComentar);

        JPanel panelAcciones = new JPanel();
        panelAcciones.add(btnResponderComentario);
        panelAcciones.add(btnEliminarComentario);

        ventanaForo.add(new JLabel("Descripción: " + descripcion), BorderLayout.NORTH);
        ventanaForo.add(new JScrollPane(listaComentarios), BorderLayout.CENTER);
        ventanaForo.add(panelInferior, BorderLayout.SOUTH);
        ventanaForo.add(panelAcciones, BorderLayout.EAST);

        ventanaForo.setVisible(true);
    }

    // Clase para comentarios con subcomentarios
    static class Comentario {
        String texto;
        List<Comentario> subcomentarios = new ArrayList<>();

        Comentario(String texto) {
            this.texto = texto;
        }

        public String toString() {
            return texto;
        }
    }

    // Renderiza los comentarios con subcomentarios
    static class ComentarioRenderer extends JTextArea implements ListCellRenderer<Comentario> {
        public ComentarioRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Comentario> list, Comentario value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            StringBuilder sb = new StringBuilder(value.texto);
            for (Comentario sub : value.subcomentarios) {
                sb.append("\n   ↳ ").append(sub.texto);
            }

            setText(sb.toString());
            setBackground(isSelected ? new Color(220, 230, 255) : Color.WHITE);
            setForeground(Color.BLACK);
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Foro().setVisible(true));
    }
}
