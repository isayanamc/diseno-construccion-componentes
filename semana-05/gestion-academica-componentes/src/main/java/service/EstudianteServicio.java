package service;

import interfaces.IEstudianteServicio;
import model.Estudiante;
import org.json.JSONArray;
import org.json.JSONObject;
import util.MongoDBUtil;

import java.util.ArrayList;
import java.util.List;

public class EstudianteServicio implements IEstudianteServicio {

    private static final String COLECCION = "estudiantesIsaMurillo";

    @Override
    public String insertar(Estudiante estudiante) {
        JSONObject datos = new JSONObject();
        datos.put("nombre", estudiante.getNombre());
        datos.put("apellido", estudiante.getApellido());
        datos.put("correo", estudiante.getCorreo());
        datos.put("telefono", estudiante.getTelefono());

        return MongoDBUtil.insertar(COLECCION, datos);
    }

    @Override
    public List<Estudiante> consultarTodos() {
        String respuesta = MongoDBUtil.consultarTodos(COLECCION);
        List<Estudiante> lista = new ArrayList<>();

        JSONObject json = new JSONObject(respuesta);
        JSONArray array = json.getJSONArray("documentos");

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            Estudiante e = new Estudiante();
            e.setId(obj.getJSONObject("_id").optString("$oid"));
            e.setNombre(obj.optString("nombre"));
            e.setApellido(obj.optString("apellido"));
            e.setCorreo(obj.optString("correo"));
            e.setTelefono(obj.optString("telefono"));
            lista.add(e);
        }
        return lista;
    }

    @Override
    public Estudiante consultarPorId(String id) {
        String respuesta = MongoDBUtil.consultarPorId(COLECCION, id);
        JSONObject obj = new JSONObject(respuesta);

        Estudiante e = new Estudiante();
        e.setId(obj.optString("_id"));
        e.setNombre(obj.optString("nombre"));
        e.setApellido(obj.optString("apellido"));
        e.setCorreo(obj.optString("correo"));
        e.setTelefono(obj.optString("telefono"));
        return e;
    }

    @Override
    public String actualizar(String id, Estudiante estudiante) {
        JSONObject datos = new JSONObject();
        datos.put("nombre", estudiante.getNombre());
        datos.put("apellido", estudiante.getApellido());
        datos.put("correo", estudiante.getCorreo());
        datos.put("telefono", estudiante.getTelefono());

        return MongoDBUtil.actualizar(COLECCION, id, datos);
    }

    @Override
    public String eliminar(String id) {
        return MongoDBUtil.eliminar(COLECCION, id);
    }
}