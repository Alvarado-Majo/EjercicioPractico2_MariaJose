package caso2.caso2.controller;

import caso2.caso2.domain.Usuario;
import caso2.caso2.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ReportesController {

    private final UsuarioRepository usuarioRepository;

    public ReportesController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/reportes")
    public String verReportes(Model model) {

        // Traer todos los usuarios
        List<Usuario> usuarios = usuarioRepository.findAll();

        long total = usuarios.size();
        long activos = usuarios.stream().filter(Usuario::isActivo).count();
        long inactivos = total - activos;

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalUsuarios", total);
        model.addAttribute("activos", activos);
        model.addAttribute("inactivos", inactivos);

        // Renderiza templates/reportes.html
        return "reportes";
    }
}
