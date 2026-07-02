package cr.ac.ucenfotec.registrousuariosapp.service;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class UsuarioApiClient {

    private static final String BASE_URL = "http://localhost:8080/api/usuarios";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();

    public static class RespuestaRegistro {
        public final boolean exitoso;
        public final String mensaje;

        public RespuestaRegistro(boolean exitoso, String mensaje) {
            this.exitoso = exitoso;
            this.mensaje = mensaje;
        }
    }

    public RespuestaRegistro registrarUsuario(String nombre, String correo, String password) {
        try {
            JSONObject cuerpo = new JSONObject();
            cuerpo.put("nombre", nombre);
            cuerpo.put("correo", correo);
            cuerpo.put("password", password);

            RequestBody body = RequestBody.create(cuerpo.toString(), JSON);
            Request request = new Request.Builder()
                    .url(BASE_URL)
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String respuestaTexto = response.body() != null ? response.body().string() : "";

                if (response.isSuccessful()) {
                    return new RespuestaRegistro(true, "Usuario registrado exitosamente. Revisa tu correo.");
                } else {
                    String mensajeError = "Error al registrar el usuario.";
                    try {
                        JSONObject json = new JSONObject(respuestaTexto);
                        if (json.has("error")) {
                            mensajeError = json.getString("error");
                        }
                    } catch (Exception ignored) {
                    }
                    return new RespuestaRegistro(false, mensajeError);
                }
            }

        } catch (IOException e) {
            return new RespuestaRegistro(false, "No se pudo conectar con el servidor. ¿Está corriendo el API?");
        }
    }
}