package caso2.caso2.service;

import caso2.caso2.domain.Rol;
import caso2.caso2.repository.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Transactional(readOnly = true)
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Rol obtenerPorId(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    @Transactional
    public void guardar(Rol rol) {
        rolRepository.save(rol);
    }

    @Transactional
    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Rol obtenerPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre);
    }
}
