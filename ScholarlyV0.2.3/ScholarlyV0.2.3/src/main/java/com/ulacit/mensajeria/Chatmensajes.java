package com.ulacit.mensajeria;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author sebas
 */
public class Chatmensajes {

    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
        List<Mensajeria> mensajes = new ArrayList<>();
        boolean salir = false;

        while (!salir) {//Menu de ejemplo
            System.out.println("\n---MENU--- ");
            System.out.println("1. Enviar un mensaje");
            System.out.println("2. IBOX ");
            System.out.println("3. Salir");
            System.out.print("Por favor seleccionar una opcion: ");
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    // ENVIAR MENSAJE
                    String remitente = leerIdNumerico(scanner, "Su cedula: ");
                    System.out.print("Su nombre: ");
                    String nombreRemitente = scanner.nextLine().trim();

                    String receptor = leerIdNumerico(scanner, "Cedula del receptor: ");
                    System.out.print("Nombre del receptor: ");
                    String nombreReceptor = scanner.nextLine().trim();

                    System.out.print("Asunto: ");
                    String contenido = scanner.nextLine();

                    Mensajeria mensaje = new Mensajeria(
                        "", remitente, nombreRemitente,
                        receptor, nombreReceptor,
                        contenido, LocalDateTime.now()
                    );
                    mensajes.add(mensaje);
                    System.out.println(" su mensaje ha sido enviado.");
                    break;

                case "2":
                    // VER INBOX
                    String idUsuario = leerIdNumerico(scanner, "Ingrese su Cedula para ver sus mensajes: ");
                    System.out.println("\n=== Bandeja de Entrada de " + idUsuario + " ===");
                    boolean hayMensajes = false;

                    for (Mensajeria msg : mensajes) {
                        if (msg.getIdReceptor().equals(idUsuario)) {
                            System.out.println("De: " + msg.getNombreRemitente() + " (ID: " + msg.getIdRemitente() + ")");
                            System.out.println("Mensaje: " + msg.getContenido());
                            System.out.println("Fecha: " + msg.getFechaHora());
                            System.out.println("------------------------------");
                            hayMensajes = true;
                        }
                    }

                    if (!hayMensajes) {
                        System.out.println("Sin mensajes por el momento.");
                    }
                    break;

                case "3":
                    salir = true;
                    System.out.println("Cerrando.");
                    break;

                default:
                    System.out.println("Opción no valida.");
            }
        }

        scanner.close();
    }

    // Método para validar que el ID ingresado es un número
    private static String leerIdNumerico(Scanner scanner, String etiqueta) {
        while (true) {
            System.out.print(etiqueta);
            String entrada = scanner.nextLine().trim();
            if (entrada.matches("\\d+")) {
                return entrada;
            } else {
                System.out.println(" Es necesario que la cedula sea ecritamente numeros.");
            }
        }
    }
}
       
    
    

