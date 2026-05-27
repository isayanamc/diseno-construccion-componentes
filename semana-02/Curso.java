import java.util.ArrayList;
import java.util.List;

public class Curso {

    private String id;
    private String nombre;
    private String descripcion;
    private String tiempo;
    private String usuario;

    private List<Grupo> grupos = new ArrayList<>();
    private List<Clientes> clientes = new ArrayList<>();

    public Curso() {}

    public Curso(String id, String nombre, String descripcion, String tiempo, String usuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiempo = tiempo;
        this.usuario = usuario;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getTiempo() { return tiempo; }
    public void setTiempo(String tiempo) { this.tiempo = tiempo; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public List<Grupo> getGrupos() { return grupos; }
    public void agregarGrupo(Grupo g) { if (g != null) grupos.add(g); }

    public List<Clientes> getClientes() { return clientes; }
    public void agregarCliente(Clientes c) { if (c != null) clientes.add(c); }

    @Override
    public String toString() {
        return "Curso: " + nombre + " | Tiempo: " + tiempo + " | Usuario: " + usuario;
    }
}