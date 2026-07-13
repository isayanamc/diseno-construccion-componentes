package laboratorioseguridad.view;

import laboratorioseguridad.controller.AccionController;
import laboratorioseguridad.controller.MenuController;
import laboratorioseguridad.controller.RolController;
import laboratorioseguridad.model.Accion;
import laboratorioseguridad.model.Menu;
import laboratorioseguridad.model.Rol;

import java.util.List;
import java.util.Scanner;

/**
 * Vista de consola. Solo se encarga de mostrar menús, leer datos del usuario
 * e imprimir resultados. Toda la lógica de negocio vive en los controladores.
 */
public class ConsoleView {

    private final Scanner scanner = new Scanner(System.in);
    private final MenuController menuController = new MenuController();
    private final AccionController accionController = new AccionController();
    private final RolController rolController = new RolController();

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n===== SISTEMA DE SEGURIDAD =====");
            System.out.println("1. Administrar Menús");
            System.out.println("2. Administrar Acciones");
            System.out.println("3. Administrar Roles");
            System.out.println("4. Salir");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> administrarMenus();
                case 2 -> administrarAcciones();
                case 3 -> administrarRoles();
                case 4 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    // ---------------------- MENÚS ----------------------

    private void administrarMenus() {
        int opcion;
        do {
            System.out.println("\n--- Administrar Menús ---");
            System.out.println("1. Insertar");
            System.out.println("2. Consultar");
            System.out.println("3. Buscar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("6. Regresar");
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> {
                        String nombre = leerTexto("Nombre: ");
                        String descripcion = leerTexto("Descripción: ");
                        String estado = leerTexto("Estado (Activo/Inactivo): ");
                        System.out.println(menuController.insertar(nombre, descripcion, estado));
                    }
                    case 2 -> {
                        List<Menu> menus = menuController.consultarTodos();
                        imprimirLista(menus, "menús");
                    }
                    case 3 -> {
                        String campo = leerTexto("Campo a buscar (ej. nombre, estado): ");
                        String valor = leerTexto("Valor: ");
                        imprimirLista(menuController.buscarPorCampo(campo, valor), "menús");
                    }
                    case 4 -> {
                        String id = leerTexto("Id del menú a actualizar: ");
                        String nombre = leerTexto("Nuevo nombre (Enter para no cambiar): ");
                        String descripcion = leerTexto("Nueva descripción (Enter para no cambiar): ");
                        String estado = leerTexto("Nuevo estado (Enter para no cambiar): ");
                        System.out.println(menuController.actualizar(id, nombre, descripcion, estado));
                    }
                    case 5 -> {
                        String id = leerTexto("Id del menú a eliminar: ");
                        System.out.println(menuController.eliminar(id));
                    }
                    case 6 -> System.out.println("Regresando al menú principal...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 6);
    }

    // ---------------------- ACCIONES ----------------------

    private void administrarAcciones() {
        int opcion;
        do {
            System.out.println("\n--- Administrar Acciones ---");
            System.out.println("1. Insertar");
            System.out.println("2. Consultar");
            System.out.println("3. Buscar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("6. Regresar");
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> {
                        String nombre = leerTexto("Nombre: ");
                        String descripcion = leerTexto("Descripción: ");
                        String url = leerTexto("URL: ");
                        String menu = leerTexto("Menú asociado: ");
                        String estado = leerTexto("Estado (Activo/Inactivo): ");
                        System.out.println(accionController.insertar(nombre, descripcion, url, menu, estado));
                    }
                    case 2 -> imprimirLista(accionController.consultarTodos(), "acciones");
                    case 3 -> {
                        String campo = leerTexto("Campo a buscar (ej. menu, estado): ");
                        String valor = leerTexto("Valor: ");
                        imprimirLista(accionController.buscarPorCampo(campo, valor), "acciones");
                    }
                    case 4 -> {
                        String id = leerTexto("Id de la acción a actualizar: ");
                        String nombre = leerTexto("Nuevo nombre (Enter para no cambiar): ");
                        String descripcion = leerTexto("Nueva descripción (Enter para no cambiar): ");
                        String url = leerTexto("Nueva URL (Enter para no cambiar): ");
                        String menu = leerTexto("Nuevo menú asociado (Enter para no cambiar): ");
                        String estado = leerTexto("Nuevo estado (Enter para no cambiar): ");
                        System.out.println(accionController.actualizar(id, nombre, descripcion, url, menu, estado));
                    }
                    case 5 -> {
                        String id = leerTexto("Id de la acción a eliminar: ");
                        System.out.println(accionController.eliminar(id));
                    }
                    case 6 -> System.out.println("Regresando al menú principal...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 6);
    }

    // ---------------------- ROLES ----------------------

    private void administrarRoles() {
        int opcion;
        do {
            System.out.println("\n--- Administrar Roles ---");
            System.out.println("1. Insertar");
            System.out.println("2. Consultar");
            System.out.println("3. Buscar");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("6. Regresar");
            opcion = leerEntero("Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1 -> {
                        String nombre = leerTexto("Nombre: ");
                        String descripcion = leerTexto("Descripción: ");
                        String estado = leerTexto("Estado (Activo/Inactivo): ");
                        System.out.println(rolController.insertar(nombre, descripcion, estado));
                    }
                    case 2 -> imprimirLista(rolController.consultarTodos(), "roles");
                    case 3 -> {
                        String campo = leerTexto("Campo a buscar (ej. nombre, estado): ");
                        String valor = leerTexto("Valor: ");
                        imprimirLista(rolController.buscarPorCampo(campo, valor), "roles");
                    }
                    case 4 -> {
                        String id = leerTexto("Id del rol a actualizar: ");
                        String nombre = leerTexto("Nuevo nombre (Enter para no cambiar): ");
                        String descripcion = leerTexto("Nueva descripción (Enter para no cambiar): ");
                        String estado = leerTexto("Nuevo estado (Enter para no cambiar): ");
                        System.out.println(rolController.actualizar(id, nombre, descripcion, estado));
                    }
                    case 5 -> {
                        String id = leerTexto("Id del rol a eliminar: ");
                        System.out.println(rolController.eliminar(id));
                    }
                    case 6 -> System.out.println("Regresando al menú principal...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 6);
    }

    // ---------------------- UTILIDADES DE ENTRADA/SALIDA ----------------------

    private <T> void imprimirLista(List<T> lista, String nombreEntidad) {
        if (lista.isEmpty()) {
            System.out.println("No se encontraron " + nombreEntidad + ".");
            return;
        }
        lista.forEach(System.out::println);
    }

    private String leerTexto(String etiqueta) {
        System.out.print(etiqueta);
        return scanner.nextLine();
    }

    private int leerEntero(String etiqueta) {
        while (true) {
            System.out.print(etiqueta);
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingresa un número válido.");
            }
        }
    }
}
