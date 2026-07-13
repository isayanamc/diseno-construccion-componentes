package laboratorioseguridad.model;

/**
 * Representa un registro de la colección "acciones".
 * Cada acción pertenece a un menú (campo "menu") y define una URL asociada.
 */
public class Accion {

    private String id;
    private String nombre;
    private String descripcion;
    private String url;
    private String menu;
    private String estado;

    public Accion() {
    }

    public Accion(String nombre, String descripcion, String url, String menu, String estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
        this.menu = menu;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Accion{id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion
                + ", url=" + url + ", menu=" + menu + ", estado=" + estado + "}";
    }
}
