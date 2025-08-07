package moodulo.tareas_pendientes;
import java.util.ArrayList;
import java.util.List;

public class GestorTareas {

    public static List<Tarea> obtenerTareasPendientes(Curso curso, Estudiante estudiante) {
        List<Tarea> pendientes = new ArrayList<>();

        if (!estudiante.estaInscritoEn(curso)) {
            return pendientes;
        }

        for (Tarea tarea : curso.getTareas()) {
            if (tarea.estaActiva()) {
                pendientes.add(tarea);
            }
        }
        return pendientes;
    }
}
