package cr.ac.ucenfotec.registrousuariosapp.view;

import cr.ac.ucenfotec.registrousuariosapp.components.PanelFormulario;
import cr.ac.ucenfotec.registrousuariosapp.components.ValidadorFormulario;
import cr.ac.ucenfotec.registrousuariosapp.service.UsuarioApiClient;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro extends JFrame {

    private final UsuarioApiClient apiClient = new UsuarioApiClient();
    private PanelFormulario panelFormulario;

    public VentanaRegistro() {
        setTitle("Registro de Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        panelFormulario = new PanelFormulario(e -> manejarRegistro());

        add(panelFormulario, BorderLayout.CENTER);
    }

    private void manejarRegistro() {
        String nombre = panelFormulario.getNombre();
        String correo = panelFormulario.getCorreo();
        String password = panelFormulario.getPassword();

        String errorValidacion = ValidadorFormulario.validar(nombre, correo, password);
        if (errorValidacion != null) {
            JOptionPane.showMessageDialog(this, errorValidacion, "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Deshabilitar el botón mientras se procesa, para evitar doble clic
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        // Ejecutar la llamada HTTP en un hilo aparte para no congelar la interfaz
        SwingWorker<UsuarioApiClient.RespuestaRegistro, Void> worker = new SwingWorker<>() {
            @Override
            protected UsuarioApiClient.RespuestaRegistro doInBackground() {
                return apiClient.registrarUsuario(nombre, correo, password);
            }

            @Override
            protected void done() {
                setCursor(Cursor.getDefaultCursor());
                try {
                    UsuarioApiClient.RespuestaRegistro resultado = get();
                    if (resultado.exitoso) {
                        JOptionPane.showMessageDialog(VentanaRegistro.this,
                                resultado.mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        panelFormulario.limpiarCampos();
                    } else {
                        JOptionPane.showMessageDialog(VentanaRegistro.this,
                                resultado.mensaje, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(VentanaRegistro.this,
                            "Ocurrió un error inesperado: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        worker.execute();
    }
}