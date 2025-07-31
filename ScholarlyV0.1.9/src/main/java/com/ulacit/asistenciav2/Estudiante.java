package com.ulacit.asistenciav2;
import java.util.HashMap;
import java.util.Map;

public class Estudiante {
    private String nombre;
    private String id;
    private Map<String, String> historialAsistencia = new HashMap<>();

    public Estudiante(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() { return nombre; }
    public String getId() { return id; }

    public Map<String, String> getHistorialAsistencia() {
        return historialAsistencia;
    }

    public void registrarAsistencia(String semana, String estado) {
        historialAsistencia.put(semana, estado);
    }
}
