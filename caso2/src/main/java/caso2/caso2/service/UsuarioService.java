package caso2.caso2.service;

import caso2.caso2.domain.Usuario;
import caso2.caso2.repository.UsuarioRepository;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // CRUD 
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Transactional
    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarPorRol(String nombreRol) {
        return usuarioRepository.findByRol_Nombre(nombreRol);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarPorRangoFechas(Timestamp inicio, Timestamp fin) {
        return usuarioRepository.findByFechaCreacionBetween(inicio, fin);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarPorTexto(String texto) {
        return usuarioRepository
                .findByEmailContainingIgnoreCaseOrNombreContainingIgnoreCase(texto, texto);
    }

    @Transactional(readOnly = true)
    public long contarActivos() {
        return usuarioRepository.countByActivoTrue();
    }

    @Transactional(readOnly = true)
    public long contarInactivos() {
        return usuarioRepository.countByActivoFalse();
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarOrdenadosPorFecha() {
        return usuarioRepository.findAllByOrderByFechaCreacionDesc();
    }
}
