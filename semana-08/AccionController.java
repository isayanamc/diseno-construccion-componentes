package laboratorioseguridad.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import laboratorioseguridad.model.Accion;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsable únicamente de consumir el API REST para la colección "acciones".
 */
public class AccionDAO {

    private static final String BASE_URL = "https://paginas-web-cr.com/Api/apis/mongodb.php";
    private static final String COLECCION = "acciones";

    private final ApiRestClient apiRestClient;

    public AccionDAO() {
        this.apiRestClient = new ApiRestClient();
    }

    public ApiResponse insertar(Accion accion) {
        JsonObject datos = new JsonObject();
        datos.addProperty("nombre", accion.getNombre());
        datos.addProperty("descripcion", accion.getDescripcion());
        datos.addProperty("url", accion.getUrl());
        datos.addProperty("menu", accion.getMenu());
        datos.addProperty("estado", accion.getEstado());

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

    public ApiResponse actualizar(String id, Accion accion) {
        JsonObject filtro = new JsonObject();
        filtro.addProperty("_id", id);

        JsonObject datos = new JsonObject();
        if (accion.getNombre() != null) datos.addProperty("nombre", accion.getNombre());
        if (accion.getDescripcion() != null) datos.addProperty("descripcion", accion.getDescripcion());
        if (accion.getUrl() != null) datos.addProperty("url", accion.getUrl());
        if (accion.getMenu() != null) datos.addProperty("menu", accion.getMenu());
        if (accion.getEstado() != null) datos.addProperty("estado", accion.getEstado());

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

    public List<Accion> convertirAAcciones(String bodyJson) {
        List<Accion> acciones = new ArrayList<>();
        JsonArray documentos = MongoJsonUtil.extraerDocumentos(bodyJson);

        for (int i = 0; i < documentos.size(); i++) {
            JsonObject doc = documentos.get(i).getAsJsonObject();
            Accion accion = new Accion();
            accion.setId(MongoJsonUtil.extraerId(doc));
            accion.setNombre(MongoJsonUtil.getStringOrNull(doc, "nombre"));
            accion.setDescripcion(MongoJsonUtil.getStringOrNull(doc, "descripcion"));
            accion.setUrl(MongoJsonUtil.getStringOrNull(doc, "url"));
            accion.setMenu(MongoJsonUtil.getStringOrNull(doc, "menu"));
            accion.setEstado(MongoJsonUtil.getStringOrNull(doc, "estado"));
            acciones.add(accion);
        }
        return acciones;
    }

    private String urlEncode(String valor) {
        return URLEncoder.encode(valor, StandardCharsets.UTF_8);
    }
}
