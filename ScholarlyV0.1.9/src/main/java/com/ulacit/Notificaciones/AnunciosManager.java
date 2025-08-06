package com.ulacit.Notificaciones;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Gestor de anuncios y notificaciones para cursos.
 * Permite crear, editar, eliminar, programar y listar anuncios y notificaciones.
 */
public class AnunciosManager {
    // Lista de anuncios (simula almacenamiento en memoria)
    private final List<Anuncio> anuncios = new ArrayList<>();
    // Lista de notificaciones (simula almacenamiento en memoria)
    private final List<Notificacion> notificaciones = new ArrayList<>();

    // Singleton opcional (si se requiere acceso global)
    private static AnunciosManager instance;
    public static AnunciosManager getInstance() {
        if (instance == null) instance = new AnunciosManager();
        return instance;
    }

    // --- Métodos de gestión de anuncios ---
    public Anuncio crearAnuncio(String titulo, String mensaje, String creador, LocalDateTime fechaProgramada, String cursoId) {
        Anuncio anuncio = new Anuncio(titulo, mensaje, creador, fechaProgramada, cursoId);
        anuncios.add(anuncio);
        return anuncio;
    }

    public boolean editarAnuncio(String anuncioId, String nuevoTitulo, String nuevoMensaje, String usuario) {
        Anuncio anuncio = buscarAnuncioPorId(anuncioId);
        if (anuncio != null && (anuncio.getCreador().equals(usuario) || esAdmin(usuario))) {
            anuncio.setTitulo(nuevoTitulo);
            anuncio.setMensaje(nuevoMensaje);
            return true;
        }
        return false;
    }

    public boolean eliminarAnuncio(String anuncioId, String usuario) {
        Anuncio anuncio = buscarAnuncioPorId(anuncioId);
        if (anuncio != null && (anuncio.getCreador().equals(usuario) || esAdmin(usuario))) {
            anuncios.remove(anuncio);
            // Eliminar notificaciones asociadas
            notificaciones.removeIf(n -> n.getAnuncioId().equals(anuncioId));
            return true;
        }
        return false;
    }

    public List<Anuncio> listarAnunciosPorCurso(String cursoId) {
        List<Anuncio> result = new ArrayList<>();
        for (Anuncio a : anuncios) {
            if (a.getCursoId().equals(cursoId) && a.estaPublicado()) {
                result.add(a);
            }
        }
        // Ordenar por fecha de publicación descendente
        result.sort(Comparator.comparing(Anuncio::getFechaPublicacion, Comparator.nullsLast(Comparator.reverseOrder())));
        return result;
    }

    public Anuncio buscarAnuncioPorId(String id) {
        for (Anuncio a : anuncios) if (a.getId().equals(id)) return a;
        return null;
    }

    // --- Métodos de gestión de notificaciones ---
    public void notificarUsuarios(List<String> usuarios, String anuncioId) {
        for (String usuario : usuarios) {
            notificaciones.add(new Notificacion(anuncioId, usuario));
        }
    }

    public List<Notificacion> obtenerNotificacionesUsuario(String usuarioId) {
        List<Notificacion> result = new ArrayList<>();
        for (Notificacion n : notificaciones) {
            if (n.getUsuarioId().equals(usuarioId)) result.add(n);
        }
        return result;
    }

    // --- Métodos de publicación/programación ---
    public void publicarAnunciosProgramados() {
        LocalDateTime ahora = LocalDateTime.now();
        for (Anuncio a : anuncios) {
            if (!a.estaPublicado() && a.getFechaProgramada() != null && !a.getFechaProgramada().isAfter(ahora)) {
                a.setFechaPublicacion(ahora);
            }
        }
    }

    // --- Utilidades y permisos ---
    public boolean esAdmin(String usuario) {
        // TODO: Implementar lógica real de admin
        return "admin".equalsIgnoreCase(usuario);
    }

    // --- Clases internas ---
    public static class Anuncio {
        private final String id;
        private String titulo;
        private String mensaje;
        private String creador;
        private LocalDateTime fechaPublicacion;
        private LocalDateTime fechaProgramada;
        private String cursoId;

        public Anuncio(String titulo, String mensaje, String creador, LocalDateTime fechaProgramada, String cursoId) {
            this.id = UUID.randomUUID().toString();
            this.titulo = titulo;
            this.mensaje = mensaje;
            this.creador = creador;
            this.fechaPublicacion = (fechaProgramada == null) ? LocalDateTime.now() : null;
            this.fechaProgramada = fechaProgramada;
            this.cursoId = cursoId;
        }

        public String getId() { return id; }
        public String getTitulo() { return titulo; }
        public void setTitulo(String titulo) { this.titulo = titulo; }
        public String getMensaje() { return mensaje; }
        public void setMensaje(String mensaje) { this.mensaje = mensaje; }
        public String getCreador() { return creador; }
        public LocalDateTime getFechaPublicacion() { return fechaPublicacion; }
        public void setFechaPublicacion(LocalDateTime fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }
        public LocalDateTime getFechaProgramada() { return fechaProgramada; }
        public void setFechaProgramada(LocalDateTime fechaProgramada) { this.fechaProgramada = fechaProgramada; }
        public String getCursoId() { return cursoId; }
        public void setCursoId(String cursoId) { this.cursoId = cursoId; }
        public boolean estaPublicado() {
            return fechaPublicacion != null && (fechaProgramada == null || !fechaPublicacion.isBefore(fechaProgramada));
        }
    }

    public static class Notificacion {
        private final String id;
        private final String anuncioId;
        private final String usuarioId;
        private boolean leida;

        public Notificacion(String anuncioId, String usuarioId) {
            this.id = UUID.randomUUID().toString();
            this.anuncioId = anuncioId;
            this.usuarioId = usuarioId;
            this.leida = false;
        }

        public String getId() { return id; }
        public String getAnuncioId() { return anuncioId; }
        public String getUsuarioId() { return usuarioId; }
        public boolean isLeida() { return leida; }
        public void marcarLeida() { this.leida = true; }
    }
}
