package com.ulacit.tareas_pendientes;

import java.time.LocalDate;
import javax.swing.SwingUtilities;
public class MooduloTareas_Pendientes{
    public static void main(String[] args) {
        // Crear cursos
        Curso cursoA = new Curso("Matematica");
        Curso cursoB = new Curso("Historia");

        // Crear tareas para Curso A
        Tarea tareaActiva = new Tarea("Ensayo de Calculo", LocalDate.of(2025, 8, 15)); // activa
        cursoA.agregarTarea(tareaActiva);

        Tarea tareaVencida = new Tarea("Tarea pasada", LocalDate.of(2025, 8, 5)); // vencida
        cursoA.agregarTarea(tareaVencida);
        tareaVencida.marcarComoEntregada(); // marcar como entregada

        // Crear tareas para Curso B (sin pendientes activas)
        Tarea tareaCursoB = new Tarea("Mapa mental", LocalDate.of(2025, 8, 4)); // vencida
        cursoB.agregarTarea(tareaCursoB);
        tareaCursoB.marcarComoEntregada(); // ya entregada

        // Crear estudiante inscrito en ambos cursos
        Estudiante estudiante = new Estudiante("María Rodríguez");
        estudiante.inscribirCurso(cursoA);
        estudiante.inscribirCurso(cursoB);

        // Mostrar interfaz
        SwingUtilities.invokeLater(() -> {
            PanelPrincipal ventana = new PanelPrincipal(estudiante, cursoA, cursoB);
            ventana.setVisible(true);
        });
    }
}