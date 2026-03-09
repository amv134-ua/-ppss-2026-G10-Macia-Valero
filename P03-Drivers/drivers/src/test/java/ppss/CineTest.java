package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

@DisplayName("Tests asociados a la clase Cine")
public class CineTest {

    private Cine cine;

    @BeforeEach
    void setUp() {
        cine = new Cine(); // Instanciamos la SUT antes de cada test
    }

    @Test
    @Tag("no_parametrizado")
    void C1_reservaButacas_should_return_Exception_when_fila_empty_and_want_3() {
        // Arrange
        boolean[] asientos = new boolean[0];
        int solicitados = 3;
        String mensajeEsperado = "No se puede procesar la solicitud";

        // Act & Assert para excepciones
        ButacasException exception = assertThrows(ButacasException.class, () -> {
            cine.reservaButacas(asientos, solicitados);
        });

        // Comprobamos el mensaje
        assertEquals(mensajeEsperado, exception.getMessage());
    }

    @Test
    @Tag("no_parametrizado")
    void C2_reservaButacas_should_return_false_when_fila_empty_and_want_zero() {
        // Arrange
        boolean[] asientos = new boolean[0];
        int solicitados = 0;
        boolean reservaEsperada = false;
        boolean[] asientosEsperados = new boolean[0];

        // Act (combinado con Assert según la norma)
        boolean reservaReal = assertDoesNotThrow(() -> cine.reservaButacas(asientos, solicitados));

        // Assert
        assertAll("Comprobación de reserva y estado de asientos",
                () -> assertEquals(reservaEsperada, reservaReal),
                () -> assertArrayEquals(asientosEsperados, asientos)
        );
    }

    @Test
    @Tag("no_parametrizado")
    void C3_reservaButacas_should_return_true_when_fila_has_3_seats_free_and_want_2() {
        // Arrange
        boolean[] asientos = {false, false, false, true, true};
        int solicitados = 2;
        boolean reservaEsperada = true;
        boolean[] asientosEsperados = {true, true, false, true, true};

        // Act
        boolean reservaReal = assertDoesNotThrow(() -> cine.reservaButacas(asientos, solicitados));

        // Assert
        assertAll("Comprobación de reserva y estado de asientos",
                () -> assertEquals(reservaEsperada, reservaReal),
                () -> assertArrayEquals(asientosEsperados, asientos)
        );
    }

    @Test
    @Tag("no_parametrizado")
    void C4_reservaButacas_should_return_false_when_no_free_seats_and_want_1() {
        // Arrange
        boolean[] asientos = {true, true, true};
        int solicitados = 1;
        boolean reservaEsperada = false;
        boolean[] asientosEsperados = {true, true, true};

        // Act
        boolean reservaReal = assertDoesNotThrow(() -> cine.reservaButacas(asientos, solicitados));

        // Assert
        assertAll("Comprobación de reserva y estado de asientos",
                () -> assertEquals(reservaEsperada, reservaReal),
                () -> assertArrayEquals(asientosEsperados, asientos)
        );
    }
    // ==========================================
    // EJERCICIO 3: TESTS PARAMETRIZADOS
    // ==========================================

    @DisplayName("reservaButacas_")
    @Tag("parametrizado")
    @ParameterizedTest(name = "{0}[{index}] {5}")
    @MethodSource("casosDePruebaTablaA")
    void C5_reservaButacas(String nombreTest, boolean[] asientos, int solicitados, boolean reservaEsperada, boolean[] asientosEsperados, String mensaje) {

        // Act (usamos assertDoesNotThrow por la excepción checked)
        boolean reservaReal = assertDoesNotThrow(() -> cine.reservaButacas(asientos, solicitados));

        // Assert
        assertAll(mensaje, // Pasamos el mensaje del parámetro
                () -> assertEquals(reservaEsperada, reservaReal, "El valor de retorno (boolean) no es el esperado"),
                () -> assertArrayEquals(asientosEsperados, asientos, "El estado de los asientos no es el esperado")
        );
    }

    // Datos para C5 (Tabla A: Casos C2, C3 y C4)
    private static Stream<Arguments> casosDePruebaTablaA() {
        return Stream.of(
                Arguments.of("reservaButacas_", new boolean[]{}, 0, false, new boolean[]{}, "should be false when we want 0 and fila has no seats"),
                Arguments.of("reservaButacas_", new boolean[]{false, false, false, true, true}, 2, true, new boolean[]{true, true, false, true, true}, "should be true when we want 2 and there are 2 free seats"),
                Arguments.of("reservaButacas_", new boolean[]{true, true, true}, 1, false, new boolean[]{true, true, true}, "should be false when we want 1 and all seats are already reserved")
        );
    }

    @DisplayName("reservaButacas_")
    @Tag("parametrizado")
    @Tag("tablaB")
    @ParameterizedTest(name = "{0}[{index}] {5}")
    @MethodSource("casosDePruebaTablaB")
    void C6_reservaButacas(String nombreTest, boolean[] asientos, int solicitados, boolean reservaEsperada, boolean[] asientosEsperados, String mensaje) {

        // Act
        boolean reservaReal = assertDoesNotThrow(() -> cine.reservaButacas(asientos, solicitados));

        // Assert
        assertAll(mensaje,
                () -> assertEquals(reservaEsperada, reservaReal),
                () -> assertArrayEquals(asientosEsperados, asientos)
        );
    }

    // Datos para C6
    private static Stream<Arguments> casosDePruebaTablaB() {
        return Stream.of(
                Arguments.of("reservaButacas_", new boolean[]{false, false, false}, 1, true, new boolean[]{true, false, false}, "should return true and reserve first seat"),
                Arguments.of("reservaButacas_", new boolean[]{true}, 1, false, new boolean[]{true}, "should return false when only one seat and is occupied")
        );
    }
}