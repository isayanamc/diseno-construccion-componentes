package cr.ac.ucenfotec.registrousuariosapp.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BotonRegistrar extends JButton {

    public BotonRegistrar(String texto, ActionListener accion) {
        super(texto);
        setFont(new Font("Segoe UI", Font.BOLD, 14));
        setBackground(new Color(46, 125, 50));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setPreferredSize(new Dimension(150, 35));
        addActionListener(accion);
    }
}