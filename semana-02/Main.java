import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Curso curso = null;
        Grupo grupo = null;
        Clientes cliente = null;

        int opcion;

        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Crear Curso");
            System.out.println("2. Crear Grupo");
            System.out.println("3. Crear Cliente");
            System.out.println("4. Ver datos actuales");
            System.out.println("5. Modificar Curso");
            System.out.println("6. Modificar Grupo");
            System.out.println("7. Modificar Cliente");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {

                case 1:
                    System.out.println("\n--- Crear Curso ---");
                    System.out.print("ID: ");
                    String idC = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nomC = scanner.nextLine();
                    System.out.print("Descripción: ");
                    String desc = scanner.nextLine();
                    System.out.print("Tiempo: ");
                    String tiempo = scanner.nextLine();
                    System.out.print("Usuario: ");
                    String usuC = scanner.nextLine();
                    curso = new Curso(idC, nomC, desc, tiempo, usuC);
                    System.out.println("Curso creado con éxito.");
                    break;

                case 2:
                    System.out.println("\n--- Crear Grupo ---");
                    System.out.print("ID: ");
                    String idG = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nomG = scanner.nextLine();
                    System.out.print("Estado (Activo/Inactivo): ");
                    String estG = scanner.nextLine();
                    grupo = new Grupo(idG, nomG, estG);
                    System.out.println("Grupo creado con éxito.");
                    break;

                case 3:
                    System.out.println("\n--- Crear Cliente ---");
                    System.out.print("ID: ");
                    String idCl = scanner.nextLine();
                    System.out.print("Nombre: ");
                    String nomCl = scanner.nextLine();
                    System.out.print("Apellidos: ");
                    String apCl = scanner.nextLine();
                    System.out.print("Estado (1=Activo, 0=Inactivo): ");
                    String estCl = scanner.nextLine();
                    System.out.print("Usuario: ");
                    String usuCl = scanner.nextLine();
                    cliente = new Clientes(idCl, nomCl, apCl, estCl, usuCl);
                    System.out.println("Cliente creado con éxito.");
                    break;

                case 4:
                    System.out.println("\n--- Datos actuales ---");
                    System.out.println(curso != null ? curso : "No hay curso creado.");
                    System.out.println(grupo != null ? grupo : "No hay grupo creado.");
                    System.out.println(cliente != null ? cliente : "No hay cliente creado.");
                    break;

                case 5:
                    if (curso == null) {
                        System.out.println("Primero crea un curso.");
                    } else {
                        System.out.println("\n--- Modificar Curso ---");
                        System.out.print("Nuevo nombre: ");
                        curso.setNombre(scanner.nextLine());
                        System.out.print("Nueva descripción: ");
                        curso.setDescripcion(scanner.nextLine());
                        System.out.print("Nuevo tiempo: ");
                        curso.setTiempo(scanner.nextLine());
                        System.out.println("Curso modificado -> " + curso);
                    }
                    break;

                case 6:
                    if (grupo == null) {
                        System.out.println("Primero crea un grupo.");
                    } else {
                        System.out.println("\n--- Modificar Grupo ---");
                        System.out.print("Nuevo nombre: ");
                        grupo.setNombre(scanner.nextLine());
                        System.out.print("Nuevo estado (Activo/Inactivo): ");
                        grupo.setEstado(scanner.nextLine());
                        System.out.println("Grupo modificado -> " + grupo);
                    }
                    break;

                case 7:
                    if (cliente == null) {
                        System.out.println("Primero crea un cliente.");
                    } else {
                        System.out.println("\n--- Modificar Cliente ---");
                        System.out.print("Nuevo nombre: ");
                        cliente.setNombre(scanner.nextLine());
                        System.out.print("Nuevos apellidos: ");
                        cliente.setApellidos(scanner.nextLine());
                        System.out.print("Nuevo estado (1=Activo, 0=Inactivo): ");
                        cliente.setEstado(scanner.nextLine());
                        System.out.println("Cliente modificado -> " + cliente);
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opcion no valida, intenta de nuevo.");
            }

        } while (opcion != 0);

        scanner.close();
    }
}