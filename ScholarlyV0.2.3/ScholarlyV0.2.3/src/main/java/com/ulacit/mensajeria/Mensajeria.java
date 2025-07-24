package com.ulacit.mensajeria;
import java.time.LocalDateTime;

public class Mensajeria {
    private String idMensaje;     // se usara proximamnete     
    private String idRemitente;         
    private String idReceptor;         
    private String contenido;           
    private LocalDateTime fechaHora;    
    private String nombreRemitente;
    // private String nombreReceptor;
    
   
    public Mensajeria(String idMensaje, String idRemitente, String nombreRemitente, String idReceptor, String nombreReceptor, String contenido, LocalDateTime fechaHora) {
        this.idMensaje = idMensaje;
        this.idRemitente = idRemitente;
        this.idReceptor = idReceptor;
        this.contenido = contenido;
        this.fechaHora = fechaHora;
        this.nombreRemitente = nombreRemitente;
        // this.nombreReceptor = nombreReceptor;
    }

    

    public String getIdMensaje() {
        return idMensaje;
    }

    public String getIdRemitente() {
        return idRemitente;
    }

    public String getIdReceptor() {
        return idReceptor;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    String getNombreRemitente() {
        return nombreRemitente;
    }}
    

