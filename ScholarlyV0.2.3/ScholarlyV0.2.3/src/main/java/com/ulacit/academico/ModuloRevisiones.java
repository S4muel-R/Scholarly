package com.ulacit.academico;
import java.util.*;

public class ModuloRevisiones {
    private final Map<Integer, Map<String, Revision>> revisiones = new HashMap<>();

    public void solicitarRevision(int idEst, String tarea, String motivo) {
        revisiones
            .computeIfAbsent(idEst, k -> new HashMap<>())
            .put(tarea, new Revision(motivo));
    }

    public Revision consultarRevision(int idEst, String tarea) {
        return revisiones
            .getOrDefault(idEst, new HashMap<>())
            .get(tarea);
    }
}
