package com.ulacit.calificaciones;

import java.util.Date;

public class HistorialCambio {
    private String estudianteId;
    private String itemNombre;
    private double notaAnterior;
    private double notaNueva;
    private Date fechaCambio;

    public HistorialCambio(String estudianteId, String itemNombre, double notaAnterior, double notaNueva) {
        this.estudianteId = estudianteId;
        this.itemNombre = itemNombre;
        this.notaAnterior = notaAnterior;
        this.notaNueva = notaNueva;
        this.fechaCambio = new Date();
    }

    @Override
    public String toString() {
        return "Estudiante " + estudianteId + " - " + itemNombre + ": " + 
               notaAnterior + " → " + notaNueva + " (" + fechaCambio + ")";
    }
}
