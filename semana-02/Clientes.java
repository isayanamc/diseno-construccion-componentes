public class Clientes {

    private String id;
    private String nombre;
    private String apellidos;
    private String estado;
    private String usuario;

    public Clientes(String id, String nombre, String apellidos, String estado, String usuario) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.estado = estado;
        this.usuario = usuario;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    @Override
    public String toString() {
        return "Cliente: " + nombre + " " + apellidos + " | Estado: " + estado + " | Usuario: " + usuario;
    }
}