package com.ecovida.ecommerce_backend.services.impl;

import com.ecovida.ecommerce_backend.controller.dto.usuario.*;
import com.ecovida.ecommerce_backend.exceptions.businessException.EmailYaExisteException;
import com.ecovida.ecommerce_backend.models.Usuario;
import com.ecovida.ecommerce_backend.security.jwt.JwtService;
import com.ecovida.ecommerce_backend.security.jwt.impl.JwtServiceImpl;
import com.ecovida.ecommerce_backend.security.user.UserDetailsImpl;
import com.ecovida.ecommerce_backend.services.interfaces.AuthService;
import com.ecovida.ecommerce_backend.services.interfaces.UsuarioService;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
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
            PhoneNumber numeroTelefono = phoneNumberUtil.parse(telefono, "AR");
            if (!phoneNumberUtil.isValidNumber(numeroTelefono)) {
                throw new IllegalArgumentException("El número de teléfono no es válido");
            }
            return phoneNumberUtil.format(numeroTelefono, PhoneNumberUtil.PhoneNumberFormat.E164);

        } catch (NumberParseException e) {
            throw new IllegalArgumentException("El formato del teléfono es inválido: ");
        }
    }
}