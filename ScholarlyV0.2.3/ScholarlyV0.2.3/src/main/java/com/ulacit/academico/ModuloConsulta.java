package com.ulacit.academico;
import java.util.List;
// import com.ulacit.academico.ItemEvaluacion;

public class ModuloConsulta {
    private final ModuloEvaluaciones moduloEvaluaciones;

    public ModuloConsulta(ModuloEvaluaciones moduloEvaluaciones) {
        this.moduloEvaluaciones = moduloEvaluaciones;
    }

    public void mostrarNotas(int idEst, int idCurso) {
        List<ItemEvaluacion> items = moduloEvaluaciones.obtenerItems(idEst, idCurso);

        if (items.isEmpty()) {
            System.out.println("No hay calificaciones.");
            return;
        }

        double suma = 0;
        int count = 0;

        System.out.println("--- Calificaciones ---");
        for (ItemEvaluacion i : items) {
            System.out.println(i);
            if (i.calificacion >= 0) {
                suma += i.calificacion;
                count++;
            }
        }

        if (count > 0) {
            System.out.println("Promedio: " + (suma / count));
        }
    }
}
