package com.ulacit.tareas;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Actividad implements Serializable {
    private String id;
    private String nombre;
    private String descripcion;
    private Date fechaPublicacion;
    private Date fechaLimite;
    private Date fechaCierre;
    private List<String> archivosAdjuntos; // rutas o nombres de archivos
    private String tipo; // Ej: "Tarea", "Examen"
    private String cursoCodigo;

    public Actividad(String id, String nombre, String descripcion, Date fechaPublicacion, Date fechaLimite, Date fechaCierre, List<String> archivosAdjuntos, String tipo, String cursoCodigo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaLimite = fechaLimite;
        this.fechaCierre = fechaCierre;
        this.archivosAdjuntos = archivosAdjuntos;
        this.tipo = tipo;
        this.cursoCodigo = cursoCodigo;
    }

    // Getters y setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public Date getFechaPublicacion() { return fechaPublicacion; }
    public Date getFechaLimite() { return fechaLimite; }
    public Date getFechaCierre() { return fechaCierre; }
    public List<String> getArchivosAdjuntos() { return archivosAdjuntos; }
    public String getTipo() { return tipo; }
    public String getCursoCodigo() { return cursoCodigo; }
}
