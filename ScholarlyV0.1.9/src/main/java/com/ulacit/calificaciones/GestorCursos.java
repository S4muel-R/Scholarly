package com.ulacit.calificaciones;

import java.util.*;

public class GestorCursos {
    public Map<String, List<Estudiante>> cursos;
    private Map<String, List<ItemEvaluativo>> itemsPorCurso;
    private Map<String, List<Calificacion>> calificaciones;
    private List<HistorialCambio> historial;

    public GestorCursos() {
        cursos = new HashMap<>();
        itemsPorCurso = new HashMap<>();
        calificaciones = new HashMap<>();
        historial = new ArrayList<>();

        inicializarDatos();
    }

    private void inicializarDatos() {
        Calendar cal = Calendar.getInstance();

        // Curso A
        List<Estudiante> grupoA = Arrays.asList(
            new Estudiante("Ana", "A1"),
            new Estudiante("Luis", "A2"),
            new Estudiante("María", "A3")
        );
        cursos.put("Curso A", grupoA);

        // Curso B
        List<Estudiante> grupoB = Arrays.asList(
            new Estudiante("Pedro", "B1"),
            new Estudiante("Carla", "B2"),
            new Estudiante("Sofía", "B3")
        );
        cursos.put("Curso B", grupoB);

        // Crear fechas distintas:
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -10); // hace 10 días
        Date fechaTarea1 = cal.getTime();

        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -5);  // hace 5 días
        Date fechaExamen1 = cal.getTime();

        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -2);  // hace 2 días
        Date fechaProyecto = cal.getTime();

        // Items con fechas variadas
        agregarItem("Curso A", new ItemEvaluativo("Tarea 1", "Tarea", fechaTarea1, 25));
        agregarItem("Curso A", new ItemEvaluativo("Examen 1", "Examen", fechaExamen1, 35));
        agregarItem("Curso B", new ItemEvaluativo("Proyecto", "Proyecto", fechaProyecto, 40));

        // Notas iniciales (igual que antes)
        for (Estudiante e : grupoA) {
            agregarNota("Curso A", new Calificacion(e, itemsPorCurso.get("Curso A").get(0), 80));
            agregarNota("Curso A", new Calificacion(e, itemsPorCurso.get("Curso A").get(1), 70));
        }

        for (Estudiante e : grupoB) {
            agregarNota("Curso B", new Calificacion(e, itemsPorCurso.get("Curso B").get(0), 85));
        }
    }

    public List<Estudiante> getEstudiantes(String curso) {
        return cursos.getOrDefault(curso, new ArrayList<>());
    }

    public List<ItemEvaluativo> getItems(String curso) {
        return itemsPorCurso.getOrDefault(curso, new ArrayList<>());
    }

    public List<Calificacion> getCalificaciones(String curso) {
        return calificaciones.getOrDefault(curso, new ArrayList<>());
    }

    public List<HistorialCambio> getHistorial() {
        return historial;
    }

    public void agregarItem(String curso, ItemEvaluativo item) {
        itemsPorCurso.putIfAbsent(curso, new ArrayList<>());
        itemsPorCurso.get(curso).add(item);
    }

    public void agregarNota(String curso, Calificacion calificacion) {
        calificaciones.putIfAbsent(curso, new ArrayList<>());
        calificaciones.get(curso).add(calificacion);
    }

    public void modificarNota(String curso, Estudiante estudiante, ItemEvaluativo item, double nuevaNota) {
        List<Calificacion> lista = calificaciones.get(curso);
        for (Calificacion c : lista) {
            if (c.getEstudiante().getId().equals(estudiante.getId()) &&
                c.getItem().getNombre().equals(item.getNombre())) {
                historial.add(new HistorialCambio(estudiante.getId(), item.getNombre(), c.getNota(), nuevaNota));
                c.setNota(nuevaNota);
            }
        }
    }
}