package model;

public class Curso {

    private String id;
    private String nombre;
    private String descripcion;
    private int creditos;
    private String docenteId;

    public Curso() {}

    public Curso(String id, String nombre, String descripcion, int creditos, String docenteId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
        this.docenteId = docenteId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }

    public String getDocenteId() { return docenteId; }
    public void setDocenteId(String docenteId) { this.docenteId = docenteId; }

    @Override
    public String toString() {
        return "Curso{id='" + id + "', nombre='" + nombre + "', descripcion='" + descripcion +
                "', creditos=" + creditos + ", docenteId='" + docenteId + "'}";
    }
}