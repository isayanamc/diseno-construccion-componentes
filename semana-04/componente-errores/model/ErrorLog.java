package model;

public class ErrorLog {
    private String fecha;
    private String modulo;
    private String mensaje;
    private String detalle;
    private String usuario;

    public ErrorLog(String fecha, String modulo, String mensaje,
                    String detalle, String usuario) {
        this.fecha   = fecha;
        this.modulo  = modulo;
        this.mensaje = mensaje;
        this.detalle = detalle;
        this.usuario = usuario;
    }

    public String getFecha()   { return fecha; }
    public String getModulo()  { return modulo; }
    public String getMensaje() { return mensaje; }
    public String getDetalle() { return detalle; }
    public String getUsuario() { return usuario; }
}