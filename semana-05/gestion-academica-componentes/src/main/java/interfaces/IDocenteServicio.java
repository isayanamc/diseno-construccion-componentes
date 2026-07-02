package interfaces;

import model.Docente;
import java.util.List;

public interface IDocenteServicio {
    String insertar(Docente docente);
    List<Docente> consultarTodos();
    Docente consultarPorId(String id);
    String actualizar(String id, Docente docente);
    String eliminar(String id);
}