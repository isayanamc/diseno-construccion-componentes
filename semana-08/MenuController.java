package laboratorioseguridad.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import laboratorioseguridad.model.Menu;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO responsable únicamente de consumir el API REST para la colección "menus".
 * No conoce nada de reglas de negocio; eso vive en MenuController.
 */
public class MenuDAO {

    private static final String BASE_URL = "https://paginas-web-cr.com/Api/apis/mongodb.php";
    private static final String COLECCION = "menus";

    private final ApiRestClient apiRestClient;

    public MenuDAO() {
        this.apiRestClient = new ApiRestClient();
    }

    public ApiResponse insertar(Menu menu) {
        JsonObject datos = new JsonObject();
        datos.addProperty("nombre", menu.getNombre());
        datos.addProperty("descripcion", menu.getDescripcion());
        datos.addProperty("estado", menu.getEstado());

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

    public ApiResponse actualizar(String id, Menu menu) {
        JsonObject filtro = new JsonObject();
        filtro.addProperty("_id", id);

        JsonObject datos = new JsonObject();
        if (menu.getNombre() != null) datos.addProperty("nombre", menu.getNombre());
        if (menu.getDescripcion() != null) datos.addProperty("descripcion", menu.getDescripcion());
        if (menu.getEstado() != null) datos.addProperty("estado", menu.getEstado());

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

    /** Convierte el JSON crudo de una respuesta GET en una lista de objetos Menu. */
    public List<Menu> convertirAMenus(String bodyJson) {
        List<Menu> menus = new ArrayList<>();
        JsonArray documentos = MongoJsonUtil.extraerDocumentos(bodyJson);

        for (int i = 0; i < documentos.size(); i++) {
            JsonObject doc = documentos.get(i).getAsJsonObject();
            Menu menu = new Menu();
            menu.setId(MongoJsonUtil.extraerId(doc));
            menu.setNombre(MongoJsonUtil.getStringOrNull(doc, "nombre"));
            menu.setDescripcion(MongoJsonUtil.getStringOrNull(doc, "descripcion"));
            menu.setEstado(MongoJsonUtil.getStringOrNull(doc, "estado"));
            menus.add(menu);
        }
        return menus;
    }

    private String urlEncode(String valor) {
        return URLEncoder.encode(valor, StandardCharsets.UTF_8);
    }
}
