package model;

public class Estudiante extends Persona {

    private String telefono;

    public Estudiante() {}

    public Estudiante(String id, String nombre, String apellido, String correo, String telefono) {
        super(id, nombre, apellido, correo);
        this.telefono = telefono;
    }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return "Estudiante{id='" + id + "', nombre='" + nombre + "', apellido='" + apellido +
                "', correo='" + correo + "', telefono='" + telefono + "'}";
    }
}