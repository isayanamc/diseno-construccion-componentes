package cr.ac.ucenfotec.registrousuariosapp.components;

import java.util.regex.Pattern;

public class ValidadorFormulario {

    private static final Pattern PATRON_CORREO =
            Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    public static String validar(String nombre, String correo, String password) {
        if (nombre == null || nombre.isBlank()) {
            return "El nombre no puede estar vacío.";
        }
        if (nombre.length() < 3) {
            return "El nombre debe tener al menos 3 caracteres.";
        }
        if (correo == null || correo.isBlank()) {
            return "El correo no puede estar vacío.";
        }
        if (!PATRON_CORREO.matcher(correo).matches()) {
            return "El formato del correo no es válido.";
        }
        if (password == null || password.isBlank()) {
            return "La contraseña no puede estar vacía.";
        }
        if (password.length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres.";
        }
        return null; // null significa que todo está válido
    }
}