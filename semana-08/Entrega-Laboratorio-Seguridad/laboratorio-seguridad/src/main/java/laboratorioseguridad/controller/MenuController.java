package laboratorioseguridad.controller;

import laboratorioseguridad.dao.ApiResponse;
import laboratorioseguridad.dao.MenuDAO;
import laboratorioseguridad.model.Menu;

import java.util.List;

/**
 * Controlador de la entidad Menu. Aplica validaciones básicas antes de
 * llamar al DAO y traduce las respuestas del API a resultados que la
 * vista pueda mostrar.
 */
public class MenuController {

    private final MenuDAO menuDAO;

    public MenuController() {
        this.menuDAO = new MenuDAO();
    }

    public String insertar(String nombre, String descripcion, String estado) {
        String error = validarDatos(nombre, descripcion, estado);
        if (error != null) {
            return error;
        }
        Menu menu = new Menu(nombre.trim(), descripcion.trim(), estado.trim());
        ApiResponse respuesta = menuDAO.insertar(menu);
        return interpretarRespuesta(respuesta, "Menú insertado correctamente.");
    }

    public List<Menu> consultarTodos() {
        ApiResponse respuesta = menuDAO.consultarTodos();
        if (!respuesta.isExitoso()) {
            throw new RuntimeException(respuesta.getMensajeError());
        }
        return menuDAO.convertirAMenus(respuesta.getBody());
    }

    public List<Menu> buscarPorCampo(String campo, String valor) {
        if (campo == null || campo.isBlank() || valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El campo y el valor de búsqueda no pueden estar vacíos.");
        }
        ApiResponse respuesta = menuDAO.consultarPorFiltro(campo.trim(), valor.trim());
        if (!respuesta.isExitoso()) {
            throw new RuntimeException(respuesta.getMensajeError());
        }
        return menuDAO.convertirAMenus(respuesta.getBody());
    }

    public String actualizar(String id, String nombre, String descripcion, String estado) {
        if (id == null || id.isBlank()) {
            return "El id del menú es obligatorio para actualizar.";
        }
        Menu menu = new Menu();
        menu.setNombre(vacioComoNulo(nombre));
        menu.setDescripcion(vacioComoNulo(descripcion));
        menu.setEstado(vacioComoNulo(estado));

        ApiResponse respuesta = menuDAO.actualizar(id.trim(), menu);
        return interpretarRespuesta(respuesta, "Menú actualizado correctamente.");
    }

    public String eliminar(String id) {
        if (id == null || id.isBlank()) {
            return "El id del menú es obligatorio para eliminar.";
        }
        ApiResponse respuesta = menuDAO.eliminar(id.trim());
        return interpretarRespuesta(respuesta, "Menú eliminado correctamente.");
    }

    private String validarDatos(String nombre, String descripcion, String estado) {
        if (nombre == null || nombre.isBlank()) return "El nombre del menú es obligatorio.";
        if (descripcion == null || descripcion.isBlank()) return "La descripción del menú es obligatoria.";
        if (estado == null || estado.isBlank()) return "El estado del menú es obligatorio.";
        return null;
    }

    private String vacioComoNulo(String valor) {
        return (valor == null || valor.isBlank()) ? null : valor.trim();
    }

    private String interpretarRespuesta(ApiResponse respuesta, String mensajeExito) {
        if (respuesta.isExitoso()) {
            return mensajeExito;
        }
        return "Error: " + respuesta.getMensajeError();
    }
}
