package interfaces;

import model.Matricula;
import java.util.List;

public interface IMatriculaServicio {
    String insertar(Matricula matricula);
    List<Matricula> consultarTodos();
    Matricula consultarPorId(String id);
    String cambiarEstado(String id, String nuevoEstado);
    String eliminar(String id);
}