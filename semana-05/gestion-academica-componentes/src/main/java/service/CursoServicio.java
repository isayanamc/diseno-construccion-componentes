package service;

import interfaces.ICursoServicio;
import model.Curso;
import org.json.JSONArray;
import org.json.JSONObject;
import util.MongoDBUtil;

import java.util.ArrayList;
import java.util.List;

public class CursoServicio implements ICursoServicio {

    private static final String COLECCION = "cursosIsaMurillo";

    @Override
    public String insertar(Curso curso) {
        JSONObject datos = new JSONObject();
        datos.put("nombre", curso.getNombre());
        datos.put("descripcion", curso.getDescripcion());
        datos.put("creditos", curso.getCreditos());
        datos.put("docenteId", curso.getDocenteId());

        return MongoDBUtil.insertar(COLECCION, datos);
    }

    @Override
    public List<Curso> consultarTodos() {
        String respuesta = MongoDBUtil.consultarTodos(COLECCION);
        List<Curso> lista = new ArrayList<>();

        JSONObject json = new JSONObject(respuesta);
        JSONArray array = json.getJSONArray("documentos");

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            Curso c = new Curso();
            c.setId(obj.getJSONObject("_id").optString("$oid"));
            c.setNombre(obj.optString("nombre"));
            c.setDescripcion(obj.optString("descripcion"));
            c.setCreditos(obj.optInt("creditos"));
            c.setDocenteId(obj.optString("docenteId"));
            lista.add(c);
        }
        return lista;
    }

    @Override
    public Curso consultarPorId(String id) {
        String respuesta = MongoDBUtil.consultarPorId(COLECCION, id);
        JSONObject obj = new JSONObject(respuesta);

        Curso c = new Curso();
        c.setId(obj.optString("_id"));
        c.setNombre(obj.optString("nombre"));
        c.setDescripcion(obj.optString("descripcion"));
        c.setCreditos(obj.optInt("creditos"));
        c.setDocenteId(obj.optString("docenteId"));
        return c;
    }

    @Override
    public String actualizar(String id, Curso curso) {
        JSONObject datos = new JSONObject();
        datos.put("nombre", curso.getNombre());
        datos.put("descripcion", curso.getDescripcion());
        datos.put("creditos", curso.getCreditos());
        datos.put("docenteId", curso.getDocenteId());

        return MongoDBUtil.actualizar(COLECCION, id, datos);
    }

    @Override
    public String eliminar(String id) {
        return MongoDBUtil.eliminar(COLECCION, id);
    }
}