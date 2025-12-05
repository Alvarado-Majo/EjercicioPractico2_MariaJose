package caso2.caso2.controller;

import caso2.caso2.domain.Usuario;
import caso2.caso2.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

@Controller
public class PerfilController {

    private final UsuarioRepository usuarioRepository;

    public PerfilController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/perfil")
    public String verPerfil(Authentication auth, Model model) {

        String email = auth.getName(); // correo del usuario logueado

        Usuario usuario = usuarioRepository
                .findByEmail(email)
                .orElse(null);

        model.addAttribute("usuario", usuario);

        return "perfil";  // templates/perfil.html
    }
}
