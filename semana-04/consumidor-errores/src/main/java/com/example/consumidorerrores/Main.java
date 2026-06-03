import model.ErrorLog;
import service.ErrorService;

public class Main {
    public static void main(String[] args) {

        ErrorService servicio = new ErrorService();

        // Registrar un error nuevo desde el consumidor
        System.out.println("=== Registrando error desde consumidor ===");
        ErrorLog error = new ErrorLog(
                "2026-06-02 20:30:00",
                "Pagos",
                "No se completó la transacción",
                "Timeout en la conexión",
                "isa"
        );
        servicio.registrarError(error);

        // Consultar todos
        System.out.println("\n=== Consultando todos los errores ===");
        System.out.println(servicio.consultarErrores());
    }
}