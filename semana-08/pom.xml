package laboratorioseguridad.dao;

/**
 * Encapsula el resultado de una llamada al API: si fue exitosa,
 * el código de estado HTTP, el cuerpo (JSON crudo) y un mensaje de error legible.
 */
public class ApiResponse {

    private final boolean exitoso;
    private final int statusCode;
    private final String body;
    private final String mensajeError;

    private ApiResponse(boolean exitoso, int statusCode, String body, String mensajeError) {
        this.exitoso = exitoso;
        this.statusCode = statusCode;
        this.body = body;
        this.mensajeError = mensajeError;
    }

    public static ApiResponse ok(int statusCode, String body) {
        return new ApiResponse(true, statusCode, body, null);
    }

    public static ApiResponse error(int statusCode, String mensajeError) {
        return new ApiResponse(false, statusCode, null, mensajeError);
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public String getMensajeError() {
        return mensajeError;
    }
}
