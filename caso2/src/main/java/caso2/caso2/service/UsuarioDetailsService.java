package caso2.caso2.service;

import caso2.caso2.domain.Usuario;
import caso2.caso2.repository.UsuarioRepository;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));

        // Spring Security requiere "ROLE_..." por convención
        String role = "ROLE_" + usuario.getRol().getNombre();

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(role.replace("ROLE_", "")) // le damos solo ADMIN / PROFESOR / ESTUDIANTE
                .build();
    }
}

