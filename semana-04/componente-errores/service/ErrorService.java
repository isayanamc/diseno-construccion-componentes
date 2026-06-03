package service;

import model.ErrorLog;
import util.ConexionApi;

public class ErrorService {

    // *** CAMBIÁ esto por tu nombre y apellido real ***
    private static final String COLECCION = "erroresIsaMurillo";

    private final ConexionApi api = new ConexionApi();

    public void registrarError(ErrorLog error) {
        String json = """
                {
                    "coleccion":"%s",
                    "datos":{
                        "fecha":"%s",
                        "modulo":"%s",
                        "mensaje":"%s",
                        "detalle":"%s",
                        "usuario":"%s"
                    }
                }
                """.formatted(
                COLECCION,
                error.getFecha(),
                error.getModulo(),
                error.getMensaje(),
                error.getDetalle(),
                error.getUsuario()
        );

        String respuesta = api.post(json);
        System.out.println("Respuesta: " + respuesta);
    }

    public String consultarErrores() {
        return api.get("coleccion=" + COLECCION);
    }

    public String consultarErroresPorModulo(String modulo) {
        return api.get("coleccion=" + COLECCION + "&modulo=" + modulo);
    }

}