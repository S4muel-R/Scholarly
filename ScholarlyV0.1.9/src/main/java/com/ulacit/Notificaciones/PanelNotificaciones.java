package com.ulacit.Notificaciones;

import javax.swing.*;
import java.awt.*;

/**
 * Panel simple para mostrar notificaciones del usuario actual.
 */
public class PanelNotificaciones {
    /**
     * Muestra un diálogo modal con las notificaciones del usuario.
     * @param parent Componente padre para el diálogo (puede ser JFrame o JDialog)
     */
    public static void mostrarDialogo(Component parent) {
        // Usuario actual (en un sistema real, obtén el usuario logueado)
        String usuarioActual = "admin"; // TODO: reemplazar por usuario real
        java.util.List<AnunciosManager.Notificacion> notificaciones = AnunciosManager.getInstance().obtenerNotificacionesUsuario(usuarioActual);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(new Color(187, 222, 251));
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        DefaultListModel<String> modelo = new DefaultListModel<>();
        if (notificaciones.isEmpty()) {
            modelo.addElement("No hay notificaciones nuevas.");
        } else {
            for (AnunciosManager.Notificacion n : notificaciones) {
                AnunciosManager.Anuncio a = AnunciosManager.getInstance().buscarAnuncioPorId(n.getAnuncioId());
                if (a != null) {
                    modelo.addElement("[" + a.getTitulo() + "] " + a.getMensaje());
                }
            }
        }
        JList<String> lista = new JList<>(modelo);
        lista.setBackground(Color.WHITE);
        panel.add(new JScrollPane(lista), BorderLayout.CENTER);

        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parent), "Notificaciones", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setContentPane(panel);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}
