package caso2.caso2.ProjectConfig;

import caso2.caso2.domain.Usuario;
import caso2.caso2.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // CONTRASEÑAS EN TEXTO PLANO (12345)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // CÓMO CARGAR USUARIOS DESDE LA BD
    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return email -> {
            Usuario usuario = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario"));

            // nombre del rol en BD: ADMIN / PROFESOR / ESTUDIANTE
            String rol = usuario.getRol().getNombre();

            return User.withUsername(usuario.getEmail())
                    .password(usuario.getPassword()) // 12345
                    .roles(rol)                      // sin "ROLE_"
                    .build();
        };
    }

    // REGLAS DE SEGURIDAD Y LOGIN
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login", "/css/**").permitAll()
                    .requestMatchers("/usuario/**", "/rol/**").hasRole("ADMIN")
                    .requestMatchers("/reportes/**").hasRole("PROFESOR")
                    .requestMatchers("/perfil/**").hasRole("ESTUDIANTE")
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/login")           // Vista personalizada
                    .defaultSuccessUrl("/", true)  // Siempre va a "/"
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
            )
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}
