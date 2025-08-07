package com.ulacit.tareas_pendientes;
import java.time.LocalDate;

public class Tarea {
    private String nombre;
    private LocalDate fechaLimite;
    private boolean entregada;

    public Tarea(String nombre, LocalDate fechaLimite) {
        this.nombre = nombre;
        this.fechaLimite = fechaLimite;
        this.entregada = false;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public boolean isEntregada() {
        return entregada;
    }

    public void marcarComoEntregada() {
        this.entregada = true;
    }

    public boolean estaActiva() {
        return !entregada && (fechaLimite.isAfter(LocalDate.now()) || fechaLimite.isEqual(LocalDate.now()));
    }
}
