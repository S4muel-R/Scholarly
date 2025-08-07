package modulo.confirmacion_entrega;
import java.time.LocalDateTime;

public class Actividad {
    private String nombre;
    private LocalDateTime fechaLimite;
    private boolean entregada;
    private LocalDateTime fechaEntrega;
    private String respuesta;

    public Actividad(String nombre, LocalDateTime fechaLimite) {
        this.nombre = nombre;
        this.fechaLimite = fechaLimite;
        this.entregada = false;
    }

    public boolean entregar(String respuesta) {
        if (respuesta == null || respuesta.trim().isEmpty()) return false;
        if (LocalDateTime.now().isAfter(fechaLimite)) return false;

        this.respuesta = respuesta;
        this.fechaEntrega = LocalDateTime.now();
        this.entregada = true;
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isEntregada() {
        return entregada;
    }

    public LocalDateTime getFechaEntrega() {
        return fechaEntrega;
    }

    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }

    public String getRespuesta() {
        return respuesta;
    }
}