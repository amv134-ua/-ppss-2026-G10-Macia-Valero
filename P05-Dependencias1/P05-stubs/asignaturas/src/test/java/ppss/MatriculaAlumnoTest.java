package ppss;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatriculaAlumnoTest {

    @Test
    void MatriculaC1() {
        // Arrange
        String dni = "00000000T";
        String[] asignaturas = {"MD", "ZZ", "FBD", "YYY", "P1"};

        ArrayList<String> validasEsperadas = new ArrayList<>(Arrays.asList("MD", "FBD"));
        ArrayList<String> erroresEsperados = new ArrayList<>(Arrays.asList(
                "Asignatura ZZ no existe",
                "Asignatura YYY no existe",
                "Asignatura P1 ya cursada"));

        OperacionStub stub = new OperacionStub();

        MatriculaAlumnoTestable sut = new MatriculaAlumnoTestable();
        sut.setOperacionStub(stub);

        // Act
        JustificanteMatricula resultadoReal = sut.validaAsignaturas(dni, asignaturas);

        // Assert
        assertEquals(dni, resultadoReal.getDni());
        assertEquals(validasEsperadas, resultadoReal.getAsignaturas());
        assertEquals(erroresEsperados, resultadoReal.getErrores());
    }

    @Test
    void MatriculaC2() {
        //Arrange
        String dni = "00000000T";
        String[] asignaturas = {"PPSS", "ADA", "P3"};

        ArrayList<String> validasEsperadas = new ArrayList<>(Arrays.asList("PPSS", "ADA", "P3"));
        ArrayList<String> erroresEsperados = new ArrayList<>(Arrays.asList());

        OperacionStub stub = new OperacionStub();

        MatriculaAlumnoTestable sut = new MatriculaAlumnoTestable();
        sut.setOperacionStub(stub);

        // Act
        JustificanteMatricula resultadoReal = sut.validaAsignaturas(dni, asignaturas);

        // Assert
        assertEquals(dni, resultadoReal.getDni());
        assertEquals(validasEsperadas, resultadoReal.getAsignaturas());
        assertEquals(erroresEsperados, resultadoReal.getErrores());

    }

    }