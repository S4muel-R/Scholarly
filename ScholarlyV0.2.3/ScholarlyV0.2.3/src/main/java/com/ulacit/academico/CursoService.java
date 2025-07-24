package com.ulacit.academico;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CursoService {
    private List<Curso> cursos = new ArrayList<>();

    public List<Curso> listarCursosOrdenados() {
        return cursos.stream()
                .sorted((c1, c2) -> c1.getNombre().compareToIgnoreCase(c2.getNombre()))
                .collect(Collectors.toList());
    }

    public String agregarCurso(Curso curso) {
        for (Curso c : cursos) {
            if (c.getCodigo().equalsIgnoreCase(curso.getCodigo())) {
                return "Ya existe un curso con este código.";
            }
            if (c.getAula().equalsIgnoreCase(curso.getAula()) && c.getHorario().equalsIgnoreCase(curso.getHorario())) {
                return "Ya hay un curso en esta aula y horario.";
            }
            if (c.getProfesor().getId().equals(curso.getProfesor().getId()) && c.getHorario().equalsIgnoreCase(curso.getHorario())) {
                return "Este profesor ya está asignado a otro curso en este horario.";
            }
        }

        List<String> ids = new ArrayList<>();
        for (Estudiante e : curso.getEstudiantes()) {
            if (ids.contains(e.getId())) return "El estudiante " + e.getNombre() + " está inscrito más de una vez.";
            ids.add(e.getId());
        }

        cursos.add(curso);
        return "OK";
    }

    public void actualizarCurso(Curso original, Curso modificado) {
        cursos.remove(original);
        cursos.add(modificado);
    }

    public List<Curso> obtenerCursos() {
        return cursos;
    }
}
