package com.ulacit.asistenciav2;
    public abstract class Usuario {
    protected String nombre;
    protected String rol;

    public Usuario(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getNombre() { return nombre; }
    public String getRol() { return rol; }
}
