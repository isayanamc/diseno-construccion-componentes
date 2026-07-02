package util;

import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;

public class MongoDBUtil {

    private static final String BASE_URL = "https://paginas-web-cr.com/Api/apis/mongodb.php";
    private static final OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    // INSERTAR — POST
    public static String insertar(String coleccion, JSONObject datos) {
        JSONObject body = new JSONObject();
        body.put("coleccion", coleccion);
        body.put("datos", datos);

        RequestBody requestBody = RequestBody.create(body.toString(), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(requestBody)
                .build();
        return ejecutar(request);
    }

    // CONSULTAR TODOS — GET
    public static String consultarTodos(String coleccion) {
        String url = BASE_URL + "?coleccion=" + coleccion;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return ejecutar(request);
    }

    // CONSULTAR POR ID — GET con filtro
    public static String consultarPorId(String coleccion, String id) {
        // Trae todos y filtramos en Java por el $oid
        String url = BASE_URL + "?coleccion=" + coleccion;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return ejecutar(request);
    }

    // ACTUALIZAR — PUT
    public static String actualizar(String coleccion, String id, JSONObject datos) {
        JSONObject body = new JSONObject();
        body.put("coleccion", coleccion);
        body.put("filtro", new JSONObject().put("_id", id));
        body.put("datos", datos);

        RequestBody requestBody = RequestBody.create(body.toString(), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL)
                .put(requestBody)
                .build();
        return ejecutar(request);
    }

    // ELIMINAR — DELETE
    public static String eliminar(String coleccion, String id) {
        JSONObject body = new JSONObject();
        body.put("coleccion", coleccion);
        body.put("filtro", new JSONObject().put("_id", id));

        RequestBody requestBody = RequestBody.create(body.toString(), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL)
                .delete(requestBody)
                .build();
        return ejecutar(request);
    }

    // Método interno
    private static String ejecutar(Request request) {
        try (Response response = client.newCall(request).execute()) {
            return response.body() != null ? response.body().string() : "Sin respuesta";
        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }
    }
}