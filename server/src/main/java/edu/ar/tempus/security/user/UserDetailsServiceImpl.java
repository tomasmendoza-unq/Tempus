package edu.ar.tempus.security.user;

import com.ecovida.ecommerce_backend.models.Usuario;
import com.ecovida.ecommerce_backend.services.interfaces.UsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsuarioService usuarioService;

    public UserDetailsServiceImpl(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioService.recuperarUsuarioPorEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new UserDetailsImpl(usuario);
    }
}
