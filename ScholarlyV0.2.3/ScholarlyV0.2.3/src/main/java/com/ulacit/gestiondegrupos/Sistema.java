
package com.ulacit.gestiondegrupos;

import java.util.ArrayList;
/**
 *
 * @author penge
 */

//Clase que se encarga de realizar todas las operaciones de registrar estudiantes, crear grupos y asignar yeliminar estudiantes de grupo.
public class Sistema {
    
 private ArrayList<Estudiante> estudiantes;
    private ArrayList<Grupos> grupos;

    public Sistema() {
        estudiantes = new ArrayList<>();
        grupos = new ArrayList<>();
    }

    public void registrarEstudiante(Estudiante e) {
        estudiantes.add(e);
    }

    public void crearGrupo(String nombreGrupo) {
        grupos.add(new Grupos(nombreGrupo));
    }
    
    public void eliminarGrupo(String nombreGrupo) {
        Grupos grupoEliminar = buscarGrupo(nombreGrupo);

        if (grupoEliminar != null) {
            // codigo para eliminar los estudiantes del grupo
            for (Estudiante e : grupoEliminar.getIntegrantes()) {
            e.setGrupo(null);
            }
            // codigo para eliminar el grupo
            grupos.remove(grupoEliminar);
            System.out.println("Grupo eliminado con éxito.");
        } else {
            System.out.println("Grupo no encontrado.");
    }
}

    public Grupos buscarGrupo(String nombre) {
        for (Grupos g : grupos) {
            if (g.getNombreGrupo().equalsIgnoreCase(nombre)) return g;
        }
        return null;
    }

    public Estudiante buscarEstudiante(String id) {
        for (Estudiante e : estudiantes) {
            if (e.getId().equals(id)) return e;
        }
        return null;
    }

    public void asignarEstudianteAGrupo(String idEstudiante, String nombreGrupo){
        Estudiante e = buscarEstudiante(idEstudiante);
        Grupos g = buscarGrupo(nombreGrupo);
        if (e != null && g != null) {   //
            g.agregarEstudiante(e);
            System.out.println("Estudiante asignado.");
        } else {
            System.out.println("Error: estudiante o grupo no encontrado.");
        }
    }
    
    public void eliminarEstudianteDeGrupo (String idEstudiante, String nombreGrupo){
        Estudiante e = buscarEstudiante(idEstudiante);
        Grupos g = buscarGrupo(nombreGrupo);
        if (e != null && g != null) {   //
            g.eliminarEstudiante(e);
            System.out.println("Estudiante eliminado del grupo.");
        } else {
            System.out.println("Error: estudiante o grupo no encontrado.");
        }
        
    }

    public void mostrarGrupos() {
        for (Grupos g : grupos) {
            System.out.println("Grupo: " + g.getNombreGrupo());
            for (Estudiante e : g.getIntegrantes()) {
                System.out.println(" --- " + e.getNombre());
            }
        }
    }
}
