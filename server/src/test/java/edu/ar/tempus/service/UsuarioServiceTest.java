package edu.ar.tempus.service;

import edu.ar.tempus.exceptions.business.EmailYaExisteException;
import edu.ar.tempus.model.Role;
import edu.ar.tempus.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ResetService  resetService;

    private String email ;

    private Usuario usuario1;
    private Usuario usuario2;

    @BeforeEach
    public void setUp() {

        email = "juan.perez@mail.com";

        usuario1 = Usuario.builder()
                .email(email)
                .password("password123")
                .nombre("Juan")
                .apellido("Pérez")
                .telefono("221-4567890")
                .role(Role.USER)
                .build();

        usuario2 = Usuario.builder()
                .email("maria.gonzalez@mail.com")
                .password("password456")
                .nombre("María")
                .apellido("González")
                .telefono("351-9876543")
                .role(Role.ADMIN)
                .build();

        usuario1 = usuarioService.guardarUsuario(usuario1);

        usuario2 = usuarioService.guardarUsuario(usuario2);
    }


    @Test
    public void recuperarUsuarioPorEmail() {

        Usuario usuarioRecuperado = usuarioService.recuperarUsuarioPorEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        assertEquals(email, usuarioRecuperado.getEmail());

    }

    @Test
    public void errorAlIntentarCargarDosUsuariosConElMismoEmail() {
        Usuario usuarioError = Usuario.builder()
                .email(email)
                .password("password123")
                .nombre("Juan")
                .apellido("Pérez")
                .telefono("221-4567890")
                .role(Role.USER)
                .build();

        assertThrows(EmailYaExisteException.class, () -> usuarioService.guardarUsuario(usuarioError));

    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}
