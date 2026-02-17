package com.ecovida.ecommerce_backend.services;

import com.ecovida.ecommerce_backend.models.Role;
import com.ecovida.ecommerce_backend.models.Usuario;
import com.ecovida.ecommerce_backend.services.interfaces.AuthService;
import com.ecovida.ecommerce_backend.services.interfaces.ResetService;
import com.ecovida.ecommerce_backend.services.interfaces.UsuarioService;
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
                .provincia("Buenos Aires")
                .localidad("La Plata")
                .codigoPostal("1900")
                .domicilio("Calle 50 N° 123")
                .telefono("221-4567890")
                .role(Role.USER)
                .build();

        usuario2 = Usuario.builder()
                .email("maria.gonzalez@mail.com")
                .password("password456")
                .nombre("María")
                .apellido("González")
                .provincia("Córdoba")
                .localidad("Córdoba Capital")
                .codigoPostal("5000")
                .domicilio("Av. Colón 456")
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
                .provincia("Buenos Aires")
                .localidad("CABA")
                .codigoPostal("1000")
                .domicilio("Av. Corrientes 123")
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
                .provincia("Buenos Aires")
                .localidad("CABA")
                .codigoPostal("1000")
                .domicilio("Av. Corrientes 123")
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
                .provincia("Buenos Aires")
                .localidad("CABA")
                .codigoPostal("1000")
                .domicilio("Av. Corrientes 123")
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
                .provincia("Córdoba")
                .localidad("Córdoba")
                .codigoPostal("5000")
                .domicilio("Av. Colón 456")
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
                .provincia("Buenos Aires")
                .localidad("CABA")
                .codigoPostal("1000")
                .domicilio("Av. Corrientes 123")
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
                .provincia("Buenos Aires")
                .localidad("CABA")
                .codigoPostal("1000")
                .domicilio("Av. Corrientes 123")
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
                .provincia("Buenos Aires")
                .localidad("CABA")
                .codigoPostal("1000")
                .domicilio("Av. Corrientes 123")
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
                .provincia("Buenos Aires")
                .localidad("CABA")
                .codigoPostal("1000")
                .domicilio("Av. Corrientes 123")
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
                .provincia("Buenos Aires")
                .localidad("CABA")
                .codigoPostal("1000")
                .domicilio("Av. Corrientes 123")
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