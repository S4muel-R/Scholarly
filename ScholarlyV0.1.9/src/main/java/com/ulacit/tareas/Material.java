package com.ulacit.tareas;

import java.io.Serializable;
import java.util.Date;

public class Material implements Serializable {
    private String id;
    private String nombre;
    private String tipo; // Ej: "Guía", "Recurso", "Presentación"
    private String archivoRuta;
    private String cursoCodigo;
    private Date fechaPublicacion;

    public Material(String id, String nombre, String tipo, String archivoRuta, String cursoCodigo, Date fechaPublicacion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.archivoRuta = archivoRuta;
        this.cursoCodigo = cursoCodigo;
        this.fechaPublicacion = fechaPublicacion;
    }

    // Getters y setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getArchivoRuta() { return archivoRuta; }
    public String getCursoCodigo() { return cursoCodigo; }
    public Date getFechaPublicacion() { return fechaPublicacion; }
}
