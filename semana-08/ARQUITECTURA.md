package laboratorioseguridad;

import laboratorioseguridad.view.ConsoleView;

/**
 * Punto de entrada de la aplicación.
 * Sistema de administración de Menús, Acciones y Roles
 * que consume un API REST externo (persistencia en MongoDB).
 */
public class Main {
    public static void main(String[] args) {
        new ConsoleView().iniciar();
    }
}
