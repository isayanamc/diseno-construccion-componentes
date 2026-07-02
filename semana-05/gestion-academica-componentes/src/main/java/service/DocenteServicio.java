package service;

import interfaces.IDocenteServicio;
import model.Docente;
import org.json.JSONArray;
import org.json.JSONObject;
import util.MongoDBUtil;

import java.util.ArrayList;
import java.util.List;

public class DocenteServicio implements IDocenteServicio {

    private static final String COLECCION = "docentesIsaMurillo";

    @Override
    public String insertar(Docente docente) {
        JSONObject datos = new JSONObject();
        datos.put("nombre", docente.getNombre());
        datos.put("apellido", docente.getApellido());
        datos.put("correo", docente.getCorreo());
        datos.put("especialidad", docente.getEspecialidad());

        return MongoDBUtil.insertar(COLECCION, datos);
    }

    @Override
    public List<Docente> consultarTodos() {
        String respuesta = MongoDBUtil.consultarTodos(COLECCION);
        List<Docente> lista = new ArrayList<>();

        JSONObject json = new JSONObject(respuesta);
        JSONArray array = json.getJSONArray("documentos");

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            Docente d = new Docente();
            d.setId(obj.getJSONObject("_id").optString("$oid"));
            d.setNombre(obj.optString("nombre"));
            d.setApellido(obj.optString("apellido"));
            d.setCorreo(obj.optString("correo"));
            d.setEspecialidad(obj.optString("especialidad"));
            lista.add(d);
        }
        return lista;
    }

    @Override
    public Docente consultarPorId(String id) {
        String respuesta = MongoDBUtil.consultarPorId(COLECCION, id);
        JSONObject obj = new JSONObject(respuesta);

        Docente d = new Docente();
        d.setId(obj.optString("_id"));
        d.setNombre(obj.optString("nombre"));
        d.setApellido(obj.optString("apellido"));
        d.setCorreo(obj.optString("correo"));
        d.setEspecialidad(obj.optString("especialidad"));
        return d;
    }

    @Override
    public String actualizar(String id, Docente docente) {
        JSONObject datos = new JSONObject();
        datos.put("nombre", docente.getNombre());
        datos.put("apellido", docente.getApellido());
        datos.put("correo", docente.getCorreo());
        datos.put("especialidad", docente.getEspecialidad());

        return MongoDBUtil.actualizar(COLECCION, id, datos);
    }

    @Override
    public String eliminar(String id) {
        return MongoDBUtil.eliminar(COLECCION, id);
    }
}