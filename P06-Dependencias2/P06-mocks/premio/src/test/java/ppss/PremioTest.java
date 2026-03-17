package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PremioTest {

    // 1. Declaramos las variables
    IMocksControl ctrl;
    ClienteWebService clienteMock;
    Premio sut;

    @BeforeEach
    void setUp() {
        // 2. Inicializamos todo antes de cada test
        ctrl = EasyMock.createStrictControl();
        clienteMock = ctrl.mock(ClienteWebService.class);
        sut = EasyMock.partialMockBuilder(Premio.class)
                .addMockedMethod("generaNumero")
                .mock(ctrl);
        sut.cliente = clienteMock;
    }

    @Test
    void PremioC1() throws ClienteWebServiceException {
        // Arrange
        String resultadoEsperado = "Premiado con entrada final Champions";

        EasyMock.expect(sut.generaNumero()).andReturn(0.07f);
        EasyMock.expect(clienteMock.obtenerPremio()).andReturn("entrada final Champions");

        ctrl.replay();

        // Act
        String resultadoReal = sut.compruebaPremio();

        // Assert
        assertEquals(resultadoEsperado, resultadoReal);
        ctrl.verify();
    }
    @Test
    void PremioC2() throws ClienteWebServiceException {
        // 1. Arrange
        String resultadoEsperado = "No se ha podido obtener el premio";

        EasyMock.expect(sut.generaNumero()).andReturn(0.05f);
        EasyMock.expect(clienteMock.obtenerPremio()).andThrow(new ClienteWebServiceException("Error servidor"));

        ctrl.replay();

        // 2. Act
        String resultadoReal = sut.compruebaPremio();

        // 3. Assert
        assertEquals(resultadoEsperado, resultadoReal);
        ctrl.verify();
    }

    @Test
    void PremioC3() {
        // 1. Arrange
        String resultadoEsperado = "Sin premio";

        EasyMock.expect(sut.generaNumero()).andReturn(0.48f);

        ctrl.replay();

        // 2. Act
        String resultadoReal = sut.compruebaPremio();

        // 3. Assert
        assertEquals(resultadoEsperado, resultadoReal);
        ctrl.verify();
    }
}