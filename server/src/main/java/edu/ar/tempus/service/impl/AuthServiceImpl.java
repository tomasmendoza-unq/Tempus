package edu.ar.tempus.service.impl;


import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import edu.ar.tempus.controller.dto.usuario.LoginResponseDTO;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.security.jwt.JwtService;
import edu.ar.tempus.security.user.UserDetailsImpl;
import edu.ar.tempus.service.AuthService;
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
    private final PhoneNumberUtil phoneNumberUtil;

    public AuthServiceImpl(
            UsuarioService usuarioService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.phoneNumberUtil = PhoneNumberUtil.getInstance();
    }

    public Usuario registrarUsuario(Usuario usuario) {
        // Validar y formatear teléfono
        if (usuario.getTelefono() != null && !usuario.getTelefono().isBlank()) {
            String telefonoFormateado = validarYFormatearTelefono(usuario.getTelefono());
            usuario.setTelefono(telefonoFormateado);
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioService.guardarUsuario(usuario);
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

    private String validarYFormatearTelefono(String telefono) {
        try {
            Phonenumber.PhoneNumber numeroTelefono = phoneNumberUtil.parse(telefono, "AR");
            if (!phoneNumberUtil.isValidNumber(numeroTelefono)) {
                throw new IllegalArgumentException("El número de teléfono no es válido");
            }
            return phoneNumberUtil.format(numeroTelefono, PhoneNumberUtil.PhoneNumberFormat.E164);

        } catch (NumberParseException e) {
            throw new IllegalArgumentException("El formato del teléfono es inválido: ");
        }
    }
}