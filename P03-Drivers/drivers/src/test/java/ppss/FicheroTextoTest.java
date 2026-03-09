package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

public class FicheroTextoTest {

    private FicheroTexto ficheroTexto;

    @BeforeEach
    void setUp() {
        ficheroTexto = new FicheroTexto();
    }

    @Test
    @Tag("no_parametrizado")
    void C1_contarCaracteres_should_return_Exception_when_file_does_not_exist() {
        // Arrange
        String nombreFichero = "ficheroC1.txt"; // Asumimos que no existe
        String mensajeEsperado = "ficheroC1.txt (No existe el archivo o el directorio)";

        // Act & Assert
        FicheroException exception = assertThrows(FicheroException.class, () -> {
            ficheroTexto.contarCaracteres(nombreFichero);
        });

        // Comprobamos el mensaje de la excepción
        assertEquals(mensajeEsperado, exception.getMessage());
    }

    @Test
    @Tag("no_parametrizado")
    void C2_contarCaracteres_should_return_4_when_file_has_4_chars() {
        // Arrange
        String nombreFichero = "src/test/resources/ficheroCorrecto.txt";
        int resultadoEsperado = 4;

        // Act (combinado con assertDoesNotThrow como dicta la teoría)
        int resultadoReal = assertDoesNotThrow(() -> ficheroTexto.contarCaracteres(nombreFichero));

        // Assert
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    @Tag("excluido")
    void C3_contarCaracteres_should_return_Exception_when_file_cannot_be_read() {
        Assertions.fail("Excluido");
    }

    @Test
    @Tag("excluido")
    void C4_contarCaracteres_should_return_Exception_when_file_cannot_be_closed() {
        Assertions.fail("Excluido");
    }
}