package cr.ac.ucenfotec.registrousuariosapp;

import cr.ac.ucenfotec.registrousuariosapp.view.VentanaRegistro;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaRegistro ventana = new VentanaRegistro();
            ventana.setVisible(true);
        });
    }
}