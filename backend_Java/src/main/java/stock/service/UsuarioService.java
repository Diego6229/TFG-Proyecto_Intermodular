package stock.service;

import java.util.List;
import org.springframework.stereotype.Service;
import stock.model.Usuario;
import stock.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerUsuarios() {
        return this.usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return (Usuario)this.usuarioRepository.findById(id).orElse(null);
    }

    public Usuario añadir(Usuario usuario) {
        return (Usuario)this.usuarioRepository.save(usuario);
    }

    public void eliminar(Usuario usuario) {
        this.usuarioRepository.delete(usuario);
    }
}