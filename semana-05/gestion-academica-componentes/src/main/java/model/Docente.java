package model;

public class Docente extends Persona {

    private String especialidad;

    public Docente() {}

    public Docente(String id, String nombre, String apellido, String correo, String especialidad) {
        super(id, nombre, apellido, correo);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    @Override
    public String toString() {
        return "Docente{id='" + id + "', nombre='" + nombre + "', apellido='" + apellido +
                "', correo='" + correo + "', especialidad='" + especialidad + "'}";
    }
}