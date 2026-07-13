package laboratorioseguridad.dao;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

/**
 * Cliente HTTP genérico para consumir el API REST externo (MongoDB).
 * Ningún DAO se conecta directo a MongoDB: todo pasa por aquí.
 * Centraliza el manejo de errores (API caída, timeout, 404, 500, etc.)
 * para que los DAO de Menu/Accion/Rol no repitan esta lógica.
 */
public class ApiRestClient {

    private static final Duration TIMEOUT = Duration.ofSeconds(10);
    private final HttpClient client;

    public ApiRestClient() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(TIMEOUT)
                .build();
    }

    // Algunos hostings (mod_security / firewalls básicos de hosting compartido)
    // bloquean con 403 las peticiones que no parecen venir de un navegador.
    // Por eso mandamos un User-Agent y Accept "normales" en cada petición.
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) LaboratorioSeguridad/1.0";

    public ApiResponse get(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(TIMEOUT)
                .header("User-Agent", USER_AGENT)
                .header("Accept", "application/json")
                .GET()
                .build();
        return enviar(request);
    }

    public ApiResponse post(String url, String jsonBody) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(TIMEOUT)
                .header("User-Agent", USER_AGENT)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        return enviar(request);
    }

    public ApiResponse put(String url, String jsonBody) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(TIMEOUT)
                .header("User-Agent", USER_AGENT)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        return enviar(request);
    }

    public ApiResponse delete(String url, String jsonBody) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(TIMEOUT)
                .header("User-Agent", USER_AGENT)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        return enviar(request);
    }

    private ApiResponse enviar(HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int status = response.statusCode();

            if (status == 403) {
                return ApiResponse.error(status, "Acceso rechazado por el servidor (403). Puede ser un bloqueo del hosting por User-Agent o por demasiadas peticiones seguidas.");
            }
            if (status == 404) {
                return ApiResponse.error(status, "El recurso solicitado no existe (404). Verifica la colección o el filtro.");
            }
            if (status == 500) {
                return ApiResponse.error(status, "Error interno del servidor del API (500). Intenta de nuevo más tarde.");
            }
            if (status >= 400) {
                return ApiResponse.error(status, "El API respondió con un error (" + status + "). Revisa los datos enviados.");
            }
            return ApiResponse.ok(status, response.body());

        } catch (HttpTimeoutException e) {
            return ApiResponse.error(-1, "Tiempo de espera agotado al contactar el API. Verifica tu conexión e intenta de nuevo.");
        } catch (ConnectException e) {
            return ApiResponse.error(-1, "No se pudo establecer conexión con el API. ¿Está disponible el servicio?");
        } catch (IOException e) {
            return ApiResponse.error(-1, "El API no está disponible o hubo un error de comunicación: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ApiResponse.error(-1, "La petición fue interrumpida.");
        } catch (IllegalArgumentException e) {
            return ApiResponse.error(-1, "URL o datos inválidos: " + e.getMessage());
        }
    }
}
