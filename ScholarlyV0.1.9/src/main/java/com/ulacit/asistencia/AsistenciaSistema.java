
package com.ulacit.asistencia;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author penge
 */
class AsistenciaSistema {
    private ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private HashMap<String, String> registroAsistencia = new HashMap<>(); // id -> estado

    public void registrarEstudiante(Estudiante e) {
        if (buscarEstudiante(e.getId()) == null) estudiantes.add(e);
    }

    public Estudiante buscarEstudiante(String id) {
        for (Estudiante e : estudiantes) if (e.getId().equalsIgnoreCase(id)) return e;
        return null;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void marcarAsistencia(String id, String estado) {
        registroAsistencia.put(id, estado);
    }

    public String getAsistencia(String id) {
        return registroAsistencia.getOrDefault(id, "Sin registro");
    }
}