package caso2.caso2.controller;

import caso2.caso2.domain.Usuario;
import caso2.caso2.service.RolService;
import caso2.caso2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    // LISTAR
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuario/listado";
    }

    // NUEVO
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolService.listarRoles());
        return "usuario/formulario";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/usuario/listado";
    }

    // MODIFICAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("usuario", usuarioService.obtenerPorId(id));
        model.addAttribute("roles", rolService.listarRoles());
        return "usuario/formulario";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        usuarioService.eliminar(id);
        return "redirect:/usuario/listado";
    }

    // DETALLE
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable("id") Long id, Model model) {
        model.addAttribute("usuario", usuarioService.obtenerPorId(id));
        return "usuario/detalle";
    }
}
