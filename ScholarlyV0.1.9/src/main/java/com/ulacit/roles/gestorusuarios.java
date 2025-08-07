/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulacit.roles;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author penge
 */


public class gestorusuarios {
    private List<usuario> listaUsuarios;

    public gestorusuarios() {
        listaUsuarios = new ArrayList<>();
    }

    public void agregarUsuario(usuario u) {
        listaUsuarios.add(u);
    }
    
    public void eliminarUsuarioPorCorreo(String correo) {
    listaUsuarios.removeIf(u -> u.getCorreo().equals(correo));
}


    public boolean existeCorreo(String correo) {
        for (usuario u : listaUsuarios) {
            if (u.getCorreo().equalsIgnoreCase(correo)) {
                return true;
            }
        }
        return false;
    }

    public List<usuario> getUsuarios() {
        return listaUsuarios;
    }

    public List<usuario> getUsuariosConRol() {
        List<usuario> conRol = new ArrayList<>();
        for (usuario u : listaUsuarios) {
            if (u.getRol() != null) {
                conRol.add(u);
            }
        }
        return conRol;
    }

    public void cambiarRolUsuario(usuario u, rol r) {
        u.setRol(r);
    }
}


