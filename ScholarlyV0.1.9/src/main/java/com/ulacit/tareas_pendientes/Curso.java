package com.ulacit.tareas_pendientes;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nombre;
    private List<Tarea> tareas;

    public Curso(String nombre) {
        this.nombre = nombre;
        this.tareas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }
}
