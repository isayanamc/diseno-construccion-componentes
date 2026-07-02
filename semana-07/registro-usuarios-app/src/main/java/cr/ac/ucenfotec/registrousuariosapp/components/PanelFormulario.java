package cr.ac.ucenfotec.registrousuariosapp.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelFormulario extends JPanel {

    private final CampoTexto campoNombre;
    private final CampoTexto campoCorreo;
    private final CampoPassword campoPassword;
    private final BotonRegistrar botonRegistrar;

    public PanelFormulario(ActionListener accionRegistrar) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        EtiquetaTitulo titulo = new EtiquetaTitulo("Registro de Usuario");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoNombre = new CampoTexto("Nombre:");
        campoCorreo = new CampoTexto("Correo electrónico:");
        campoPassword = new CampoPassword("Contraseña:");
        botonRegistrar = new BotonRegistrar("Registrar", accionRegistrar);

        campoNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoCorreo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(titulo);
        add(Box.createVerticalStrut(20));
        add(campoNombre);
        add(Box.createVerticalStrut(10));
        add(campoCorreo);
        add(Box.createVerticalStrut(10));
        add(campoPassword);
        add(Box.createVerticalStrut(20));
        add(botonRegistrar);
    }

    public String getNombre() {
        return campoNombre.getTexto();
    }

    public String getCorreo() {
        return campoCorreo.getTexto();
    }

    public String getPassword() {
        return campoPassword.getTexto();
    }

    public void limpiarCampos() {
        campoNombre.limpiar();
        campoCorreo.limpiar();
        campoPassword.limpiar();
    }
}