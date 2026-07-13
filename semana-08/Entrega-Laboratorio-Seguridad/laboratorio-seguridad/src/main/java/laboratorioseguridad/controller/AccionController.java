package laboratorioseguridad.controller;

import laboratorioseguridad.dao.AccionDAO;
import laboratorioseguridad.dao.ApiResponse;
import laboratorioseguridad.model.Accion;

import java.util.List;

/**
 * Controlador de la entidad Accion. Valida los datos que llegan desde la
 * vista antes de llamar al AccionDAO, y traduce las respuestas del API en
 * mensajes legibles para el usuario.
 */
public class AccionController {

    private final AccionDAO accionDAO;

    public AccionController() {
        this.accionDAO = new AccionDAO();
    }

    public String insertar(String nombre, String descripcion, String url, String menu, String estado) {
        if (nombre == null || nombre.isBlank()) return "El nombre de la acción es obligatorio.";
        if (descripcion == null || descripcion.isBlank()) return "La descripción de la acción es obligatoria.";
        if (url == null || url.isBlank()) return "La URL de la acción es obligatoria.";
        if (menu == null || menu.isBlank()) return "El menú asociado es obligatorio.";
        if (estado == null || estado.isBlank()) return "El estado de la acción es obligatorio.";

        Accion accion = new Accion(nombre.trim(), descripcion.trim(), url.trim(), menu.trim(), estado.trim());
        ApiResponse respuesta = accionDAO.insertar(accion);
        return interpretarRespuesta(respuesta, "Acción insertada correctamente.");
    }

    public List<Accion> consultarTodos() {
        ApiResponse respuesta = accionDAO.consultarTodos();
        if (!respuesta.isExitoso()) {
            throw new RuntimeException(respuesta.getMensajeError());
        }
        return accionDAO.convertirAAcciones(respuesta.getBody());
    }

    public List<Accion> buscarPorCampo(String campo, String valor) {
        if (campo == null || campo.isBlank() || valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El campo y el valor de búsqueda no pueden estar vacíos.");
        }
        ApiResponse respuesta = accionDAO.consultarPorFiltro(campo.trim(), valor.trim());
        if (!respuesta.isExitoso()) {
            throw new RuntimeException(respuesta.getMensajeError());
        }
        return accionDAO.convertirAAcciones(respuesta.getBody());
    }

    public String actualizar(String id, String nombre, String descripcion, String url, String menu, String estado) {
        if (id == null || id.isBlank()) {
            return "El id de la acción es obligatorio para actualizar.";
        }
        Accion accion = new Accion();
        accion.setNombre(vacioComoNulo(nombre));
        accion.setDescripcion(vacioComoNulo(descripcion));
        accion.setUrl(vacioComoNulo(url));
        accion.setMenu(vacioComoNulo(menu));
        accion.setEstado(vacioComoNulo(estado));

        ApiResponse respuesta = accionDAO.actualizar(id.trim(), accion);
        return interpretarRespuesta(respuesta, "Acción actualizada correctamente.");
    }

    public String eliminar(String id) {
        if (id == null || id.isBlank()) {
            return "El id de la acción es obligatorio para eliminar.";
        }
        ApiResponse respuesta = accionDAO.eliminar(id.trim());
        return interpretarRespuesta(respuesta, "Acción eliminada correctamente.");
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
