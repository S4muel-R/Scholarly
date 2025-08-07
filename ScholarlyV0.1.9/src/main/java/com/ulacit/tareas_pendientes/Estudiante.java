package moodulo.tareas_pendientes;
import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String nombre;
    private List<Curso> cursos;

    public Estudiante(String nombre) {
        this.nombre = nombre;
        this.cursos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void inscribirCurso(Curso curso) {
        cursos.add(curso);
    }

    public boolean estaInscritoEn(Curso curso) {
        return cursos.contains(curso);
    }
}
