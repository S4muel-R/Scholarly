package com.ulacit.asistenciav2;
import java.util.Arrays;
import java.util.List;

public class Asistencia_Cursos {
    public static void main(String[] args) {
        // Estudiantes del Curso A
        Estudiante adrian = new Estudiante("Adrian", "001");
        adrian.registrarAsistencia("Semana 1", "Ausente");
        adrian.registrarAsistencia("Semana 2", "Presente");

        Estudiante maria = new Estudiante("Maria", "002");
        maria.registrarAsistencia("Semana 1", "Presente");

        Estudiante jose = new Estudiante("Jose", "003");
        jose.registrarAsistencia("Semana 1", "Justificado");

        // Estudiantes del Curso B
        Estudiante laura = new Estudiante("Laura", "004");
        laura.registrarAsistencia("Semana 1", "Presente");

        Estudiante kevin = new Estudiante("Kevin", "005");
        kevin.registrarAsistencia("Semana 1", "Ausente");

        // Crear cursos
        Curso cursoA = new Curso("Curso A");
        cursoA.agregarEstudiante(adrian);
        cursoA.agregarEstudiante(maria);
        cursoA.agregarEstudiante(jose);

        Curso cursoB = new Curso("Curso B");
        cursoB.agregarEstudiante(laura);
        cursoB.agregarEstudiante(kevin);

        // Profesor
        Profesor prof = new Profesor("Profe Juan");

        // Lista de semanas
        List<String> semanas = List.of("Semana 1", "Semana 2", "Semana 3");

        // Lista de cursos
        List<Curso> cursos = List.of(cursoA, cursoB);

        // Mostrar menú de asistencias
        AsistenciaManager.mostrarAsistenciaProfesor(prof, cursos, semanas);
    }
}