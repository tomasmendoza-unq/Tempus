package edu.ar.tempus.feature.auth.service.impl;


import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import edu.ar.tempus.controller.dto.auth.LoginResponseDTO;
import edu.ar.tempus.feature.alumno.model.Alumno;
import edu.ar.tempus.feature.alumno.service.AlumnoService;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.security.jwt.JwtService;
import edu.ar.tempus.security.user.UserDetailsImpl;
import edu.ar.tempus.feature.auth.service.AuthService;
import edu.ar.tempus.service.UsuarioService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioService usuarioService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final AlumnoService alumnoService;

    public AuthServiceImpl(
            UsuarioService usuarioService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService, AlumnoService alumnoService) {

        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.alumnoService = alumnoService;
    }

    public Usuario registrar(Usuario usuario, Long carreraId) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return alumnoService.guardar((Alumno) usuario, carreraId);
    }

    public LoginResponseDTO autenticarUsuario(UsernamePasswordAuthenticationToken token) {
        String email = token.getPrincipal().toString();

        Usuario usuario = usuarioService.recuperarUsuarioPorEmail(email)
                .orElseThrow(() -> new BadCredentialsException(
                        "Email o contraseña incorrectos"
                ));

        authenticationManager.authenticate(token);

        UserDetails userDetails = new UserDetailsImpl(usuario);

        String jwt = jwtService.generarToken(userDetails);

        return new LoginResponseDTO(
                jwt,
                "Bearer",
                usuario.getId(),
                usuario.getEmail(),
                usuario.getRole().name()
        );
    }

    @Override
    public LoginResponseDTO generarRespuestaPostRegistro(Usuario usuario) {
        UserDetails userDetails = new UserDetailsImpl(usuario);

        String jwt = jwtService.generarToken(userDetails);

        return new LoginResponseDTO(
                jwt,
                "Bearer",
                usuario.getId(),
                usuario.getEmail(),
                usuario.getRole().name()
        );
    }


}