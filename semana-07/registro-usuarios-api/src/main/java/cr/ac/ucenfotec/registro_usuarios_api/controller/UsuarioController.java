package cr.ac.ucenfotec.registro_usuarios_api.controller;

import cr.ac.ucenfotec.registro_usuarios_api.dto.UsuarioRegistroRequest;
import cr.ac.ucenfotec.registro_usuarios_api.model.Usuario;
import cr.ac.ucenfotec.registro_usuarios_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*") // Permite consumir el API sin bloqueos CORS
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody UsuarioRegistroRequest request) {
        try {
            Usuario usuario = usuarioService.registrarUsuario(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioRegistroRequest request) {
        try {
            Usuario usuario = usuarioService.actualizarUsuario(id, request);
            return ResponseEntity.ok(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}