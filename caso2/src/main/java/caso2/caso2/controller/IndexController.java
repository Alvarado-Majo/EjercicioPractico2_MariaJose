package caso2.caso2.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String inicio(Authentication auth) {

        if (auth.getAuthorities().contains(
                new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/usuario/listado";
        }

        if (auth.getAuthorities().contains(
                new SimpleGrantedAuthority("ROLE_PROFESOR"))) {
            return "redirect:/reportes";
        }

        if (auth.getAuthorities().contains(
                new SimpleGrantedAuthority("ROLE_ESTUDIANTE"))) {
            return "redirect:/perfil";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
