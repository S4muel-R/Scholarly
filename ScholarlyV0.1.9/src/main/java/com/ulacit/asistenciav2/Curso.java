package com.ulacit.asistenciav2;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nombre;
    private List<Estudiante> estudiantes = new ArrayList<>();

    public Curso(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void agregarEstudiante(Estudiante e) {
        estudiantes.add(e);
    }
}
