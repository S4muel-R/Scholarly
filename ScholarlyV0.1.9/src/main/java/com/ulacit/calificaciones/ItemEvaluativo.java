package com.ulacit.calificaciones;

import java.util.Date;

public class ItemEvaluativo {
    private String nombre;
    private String tipo;
    private Date fecha;
    private double porcentaje;

    public ItemEvaluativo(String nombre, String tipo, Date fecha, double porcentaje) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.porcentaje = porcentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getPorcentaje() {
        return porcentaje;
    }
}
