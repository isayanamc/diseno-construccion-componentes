package cr.ac.ucenfotec.registro_usuarios_api.service;

import cr.ac.ucenfotec.registro_usuarios_api.dto.UsuarioRegistroRequest;
import cr.ac.ucenfotec.registro_usuarios_api.model.Usuario;
import cr.ac.ucenfotec.registro_usuarios_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    public Usuario registrarUsuario(UsuarioRegistroRequest request) {
        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new IllegalArgumentException("Ya existe un usuario registrado con ese correo");
        }

        Usuario nuevoUsuario = new Usuario(
                request.getNombre(),
                request.getCorreo(),
                request.getPassword()
        );

        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        // Componente independiente: si el correo falla, el registro ya quedó hecho
        emailService.enviarCorreoBienvenida(usuarioGuardado.getCorreo());

        return usuarioGuardado;
    }

    public Usuario actualizarUsuario(Long id, UsuarioRegistroRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe un usuario con ese id"));

        // Si cambia el correo, verificar que no choque con otro usuario existente
        if (!usuario.getCorreo().equals(request.getCorreo())
                && usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new IllegalArgumentException("Ya existe otro usuario registrado con ese correo");
        }

        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setPassword(request.getPassword());

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}