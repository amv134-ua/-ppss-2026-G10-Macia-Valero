package ppss;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class AlquilaCochesTest {

    @Test
    void AlquilaC1() throws MensajeException {
        // Arrange
        LocalDate inicio = LocalDate.of(2024, 5, 18);
        int ndias = 10;
        float resultadoEsperado = 75.0f;

        ServicioStub servicioStub = new ServicioStub();
        CalendarioStub calendarioStub = new CalendarioStub(); // Sin festivos ni errores

        AlquilaCochesTestable sut = new AlquilaCochesTestable();
        sut.setServicioStub(servicioStub);
        sut.setCalendarioStub(calendarioStub);

        // Act
        Ticket ticket = sut.calculaPrecio(TipoCoche.TURISMO, inicio, ndias);

        // Assert
        assertEquals(resultadoEsperado, ticket.getPrecio_final(), 0.001f);
    }

    @Test
    void AlquilaC2() throws MensajeException {
        // Arrange
        LocalDate inicio = LocalDate.of(2024, 6, 19);
        int ndias = 7;
        float resultadoEsperado = 62.5f;

        ServicioStub servicioStub = new ServicioStub();
        CalendarioStub calendarioStub = new CalendarioStub();
        calendarioStub.addFestivo(LocalDate.of(2024, 6, 20));
        calendarioStub.addFestivo(LocalDate.of(2024, 6, 24));

        AlquilaCochesTestable sut = new AlquilaCochesTestable();
        sut.setServicioStub(servicioStub);
        sut.setCalendarioStub(calendarioStub);

        // Act
        Ticket ticket = sut.calculaPrecio(TipoCoche.CARAVANA, inicio, ndias);

        // Assert
        assertEquals(resultadoEsperado, ticket.getPrecio_final(), 0.001f);
    }

    @Test
    void AlquilaC3() {
        // Arrange
        LocalDate inicio = LocalDate.of(2024, 4, 17);
        int ndias = 8;
        String mensajeEsperado = "Error en dia: 2024-04-18; Error en dia: 2024-04-21; Error en dia: 2024-04-22; ";

        ServicioStub servicioStub = new ServicioStub();
        CalendarioStub calendarioStub = new CalendarioStub();
        calendarioStub.addError(LocalDate.of(2024, 4, 18));
        calendarioStub.addError(LocalDate.of(2024, 4, 21));
        calendarioStub.addError(LocalDate.of(2024, 4, 22));

        AlquilaCochesTestable sut = new AlquilaCochesTestable();
        sut.setServicioStub(servicioStub);
        sut.setCalendarioStub(calendarioStub);

        // Act y Assert
        MensajeException exception = assertThrows(MensajeException.class, () -> {
            sut.calculaPrecio(TipoCoche.TURISMO, inicio, ndias);
        });
        assertEquals(mensajeEsperado, exception.getMessage());
    }
}