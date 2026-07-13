package laboratorioseguridad.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * Utilidades para lidiar con las particularidades del API del profesor:
 * - Las respuestas de consulta vienen envueltas en "documentos".
 * - Los IDs de MongoDB vienen anidados como {"$oid": "..."} en vez de un string simple.
 */
public final class MongoJsonUtil {

    private MongoJsonUtil() {
    }

    /**
     * Extrae el arreglo de documentos de una respuesta de consulta.
     * Soporta tanto {"documentos":[...]} como un arreglo JSON directo,
     * por si el API responde de una u otra forma según el endpoint.
     */
    public static JsonArray extraerDocumentos(String bodyJson) {
        try {
            JsonElement root = JsonParser.parseString(bodyJson);

            if (root.isJsonArray()) {
                return root.getAsJsonArray();
            }
            if (root.isJsonObject()) {
                JsonObject obj = root.getAsJsonObject();
                if (obj.has("documentos") && obj.get("documentos").isJsonArray()) {
                    return obj.getAsJsonArray("documentos");
                }
                // Si vino un solo documento (sin envoltorio), lo devolvemos como arreglo de 1
                JsonArray unico = new JsonArray();
                unico.add(obj);
                return unico;
            }
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException("El API devolvió un JSON inválido: " + e.getMessage(), e);
        }
        return new JsonArray();
    }

    /**
     * Extrae el id de Mongo de un documento, soportando tanto
     * {"_id": {"$oid": "..."}} como {"_id": "..."}.
     */
    public static String extraerId(JsonObject documento) {
        if (documento == null || !documento.has("_id")) {
            return null;
        }
        JsonElement idElement = documento.get("_id");
        if (idElement.isJsonObject() && idElement.getAsJsonObject().has("$oid")) {
            return idElement.getAsJsonObject().get("$oid").getAsString();
        }
        if (idElement.isJsonPrimitive()) {
            return idElement.getAsString();
        }
        return null;
    }

    public static String getStringOrNull(JsonObject obj, String campo) {
        if (obj == null || !obj.has(campo) || obj.get(campo).isJsonNull()) {
            return null;
        }
        return obj.get(campo).getAsString();
    }
}
