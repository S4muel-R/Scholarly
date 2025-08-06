package com.ulacit.Notificaciones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.ulacit.login.LoginApp;
import com.ulacit.dashboard.moddashboardclass;

/**
 * Panel de anuncios para administración y visualización.
 * Permite crear, editar, eliminar y programar anuncios.
 */
public class PanelAnuncios extends JPanel {
    private final String usuarioActual;
    private final String cursoId;
    private final AnunciosManager manager;
    private final DefaultListModel<AnunciosManager.Anuncio> modeloLista = new DefaultListModel<>();
    private final JList<AnunciosManager.Anuncio> listaAnuncios = new JList<>(modeloLista);
    private final JTextArea areaDetalle = new JTextArea();
    private final JButton btnCrear = new JButton("Crear anuncio");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnRefrescar = new JButton("Refrescar");

    public PanelAnuncios(String usuarioActual, String cursoId) {
        this.usuarioActual = usuarioActual;
        this.cursoId = cursoId;
        this.manager = AnunciosManager.getInstance();
        setLayout(new BorderLayout(16, 16));
        setBackground(new Color(187, 222, 251));
        // Fondo azul para todos los paneles principales
        setOpaque(true);
        initUI();
        cargarAnuncios();
    }

    private void initUI() {
        Color azulFondo = new Color(187, 222, 251);
        // Panel lista anuncios
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBorder(BorderFactory.createTitledBorder("Anuncios destacados"));
        panelLista.setBackground(azulFondo);
        panelLista.setOpaque(true);
        listaAnuncios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaAnuncios.setCellRenderer(new AnuncioRenderer());
        JScrollPane scrollLista = new JScrollPane(listaAnuncios);
        scrollLista.getViewport().setBackground(Color.WHITE);
        panelLista.add(scrollLista, BorderLayout.CENTER);
        add(panelLista, BorderLayout.WEST);

        // Panel detalle
        JPanel panelDetalle = new JPanel(new BorderLayout());
        panelDetalle.setBorder(BorderFactory.createTitledBorder("Detalle del anuncio"));
        panelDetalle.setBackground(azulFondo);
        panelDetalle.setOpaque(true);
        areaDetalle.setEditable(false);
        areaDetalle.setLineWrap(true);
        areaDetalle.setWrapStyleWord(true);
        JScrollPane scrollDetalle = new JScrollPane(areaDetalle);
        scrollDetalle.getViewport().setBackground(Color.WHITE);
        panelDetalle.add(scrollDetalle, BorderLayout.CENTER);
        add(panelDetalle, BorderLayout.CENTER);

        // Panel botones
        // Panel sur con dos zonas: izquierda (volver) y derecha (acciones)
        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.setBackground(azulFondo);
        panelSur.setOpaque(true);

        // Botón Volver (izquierda)
        JPanel panelVolver = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelVolver.setBackground(azulFondo);
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            Window win = SwingUtilities.getWindowAncestor(this);
            if (win != null) win.dispose();
            new moddashboardclass().setVisible(true);
        });
        panelVolver.add(btnVolver);
        panelSur.add(panelVolver, BorderLayout.WEST);

        // Botones de acciones (derecha)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(azulFondo);
        panelBotones.setOpaque(true);
        panelBotones.add(btnRefrescar);
        boolean esEstudiante = false;
        if (LoginApp.usuarioActual != null && LoginApp.usuarioActual.tipo != null) {
            esEstudiante = LoginApp.usuarioActual.tipo.trim().equalsIgnoreCase("Estudiante");
        }
        if (!esEstudiante) {
            panelBotones.add(btnCrear);
        }
        panelBotones.add(btnEliminar);
        panelSur.add(panelBotones, BorderLayout.EAST);

        add(panelSur, BorderLayout.SOUTH);

        // Listeners
        btnRefrescar.addActionListener(e -> cargarAnuncios());
        btnCrear.addActionListener(e -> mostrarDialogoCrear());
        btnEliminar.addActionListener(e -> eliminarAnuncioSeleccionado());
        listaAnuncios.addListSelectionListener(e -> mostrarDetalleSeleccionado());
    }

    private void cargarAnuncios() {
        modeloLista.clear();
        manager.publicarAnunciosProgramados();
        List<AnunciosManager.Anuncio> anuncios = manager.listarAnunciosPorCurso(cursoId);
        for (AnunciosManager.Anuncio a : anuncios) modeloLista.addElement(a);
        areaDetalle.setText("");
    }

    private void mostrarDetalleSeleccionado() {
        AnunciosManager.Anuncio anuncio = listaAnuncios.getSelectedValue();
        if (anuncio == null) {
            areaDetalle.setText("");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Título: ").append(anuncio.getTitulo()).append("\n");
        sb.append("Publicado por: ").append(anuncio.getCreador()).append("\n");
        if (anuncio.getFechaPublicacion() != null)
            sb.append("Publicado: ").append(anuncio.getFechaPublicacion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append("\n");
        if (anuncio.getFechaProgramada() != null)
            sb.append("Programado para: ").append(anuncio.getFechaProgramada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append("\n");
        sb.append("\n").append(anuncio.getMensaje());
        areaDetalle.setText(sb.toString());
    }

    private void mostrarDialogoCrear() {
        // Panel principal para fondo azul
        JPanel panelFondo = new JPanel(new BorderLayout());
        panelFondo.setBackground(new Color(187, 222, 251));

        // Panel de campos con GridBagLayout
        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBackground(new Color(187, 222, 251));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        gbc.gridx = 0; gbc.gridy = 0;
        panelCampos.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        JTextField fieldTitulo = new JTextField(24);
        panelCampos.add(fieldTitulo, gbc);

        // Fecha programada
        gbc.gridx = 0; gbc.gridy = 1;
        panelCampos.add(new JLabel("Programar para (yyyy-MM-dd HH:mm, opcional):"), gbc);
        gbc.gridx = 1;
        JTextField fieldFecha = new JTextField(24);
        panelCampos.add(fieldFecha, gbc);

        // Mensaje
        gbc.gridx = 0; gbc.gridy = 2;
        panelCampos.add(new JLabel("Mensaje:"), gbc);
        gbc.gridx = 1;
        JTextArea areaMensaje = new JTextArea(4, 24);
        areaMensaje.setLineWrap(true);
        areaMensaje.setWrapStyleWord(true);
        JScrollPane scrollMensaje = new JScrollPane(areaMensaje);
        panelCampos.add(scrollMensaje, gbc);

        // Panel de botones también azul
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotones.setBackground(new Color(187, 222, 251));
        JButton okBtn = new JButton("OK");
        JButton cancelBtn = new JButton("Cancel");
        panelBotones.add(okBtn);
        panelBotones.add(cancelBtn);

        // Añadir campos y botones al panel principal
        panelFondo.add(panelCampos, BorderLayout.CENTER);
        panelFondo.add(panelBotones, BorderLayout.SOUTH);

        // Usar Frame como owner para compatibilidad
        Frame owner = JOptionPane.getFrameForComponent(this);
        JDialog dialog = new JDialog(owner, "Crear anuncio", true);
        dialog.setContentPane(panelFondo);
        dialog.pack();
        dialog.setLocationRelativeTo(this);

        // Acción de OK
        okBtn.addActionListener(e -> {
            String titulo = fieldTitulo.getText().trim();
            String mensaje = areaMensaje.getText().trim();
            String fechaStr = fieldFecha.getText().trim();
            LocalDateTime fechaProg = null;
            if (!fechaStr.isEmpty()) {
                try {
                    fechaProg = LocalDateTime.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Formato de fecha inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if (titulo.isEmpty() || mensaje.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Título y mensaje son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            manager.crearAnuncio(titulo, mensaje, usuarioActual, fechaProg, cursoId);
            cargarAnuncios();
            dialog.dispose();
        });
        // Acción de Cancelar
        cancelBtn.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void eliminarAnuncioSeleccionado() {
        AnunciosManager.Anuncio anuncio = listaAnuncios.getSelectedValue();
        if (anuncio == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un anuncio para eliminar.", "Sin selección", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!anuncio.getCreador().equals(usuarioActual) && !AnunciosManager.getInstance().esAdmin(usuarioActual)) {
            JOptionPane.showMessageDialog(this, "Solo el creador o un administrador puede eliminar este anuncio.", "Permiso denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int res = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar el anuncio?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            manager.eliminarAnuncio(anuncio.getId(), usuarioActual);
            cargarAnuncios();
        }
    }

    /**
     * Permite ejecutar el panel de anuncios como aplicación standalone para pruebas.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gestión de Anuncios - Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 500);
            frame.setLocationRelativeTo(null);
            // Usuario y curso de ejemplo
            String usuario = "admin";
            String cursoId = "CURSO-TEST";
            frame.setContentPane(new PanelAnuncios(usuario, cursoId));
            frame.setVisible(true);
        });
    }

    /**
     * Renderizador para mostrar título y fecha en la lista de anuncios.
     */
    public static class AnuncioRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof AnunciosManager.Anuncio) {
                AnunciosManager.Anuncio a = (AnunciosManager.Anuncio) value;
                String fecha = a.getFechaPublicacion() != null ? a.getFechaPublicacion().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "(programado)";
                label.setText(a.getTitulo() + "  [" + fecha + "]");
            }
            return label;
        }
    }
}
