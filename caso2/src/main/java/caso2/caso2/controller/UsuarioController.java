package caso2.caso2.controller;

import caso2.caso2.domain.Usuario;
import caso2.caso2.service.UsuarioService;
import caso2.caso2.service.RolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolService rolService;

    public UsuarioController(UsuarioService usuarioService,
                             RolService rolService) {
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    // LISTADO -----------------------------------------------------------------
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuario/listado";   // templates/usuario/listado.html
    }

    // NUEVO -------------------------------------------------------------------
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolService.listarTodos());
        return "usuario/formulario";  // templates/usuario/formulario.html
    }

    // GUARDAR (crear / editar) -----------------------------------------------
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/usuario/listado";
    }

    // EDITAR ------------------------------------------------------------------
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Id de usuario inválido: " + id));

        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolService.listarTodos());
        return "usuario/formulario";
    }

    // DETALLE -----------------------------------------------------------------
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Id de usuario inválido: " + id));

        model.addAttribute("usuario", usuario);
        return "usuario/detalle";   // templates/usuario/detalle.html
    }

    // ELIMINAR ----------------------------------------------------------------
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        usuarioService.eliminar(id);
        return "redirect:/usuario/listado";
    }
}
