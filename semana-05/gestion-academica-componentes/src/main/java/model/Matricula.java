package model;

public class Matricula {

    private String id;
    private String estudianteId;
    private String cursoId;
    private String fechaMatricula;
    private String estado; // "ACTIVA", "CANCELADA", "FINALIZADA"

    public Matricula() {}

    public Matricula(String id, String estudianteId, String cursoId, String fechaMatricula, String estado) {
        this.id = id;
        this.estudianteId = estudianteId;
        this.cursoId = cursoId;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEstudianteId() { return estudianteId; }
    public void setEstudianteId(String estudianteId) { this.estudianteId = estudianteId; }

    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }

    public String getFechaMatricula() { return fechaMatricula; }
    public void setFechaMatricula(String fechaMatricula) { this.fechaMatricula = fechaMatricula; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Matricula{id='" + id + "', estudianteId='" + estudianteId + "', cursoId='" + cursoId +
                "', fechaMatricula='" + fechaMatricula + "', estado='" + estado + "'}";
    }
}