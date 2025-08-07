/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulacit.roles;

/**
 *
 * @author penge
 */
public class usuario {
    private String nombre;
    private String correo;
    private rol rol;

    public usuario(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        this.rol = null; // Sin rol inicialmente
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public rol getRol() {
        return rol;
    }

    public void asignarRol(rol nuevoRol) {
        this.rol = nuevoRol;
    }

    public String getResumen() {
        return nombre + " - " + correo + " - Rol: " + (rol != null ? rol.name() : "Sin rol");
    }
}
