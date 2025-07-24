
package com.ulacit.gestiondegrupos;

import java.util.Scanner;
/**
 *
 * @author penge
 */

//Este es el main, donde esta el menu.
public class ModuloGestiondeGrupos {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("---Gestion de Grupos de Trabajo ---");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Crear grupo");
            System.out.println("3. Eliminar Grupo");
            System.out.println("4. Asignar estudiante a grupo");
            System.out.println("5. Eliminar estudiante del grupo");
            System.out.println("6. Ver grupos");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");
            int opc = scanner.nextInt();
            scanner.nextLine();

            switch (opc) {
                case 1:
                    System.out.print("Nombre del estudiante: ");
                    String nombre = scanner.nextLine();
                    System.out.print("ID del estudiante: ");
                    String id = scanner.nextLine();
                    sistema.registrarEstudiante(new Estudiante(nombre, id));
                    break;

                case 2:
                    System.out.print("Nombre del grupo: ");
                    String nombreGrupo = scanner.nextLine();
                    sistema.crearGrupo(nombreGrupo);
                    break;
                    
                case 3:
                    System.out.print("Nombre del grupo: ");
                    String grupoEliminar = scanner.nextLine();
                    sistema.eliminarGrupo(grupoEliminar);
                    break;
                    

                case 4:
                    System.out.print("ID del estudiante: ");
                    String idEst = scanner.nextLine();
                    System.out.print("Nombre del grupo: ");
                    String grupoNom = scanner.nextLine();
                    sistema.asignarEstudianteAGrupo(idEst, grupoNom);
                    break;
                    
                case 5:
                    System.out.print("ID del estudiante: ");
                    String idEliminar = scanner.nextLine();
                    System.out.print("Nombre del grupo: ");
                    String estudianteEliminarGrupo  = scanner.nextLine();
                    sistema.eliminarEstudianteDeGrupo(idEliminar, estudianteEliminarGrupo);
                    break;
                        

                case 6:
                    sistema.mostrarGrupos();
                    break;

                case 7:
                    System.out.println("Saliendo");
                    return;

                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}
