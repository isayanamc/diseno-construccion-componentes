package laboratorioseguridad.model;

/**
 * Representa un registro de la colección "menus".
 * Solo contiene datos; no tiene ninguna lógica de negocio.
 */
public class Menu {

    private String id;
    private String nombre;
    private String descripcion;
    private String estado;

    public Menu() {
    }

    public Menu(String nombre, String descripcion, String estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Menu{id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estado=" + estado + "}";
    }
}
