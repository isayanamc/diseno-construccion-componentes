package cr.ac.ucenfotec.registrousuariosapp.components;

import javax.swing.*;
import java.awt.*;

public class CampoTexto extends JPanel {

    private final JTextField textField;

    public CampoTexto(String etiqueta) {
        setLayout(new BorderLayout(5, 2));
        JLabel label = new JLabel(etiqueta);
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(250, 28));

        add(label, BorderLayout.NORTH);
        add(textField, BorderLayout.CENTER);
    }

    public String getTexto() {
        return textField.getText().trim();
    }

    public void limpiar() {
        textField.setText("");
    }
}