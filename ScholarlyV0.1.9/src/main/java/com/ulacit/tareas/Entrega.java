package com.ulacit.tareas;

import java.io.Serializable;
import java.util.Date;

public class Entrega implements Serializable {
    private String idActividad;
    private String idEstudiante;
    private Date fechaEntrega;
    private String archivoRuta;
    private String estado; // "A tiempo", "Tardía", "Cerrada"

    public Entrega(String idActividad, String idEstudiante, Date fechaEntrega, String archivoRuta, String estado) {
        this.idActividad = idActividad;
        this.idEstudiante = idEstudiante;
        this.fechaEntrega = fechaEntrega;
        this.archivoRuta = archivoRuta;
        this.estado = estado;
    }

    // Getters y setters
    public String getIdActividad() { return idActividad; }
    public String getIdEstudiante() { return idEstudiante; }
    public Date getFechaEntrega() { return fechaEntrega; }
    public String getArchivoRuta() { return archivoRuta; }
    public String getEstado() { return estado; }
}
