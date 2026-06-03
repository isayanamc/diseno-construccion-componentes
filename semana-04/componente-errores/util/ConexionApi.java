package util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionApi {

    private static final String URL_BASE =
            "https://paginas-web-cr.com/Api/apis/mongodb.php";

    public String post(String json) {
        try {
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_BASE))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response =
                    cliente.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("cod:" + response.statusCode());
            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error de conexión: " + e.getMessage();
        }
    }

    public String get(String parametros) {
        try {
            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_BASE + "?" + parametros))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    cliente.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error de conexión: " + e.getMessage();
        }
    }
}