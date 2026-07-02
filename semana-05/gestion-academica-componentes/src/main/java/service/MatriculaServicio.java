package service;

import interfaces.IMatriculaServicio;
import model.Matricula;
import org.json.JSONArray;
import org.json.JSONObject;
import util.MongoDBUtil;

import java.util.ArrayList;
import java.util.List;

public class MatriculaServicio implements IMatriculaServicio {

    private static final String COLECCION = "matriculasIsaMurillo";

    @Override
    public String insertar(Matricula matricula) {
        JSONObject datos = new JSONObject();
        datos.put("estudianteId", matricula.getEstudianteId());
        datos.put("cursoId", matricula.getCursoId());
        datos.put("fechaMatricula", matricula.getFechaMatricula());
        datos.put("estado", matricula.getEstado());

        return MongoDBUtil.insertar(COLECCION, datos);
    }

    @Override
    public List<Matricula> consultarTodos() {
        String respuesta = MongoDBUtil.consultarTodos(COLECCION);
        List<Matricula> lista = new ArrayList<>();

        JSONObject json = new JSONObject(respuesta);
        JSONArray array = json.getJSONArray("documentos");

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            Matricula m = new Matricula();
            m.setId(obj.getJSONObject("_id").optString("$oid"));
            m.setEstudianteId(obj.optString("estudianteId"));
            m.setCursoId(obj.optString("cursoId"));
            m.setFechaMatricula(obj.optString("fechaMatricula"));
            m.setEstado(obj.optString("estado"));
            lista.add(m);
        }
        return lista;
    }

    @Override
    public Matricula consultarPorId(String id) {
        String respuesta = MongoDBUtil.consultarPorId(COLECCION, id);
        JSONObject obj = new JSONObject(respuesta);

        Matricula m = new Matricula();
        m.setId(obj.optString("_id"));
        m.setEstudianteId(obj.optString("estudianteId"));
        m.setCursoId(obj.optString("cursoId"));
        m.setFechaMatricula(obj.optString("fechaMatricula"));
        m.setEstado(obj.optString("estado"));
        return m;
    }

    @Override
    public String cambiarEstado(String id, String nuevoEstado) {
        // Solo actualiza el campo estado, no toda la matrícula
        JSONObject datos = new JSONObject();
        datos.put("estado", nuevoEstado);

        return MongoDBUtil.actualizar(COLECCION, id, datos);
    }

    @Override
    public String eliminar(String id) {
        return MongoDBUtil.eliminar(COLECCION, id);
    }
}