
package com.ulacit.gestiondegrupos;

/**
 *
 * @author penge
 */
public class Estudiante {
    private String nombre;
    private String id;
    private Grupos grupo;

    public Estudiante(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.grupo = null;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public Grupos getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupos grupo) {
        this.grupo = grupo;
    }
    
        @Override
    public String toString() {
        return nombre + " (" + id + ")";
    }

}
