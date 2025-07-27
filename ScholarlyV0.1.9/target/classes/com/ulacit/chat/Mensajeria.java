/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatgui;

import java.time.LocalDateTime;

/**
 *
 * @author sebas
 */
public class Mensajeria {
    private String idMensaje;       
    private String idRemitente;
    private String nombreRemitente;
    private String idReceptor;
    private String nombreReceptor;
    private String contenido;
    private LocalDateTime fechaHora;
    private boolean leido;

  
    public Mensajeria(
            String idRemitente,
            String nombreRemitente,
            String idReceptor,
            String nombreReceptor,
            String contenido,
            LocalDateTime fechaHora
    ) {
        this.idRemitente     = idRemitente;
        this.nombreRemitente = nombreRemitente;
        this.idReceptor      = idReceptor;
        this.nombreReceptor  = nombreReceptor;
        this.contenido       = contenido;
        this.fechaHora       = fechaHora;
        this.leido           = false;
    }

   
    public String getIdMensaje()        { return idMensaje; }
    public String getIdRemitente()      { return idRemitente; }
    public String getNombreRemitente()  { return nombreRemitente; }
    public String getIdReceptor()       { return idReceptor; }
    public String getNombreReceptor()   { return nombreReceptor; }
    public String getContenido()        { return contenido; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public boolean isLeido()            { return leido; }
    public void setLeido(boolean leido) { this.leido = leido; }
}
    

