package cr.ac.ucenfotec.registrousuariosapp.components;

import javax.swing.*;
import java.awt.*;

public class CampoPassword extends JPanel {

    private final JPasswordField passwordField;

    public CampoPassword(String etiqueta) {
        setLayout(new BorderLayout(5, 2));
        JLabel label = new JLabel(etiqueta);
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(250, 28));

        add(label, BorderLayout.NORTH);
        add(passwordField, BorderLayout.CENTER);
    }

    public String getTexto() {
        return new String(passwordField.getPassword()).trim();
    }

    public void limpiar() {
        passwordField.setText("");
    }
}