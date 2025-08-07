package com.ulacit.tema;

import java.awt.Color;

public class Tema {
    // Colores de los dos temas
    private static final Color COLOR_CLARO = new Color(187, 222, 251);
    private static final Color COLOR_OSCURO = new Color(0, 38, 77);

    // Variable para saber si está en modo oscuro
    private static boolean modoOscuro = false;

    // Método para cambiar de tema (lo usarás SOLO desde el botón en Dashboard)
    public static void cambiarTema() {
        modoOscuro = !modoOscuro;
    }

    public static Color getColorFondo() {
        return modoOscuro ? COLOR_OSCURO : COLOR_CLARO;
    }

    public static Color getColorTexto() {
        return modoOscuro ? Color.WHITE : Color.BLACK;
    }

    public static boolean esModoOscuro() {
        return modoOscuro;
    }
}