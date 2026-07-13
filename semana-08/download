package laboratorioseguridad.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import laboratorioseguridad.model.Rol;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsable únicamente de consumir el API REST para la colección "roles".
 */
public class RolDAO {

    private static final String BASE_URL = "https://paginas-web-cr.com/Api/apis/mongodb.php";
    private static final String COLECCION = "roles";

    private final ApiRestClient apiRestClient;

    public RolDAO() {
        this.apiRestClient = new ApiRestClient();
    }

    public ApiResponse insertar(Rol rol) {
        JsonObject datos = new JsonObject();
        datos.addProperty("nombre", rol.getNombre());
        datos.addProperty("descripcion", rol.getDescripcion());
        datos.addProperty("estado", rol.getEstado());

        JsonObject body = new JsonObject();
        body.addProperty("coleccion", COLECCION);
        body.add("datos", datos);

        return apiRestClient.post(BASE_URL, body.toString());
    }

    public ApiResponse consultarTodos() {
        String url = BASE_URL + "?coleccion=" + COLECCION;
        return apiRestClient.get(url);
    }

    public ApiResponse consultarPorFiltro(String campo, String valor) {
        String url = BASE_URL + "?coleccion=" + COLECCION
                + "&" + urlEncode(campo) + "=" + urlEncode(valor);
        return apiRestClient.get(url);
    }

    public ApiResponse actualizar(String id, Rol rol) {
        JsonObject filtro = new JsonObject();
        filtro.addProperty("_id", id);

        JsonObject datos = new JsonObject();
        if (rol.getNombre() != null) datos.addProperty("nombre", rol.getNombre());
        if (rol.getDescripcion() != null) datos.addProperty("descripcion", rol.getDescripcion());
        if (rol.getEstado() != null) datos.addProperty("estado", rol.getEstado());

        JsonObject body = new JsonObject();
        body.addProperty("coleccion", COLECCION);
        body.add("filtro", filtro);
        body.add("datos", datos);

        return apiRestClient.put(BASE_URL, body.toString());
    }

    public ApiResponse eliminar(String id) {
        JsonObject filtro = new JsonObject();
        filtro.addProperty("_id", id);

        JsonObject body = new JsonObject();
        body.addProperty("coleccion", COLECCION);
        body.add("filtro", filtro);

        return apiRestClient.delete(BASE_URL, body.toString());
    }

    public List<Rol> convertirARoles(String bodyJson) {
        List<Rol> roles = new ArrayList<>();
        JsonArray documentos = MongoJsonUtil.extraerDocumentos(bodyJson);

        for (int i = 0; i < documentos.size(); i++) {
            JsonObject doc = documentos.get(i).getAsJsonObject();
            Rol rol = new Rol();
            rol.setId(MongoJsonUtil.extraerId(doc));
            rol.setNombre(MongoJsonUtil.getStringOrNull(doc, "nombre"));
            rol.setDescripcion(MongoJsonUtil.getStringOrNull(doc, "descripcion"));
            rol.setEstado(MongoJsonUtil.getStringOrNull(doc, "estado"));
            roles.add(rol);
        }
        return roles;
    }

    private String urlEncode(String valor) {
        return URLEncoder.encode(valor, StandardCharsets.UTF_8);
    }
}
