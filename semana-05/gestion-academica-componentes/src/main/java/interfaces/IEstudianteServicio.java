package interfaces;

import model.Estudiante;
import java.util.List;

public interface IEstudianteServicio {
    String insertar(Estudiante estudiante);
    List<Estudiante> consultarTodos();
    Estudiante consultarPorId(String id);
    String actualizar(String id, Estudiante estudiante);
    String eliminar(String id);
}