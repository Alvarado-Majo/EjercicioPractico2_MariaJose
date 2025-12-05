package caso2.caso2.controller;

import caso2.caso2.domain.Rol;
import caso2.caso2.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    // LISTADO
    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("roles", rolService.listarRoles());
        return "rol/listado";
    }

    // NUEVO
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("rol", new Rol());
        return "rol/formulario";
    }

    // GUARDAR
    @PostMapping("/guardar")
    public String guardar(Rol rol) {
        rolService.guardar(rol);
        return "redirect:/rol/listado";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("rol", rolService.obtenerPorId(id));
        return "rol/formulario";
    }

    // ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        rolService.eliminar(id);
        return "redirect:/rol/listado";
    }
}
