package com.ulacit.academico;
import java.util.*;

public class ModuloEvaluaciones {
    private final Map<Integer, Map<Integer, List<ItemEvaluacion>>> calificaciones = new HashMap<>();

    public void registrarNota(int idEst, int idCurso, ItemEvaluacion item) {
        calificaciones
            .computeIfAbsent(idEst, k -> new HashMap<>())
            .computeIfAbsent(idCurso, k -> new ArrayList<>())
            .add(item);
    }

    public List<ItemEvaluacion> obtenerItems(int idEst, int idCurso) {
        return calificaciones
            .getOrDefault(idEst, new HashMap<>())
            .getOrDefault(idCurso, new ArrayList<>());
    }

    public boolean editarItem(int idEst, int idCurso, int index, double nuevaNota, String retro) {
        List<ItemEvaluacion> items = obtenerItems(idEst, idCurso);
        if (index >= 0 && index < items.size()) {
            items.get(index).calificacion = nuevaNota;
            items.get(index).retroalimentacion = retro;
            return true;
        }
        return false;
    }
}
