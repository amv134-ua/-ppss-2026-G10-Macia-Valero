package llamadas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GestorLlamadasTest {

    @Test
    void GestorC1() {
        // Arrange
        int minutos = 10;
        int hora = 15;
        double resultadoEsperado = 147.0;

        // 1. Preparamos el Stub del calendario con la hora de prueba
        CalendarioStub calendarioStub = new CalendarioStub();
        calendarioStub.setHoraPredef(hora);

        // 2. Preparamos nuestra SUT Testable y le inyectamos el Stub
        GestorLlamadasTestable sut = new GestorLlamadasTestable();
        sut.setCalendarioStub(calendarioStub);

        // Act
        double resultadoReal = sut.calculaConsumo(minutos);

        // Assert
        assertEquals(resultadoEsperado, resultadoReal, 0.001); // Se añade delta para floats/doubles
    }













    @Test
    void GestorC2() {
        // Arrange
        int minutos = 10;
        int hora = 23;
        double resultadoEsperado = 65.0;

        CalendarioStub calendarioStub = new CalendarioStub();
        calendarioStub.setHoraPredef(hora);

        GestorLlamadasTestable sut = new GestorLlamadasTestable();
        sut.setCalendarioStub(calendarioStub);

        // Act
        double resultadoReal = sut.calculaConsumo(minutos);

        // Assert
        assertEquals(resultadoEsperado, resultadoReal, 0.001);
    }
}