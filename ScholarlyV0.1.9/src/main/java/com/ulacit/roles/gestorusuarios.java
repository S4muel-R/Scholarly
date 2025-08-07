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
    private List<usuario> usuarios;

    public gestorusuarios() {
        usuarios = new ArrayList<>();
    }

    public void agregarUsuario(usuario usuario) {
        usuarios.add(usuario);
    }

    public List<usuario> getUsuarios() {
        return usuarios;
    }

    public List<usuario> getUsuariosConRol() {
        List<usuario> activos = new ArrayList<>();
        for (usuario u : usuarios) {
            if (u.getRol() != null) {
                activos.add(u);
            }
        }
        return activos;
    }

    public boolean cambiarRolUsuario(usuario usuario, rol nuevoRol) {
        usuario.asignarRol(nuevoRol); // Solo se permite un rol
        return true;
    }
}
