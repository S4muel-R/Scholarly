
package com.ulacit.gestiondegrupos;

/**
 *
 * @author penge
 */
public class Estudiante extends Usuario{
    private Grupos grupo;
    
    public Estudiante(String nombre, String id) {
        super(nombre, id);
    }

    public void setGrupo(Grupos grupo) {
        this.grupo = grupo;
    }

    public Grupos getGrupo() {
        return grupo;
    }
    
}
