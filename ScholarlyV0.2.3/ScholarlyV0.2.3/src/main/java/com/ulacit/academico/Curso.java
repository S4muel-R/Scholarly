package com.ulacit.academico;

import java.util.List;

public class Curso {
    private String nombre;
    private String codigo;
    private String periodo;
    private Profesor profesor;
    private List<Estudiante> estudiantes;
    private String horario;
    private String aula;

    public Curso(String nombre, String codigo, String periodo, Profesor profesor,
                 List<Estudiante> estudiantes, String horario, String aula) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.periodo = periodo;
        this.profesor = profesor;
        this.estudiantes = estudiantes;
        this.horario = horario;
        this.aula = aula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public String getHorario() {
        return horario;
    }

    public String getAula() {
        return aula;
    }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }
}
