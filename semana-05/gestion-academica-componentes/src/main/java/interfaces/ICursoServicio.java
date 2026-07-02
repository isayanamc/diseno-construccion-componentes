package interfaces;

import model.Curso;
import java.util.List;

public interface ICursoServicio {
    String insertar(Curso curso);
    List<Curso> consultarTodos();
    Curso consultarPorId(String id);
    String actualizar(String id, Curso curso);
    String eliminar(String id);
}