
package com.ulacit.gestiondegrupos;
import java.util.ArrayList;
/**
 *
 * @author penge
 */
public class Grupos {
    private String nombreGrupo;
    private ArrayList<Estudiante> miembros;

    public Grupos(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
        this.miembros = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiante est) {
        miembros.add(est);
        est.setGrupo(this);
    }

    public void eliminarEstudiante(Estudiante est) {
        miembros.remove(est);
        est.setGrupo(null);
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public ArrayList<Estudiante> getIntegrantes() {
        return miembros;
    }

    @Override
    public String toString() {
        return nombreGrupo;
    }

}

