package edu.ar.tempus.service.usuario;

import edu.ar.tempus.model.usuario.Alumno;
import edu.ar.tempus.service.AlumnoService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AlumnoServiceTest {
    private AlumnoService alumnoService;

    private Alumno alumno;


}
