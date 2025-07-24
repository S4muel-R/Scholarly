package com.ulacit.academico;
import java.util.Scanner;
import java.util.*;

public class Plataforma {
static Scanner sc = new Scanner(System.in);

    static Map<Integer, String> estudiantes = Map.of(
        8, "Ana",
        20, "Luis"
    );

    static Map<Integer, String> cursos = Map.of(
        101, "Curso A",
        102, "Curso B"
    );

    static ModuloEvaluaciones moduloEval = new ModuloEvaluaciones();
    static ModuloConsulta moduloConsulta = new ModuloConsulta(moduloEval);
    static ModuloRevisiones moduloRev = new ModuloRevisiones();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Ingresar nota");
            System.out.println("2. Editar nota");
            System.out.println("3. Ver notas");
            System.out.println("4. Solicitar revisión");
            System.out.println("5. Ver estado de revisión");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int op = leerInt("");

            switch (op) {
                case 1 -> ingresarNota();
                case 2 -> editarNota();
                case 3 -> verNotas();
                case 4 -> solicitarRevision();
                case 5 -> verRevision();
                case 6 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    static int leerIDEstudiante() {
        int id;
        while (true) {
            id = leerInt("ID Estudiante disponible (8=Ana, 20=Luis)");
            if (estudiantes.containsKey(id)) break;
            System.out.println("ID de estudiante no existente. Intente nuevamente.");
        }
        return id;
    }

    static int leerIDCurso() {
        int id;
        while (true) {
            id = leerInt("ID Curso disponible (101=A, 102=B)");
            if (cursos.containsKey(id)) break;
            System.out.println("ID de curso no existente. Intente nuevamente.");
        }
        return id;
    }

    static void ingresarNota() {
        int idEst = leerIDEstudiante();
        int idCurso = leerIDCurso();
        sc.nextLine(); // limpiar
        System.out.print("Nombre de tarea: ");
        String nombre = sc.nextLine();
        System.out.print("Descripción: ");
        String desc = sc.nextLine();
        double nota = leerDouble("Nota");
        sc.nextLine(); // limpiar
        System.out.print("Retroalimentación: ");
        String retro = sc.nextLine();

        ItemEvaluacion item = new ItemEvaluacion(nombre, desc);
        item.calificacion = nota;
        item.retroalimentacion = retro;

        moduloEval.registrarNota(idEst, idCurso, item);
        System.out.println("Nota registrada correctamente.");
    }

    static void editarNota() {
        int idEst = leerIDEstudiante();
        int idCurso = leerIDCurso();

        var items = moduloEval.obtenerItems(idEst, idCurso);
        if (items.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }

        System.out.println("Tareas:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }

        int idx = leerInt("Número de la tarea a editar") - 1;
        if (idx < 0 || idx >= items.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        double nota = leerDouble("Nueva nota");
        sc.nextLine();
        System.out.print("Nueva retroalimentación: ");
        String retro = sc.nextLine();

        if (moduloEval.editarItem(idEst, idCurso, idx, nota, retro)) {
            System.out.println("Nota actualizada.");
        } else {
            System.out.println("Error al actualizar nota.");
        }
    }

    static void verNotas() {
        int idEst = leerIDEstudiante();
        int idCurso = leerIDCurso();
        moduloConsulta.mostrarNotas(idEst, idCurso);
    }

    static void solicitarRevision() {
        int idEst = leerIDEstudiante();
        sc.nextLine();
        System.out.print("Nombre de la tarea: ");
        String tarea = sc.nextLine();
        System.out.print("Motivo de la revisión: ");
        String motivo = sc.nextLine();

        moduloRev.solicitarRevision(idEst, tarea, motivo);
        System.out.println("Revisión solicitada.");
    }

    static void verRevision() {
        int idEst = leerIDEstudiante();
        sc.nextLine();
        System.out.print("Nombre de la tarea: ");
        String tarea = sc.nextLine();

        Revision r = moduloRev.consultarRevision(idEst, tarea);
        if (r != null) {
            System.out.println("--- Estado de la Revisión ---");
            System.out.println(r);
        } else {
            System.out.println("No hay revisión registrada.");
        }
    }

    static int leerInt(String msg) {
        if (!msg.isEmpty()) System.out.print(msg + ": ");
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    static double leerDouble(String msg) {
        System.out.print(msg + ": ");
        while (!sc.hasNextDouble()) {
            System.out.print("Ingrese un número válido: ");
            sc.next();
        }
        return sc.nextDouble();
    }
}