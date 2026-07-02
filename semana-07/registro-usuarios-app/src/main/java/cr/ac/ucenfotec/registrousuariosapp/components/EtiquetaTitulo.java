package cr.ac.ucenfotec.registrousuariosapp.components;

import javax.swing.*;
import java.awt.*;

public class EtiquetaTitulo extends JLabel {

    public EtiquetaTitulo(String texto) {
        super(texto);
        setFont(new Font("Segoe UI", Font.BOLD, 20));
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}