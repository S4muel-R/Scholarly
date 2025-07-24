package com.ulacit.academico;
public class ItemEvaluacion {
    public String nombre, descripcion, retroalimentacion;
    public double calificacion;

    public ItemEvaluacion(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.calificacion = -1;
        this.retroalimentacion = "";
    }

    @Override
    public String toString() {
        return nombre + " - Nota: " + (calificacion >= 0 ? calificacion : "Sin calificar") +
               " | Retro: " + retroalimentacion;
    }
}
