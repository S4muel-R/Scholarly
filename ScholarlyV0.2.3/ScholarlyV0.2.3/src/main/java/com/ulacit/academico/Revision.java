package com.ulacit.academico;

public class Revision {
    public String motivo, estado = "Pendiente", respuesta = "";

    public Revision(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Motivo: " + motivo + " | Estado: " + estado + " | Respuesta: " + respuesta;
    }
}
