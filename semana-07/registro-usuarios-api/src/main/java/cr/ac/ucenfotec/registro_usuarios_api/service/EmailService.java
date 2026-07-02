package cr.ac.ucenfotec.registro_usuarios_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private static final String EMAIL_API_URL = "https://paginas-web-cr.com/Api/apis/sendEmail.php";

    @Autowired
    private RestTemplate restTemplate;

    public boolean enviarCorreoBienvenida(String correoDestino) {
        try {
            Map<String, String> cuerpo = new HashMap<>();
            cuerpo.put("email", correoDestino);
            cuerpo.put("title", "Bienvenido a nuestra plataforma");
            cuerpo.put("text", "Gracias por registrarte. Tu cuenta ya está activa.");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, String>> peticion = new HttpEntity<>(cuerpo, headers);

            ResponseEntity<String> respuesta = restTemplate.postForEntity(
                    EMAIL_API_URL, peticion, String.class
            );

            System.out.println("Respuesta del API de correo: " + respuesta.getStatusCode());
            return respuesta.getStatusCode().is2xxSuccessful();

        } catch (Exception e) {
            System.err.println("Error al enviar correo de bienvenida: " + e.getMessage());
            return false;
        }
    }
}