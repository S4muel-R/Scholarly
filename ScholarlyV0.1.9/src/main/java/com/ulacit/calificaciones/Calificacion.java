package com.ulacit.calificaciones;

public class Calificacion {
    private Estudiante estudiante;
    private ItemEvaluativo item;
    private double nota;

    public Calificacion(Estudiante estudiante, ItemEvaluativo item, double nota) {
        this.estudiante = estudiante;
        this.item = item;
        this.nota = nota;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public ItemEvaluativo getItem() {
        return item;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
