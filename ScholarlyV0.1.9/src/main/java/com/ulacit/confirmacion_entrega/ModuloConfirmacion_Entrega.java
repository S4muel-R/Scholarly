package com.ulacit.confirmacion_entrega;

import javax.swing.*;
import java.time.LocalDateTime;
public class ModuloConfirmacion_Entrega {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new VentanaEntrega().setVisible(true);
        });
    }
}