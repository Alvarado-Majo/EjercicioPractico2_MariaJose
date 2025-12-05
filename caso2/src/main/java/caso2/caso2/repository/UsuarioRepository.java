package caso2.caso2.repository;

import caso2.caso2.domain.Usuario;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Para login: buscar usuario por email
    Optional<Usuario> findByEmail(String email);

    // 1) Buscar usuarios por rol (ADMIN, PROFESOR, ESTUDIANTE)
    List<Usuario> findByRol_Nombre(String nombre);

    // 2) Buscar usuarios creados en un rango de fechas
    List<Usuario> findByFechaCreacionBetween(Timestamp inicio, Timestamp fin);

    // 3) Buscar por coincidencia parcial en correo o nombre
    List<Usuario> findByEmailContainingIgnoreCaseOrNombreContainingIgnoreCase(
            String email,
            String nombre
    );

    // Extras para reportes: contar activos / inactivos
    long countByActivoTrue();
    long countByActivoFalse();

    // Obtener usuarios ordenados por fecha de creación (más nuevos primero)
    List<Usuario> findAllByOrderByFechaCreacionDesc();
}
