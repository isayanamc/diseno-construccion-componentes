package laboratorioseguridad.controller;

import laboratorioseguridad.dao.ApiResponse;
import laboratorioseguridad.dao.RolDAO;
import laboratorioseguridad.model.Rol;

import java.util.List;

/**
 * Controlador de la entidad Rol. Valida los datos que llegan desde la
 * vista antes de llamar al RolDAO, y traduce las respuestas del API en
 * mensajes legibles para el usuario.
 */
public class RolController {

    private final RolDAO rolDAO;

    public RolController() {
        this.rolDAO = new RolDAO();
    }

    public String insertar(String nombre, String descripcion, String estado) {
        if (nombre == null || nombre.isBlank()) return "El nombre del rol es obligatorio.";
        if (descripcion == null || descripcion.isBlank()) return "La descripción del rol es obligatoria.";
        if (estado == null || estado.isBlank()) return "El estado del rol es obligatorio.";

        Rol rol = new Rol(nombre.trim(), descripcion.trim(), estado.trim());
        ApiResponse respuesta = rolDAO.insertar(rol);
        return interpretarRespuesta(respuesta, "Rol insertado correctamente.");
    }

    public List<Rol> consultarTodos() {
        ApiResponse respuesta = rolDAO.consultarTodos();
        if (!respuesta.isExitoso()) {
            throw new RuntimeException(respuesta.getMensajeError());
        }
        return rolDAO.convertirARoles(respuesta.getBody());
    }

    public List<Rol> buscarPorCampo(String campo, String valor) {
        if (campo == null || campo.isBlank() || valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El campo y el valor de búsqueda no pueden estar vacíos.");
        }
        ApiResponse respuesta = rolDAO.consultarPorFiltro(campo.trim(), valor.trim());
        if (!respuesta.isExitoso()) {
            throw new RuntimeException(respuesta.getMensajeError());
        }
        return rolDAO.convertirARoles(respuesta.getBody());
    }

    public String actualizar(String id, String nombre, String descripcion, String estado) {
        if (id == null || id.isBlank()) {
            return "El id del rol es obligatorio para actualizar.";
        }
        Rol rol = new Rol();
        rol.setNombre(vacioComoNulo(nombre));
        rol.setDescripcion(vacioComoNulo(descripcion));
        rol.setEstado(vacioComoNulo(estado));

        ApiResponse respuesta = rolDAO.actualizar(id.trim(), rol);
        return interpretarRespuesta(respuesta, "Rol actualizado correctamente.");
    }

    public String eliminar(String id) {
        if (id == null || id.isBlank()) {
            return "El id del rol es obligatorio para eliminar.";
        }
        ApiResponse respuesta = rolDAO.eliminar(id.trim());
        return interpretarRespuesta(respuesta, "Rol eliminado correctamente.");
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
