package edu.ar.tempus.service;

import edu.ar.tempus.exceptions.business.EmailYaExisteException;
import edu.ar.tempus.model.Role;
import edu.ar.tempus.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AuthServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ResetService resetService;

    private String email;

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

        usuario1 = authService.registrarUsuario(usuario1);
        usuario2 = authService.registrarUsuario(usuario2);
    }

    @Test
    public void testRegistrarUsuarioConTelefonoValidoFormateaCorrectamente() {
        Usuario nuevoUsuario = Usuario.builder()
                .email("test@mail.com")
                .password("password123")
                .nombre("Test")
                .apellido("User")
                .telefono("11 5023-4567")
                .role(Role.USER)
                .build();

        Usuario usuarioGuardado = authService.registrarUsuario(nuevoUsuario);

        assertNotNull(usuarioGuardado);
        assertEquals("+541150234567", usuarioGuardado.getTelefono());
    }

    @Test
    public void testRegistrarUsuarioConTelefonoSinCodigoAreaSeAsume11() {
        Usuario nuevoUsuario = Usuario.builder()
                .email("test2@mail.com")
                .password("password123")
                .nombre("Test")
                .apellido("User")
                .telefono("1150234567")
                .role(Role.USER)
                .build();

        Usuario usuarioGuardado = authService.registrarUsuario(nuevoUsuario);

        assertNotNull(usuarioGuardado);
        assertEquals("+541150234567", usuarioGuardado.getTelefono());
    }

    @Test
    public void testRegistrarUsuarioConTelefonoConCodigoPais() {
        Usuario nuevoUsuario = Usuario.builder()
                .email("test3@mail.com")
                .password("password123")
                .nombre("Test")
                .apellido("User")

                .telefono("+54 11 5023-4567")
                .role(Role.USER)
                .build();

        Usuario usuarioGuardado = authService.registrarUsuario(nuevoUsuario);

        assertNotNull(usuarioGuardado);
        assertEquals("+541150234567", usuarioGuardado.getTelefono());
    }

    @Test
    public void testRegistrarUsuarioConTelefonoDeCordova() {
        Usuario nuevoUsuario = Usuario.builder()
                .email("test4@mail.com")
                .password("password123")
                .nombre("Test")
                .apellido("User")
                .telefono("351-5234567")
                .role(Role.USER)
                .build();

        Usuario usuarioGuardado = authService.registrarUsuario(nuevoUsuario);

        assertNotNull(usuarioGuardado);
        assertEquals("+543515234567", usuarioGuardado.getTelefono());
    }

    @Test
    public void testRegistrarUsuarioConTelefonoInvalidoLanzaExcepcion() {
        Usuario nuevoUsuario = Usuario.builder()
                .email("test5@mail.com")
                .password("password123")
                .nombre("Test")
                .apellido("User")

                .telefono("123")
                .role(Role.USER)
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> authService.registrarUsuario(nuevoUsuario)
        );

        assertTrue(exception.getMessage().contains("teléfono"));
    }

    @Test
    public void testRegistrarUsuarioConTelefonoConLetrasLanzaExcepcion() {
        Usuario nuevoUsuario = Usuario.builder()
                .email("test6@mail.com")
                .password("password123")
                .nombre("Test")
                .apellido("User")

                .telefono("11abc23456")
                .role(Role.USER)
                .build();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> authService.registrarUsuario(nuevoUsuario)
        );

        assertTrue(exception.getMessage().contains("formato") ||
                exception.getMessage().contains("inválido"));
    }

    @Test
    public void testRegistrarUsuarioConTelefonoMuyLargoLanzaExcepcion() {
        Usuario nuevoUsuario = Usuario.builder()
                .email("test7@mail.com")
                .password("password123")
                .nombre("Test")
                .apellido("User")

                .telefono("111111111111111111111")
                .role(Role.USER)
                .build();


        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> authService.registrarUsuario(nuevoUsuario)
        );

        assertTrue(exception.getMessage().contains("teléfono") ||
                exception.getMessage().contains("formato"));
    }

    @Test
    public void testRegistrarUsuarioSinTelefonoNoLanzaExcepcion() {
        Usuario nuevoUsuario = Usuario.builder()
                .email("test8@mail.com")
                .password("password123")
                .nombre("Test")
                .apellido("User")

                .telefono(null)
                .role(Role.USER)
                .build();


        assertThrows(
                DataIntegrityViolationException.class,
                () -> authService.registrarUsuario(nuevoUsuario)
        );

    }

    @Test
    public void testRegistrarUsuarioConTelefonoVacioNoLanzaExcepcion() {
        Usuario nuevoUsuario = Usuario.builder()
                .email("test9@mail.com")
                .password("password123")
                .nombre("Test")
                .apellido("User")

                .telefono("   ")
                .role(Role.USER)
                .build();


        Usuario usuarioGuardado = authService.registrarUsuario(nuevoUsuario);

        assertNotNull(usuarioGuardado);
        assertEquals("   ", usuarioGuardado.getTelefono());
    }

    @AfterEach
    public void tearDown() {
        resetService.resetAll();
    }
}