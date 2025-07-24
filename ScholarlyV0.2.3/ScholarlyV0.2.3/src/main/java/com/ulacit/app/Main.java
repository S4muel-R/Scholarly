package com.ulacit.app;

import com.ulacit.login.Login;
import com.ulacit.academico.Plataforma;
import com.ulacit.gestiondegrupos.ModuloGestiondeGrupos;
import com.ulacit.mensajeria.Chatmensajes;
import com.ulacit.academico.ModuloAdministracionCursos;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Login(() -> {
                mostrarMenu();
            }).setVisible(true);
        });
    }
    
    public static void mostrarMenu() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Plataforma Académica");
            System.out.println("2. Gestión de Grupos");
            System.out.println("3. Revisión de Mensajes");
            System.out.println("4. Administración de Cursos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    Plataforma.main(new String[]{});
                    break;
                case 2:
                    ModuloGestiondeGrupos.main(new String[]{});
                    break;
                case 3:
                    Chatmensajes.main(new String[]{});
                    break;
                case 4:
                    javax.swing.SwingUtilities.invokeLater(() -> new ModuloAdministracionCursos().showAdminCursosUI());
                    break;
                case 0:
                    System.out.println("Saliendo del sistema.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        scanner.close();
    }
}