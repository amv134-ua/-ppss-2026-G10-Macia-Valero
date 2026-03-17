package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FicheroTextoTest {

    IMocksControl ctrl;
    FileReader mockReader;
    FicheroTexto sut;

    @BeforeEach
    void setUp() {
        ctrl = EasyMock.createStrictControl();

        // Mock de la dependencia
        mockReader = ctrl.mock(FileReader.class);

        // Mock Parcial de la SUT anulando la factoría local
        sut = EasyMock.partialMockBuilder(FicheroTexto.class)
                .addMockedMethod("getFileReader")
                .mock(ctrl);
    }

    @Test
    void FicheroTextoC1() throws Exception {
        // Arrange
        String archivo = "src/test/resources/ficheroC1.txt";
        String mensajeEsperado = archivo + " (Error al leer el archivo)";

        EasyMock.expect(sut.getFileReader(archivo)).andReturn(mockReader);
        EasyMock.expect(mockReader.read()).andReturn((int) 'a');
        EasyMock.expect(mockReader.read()).andReturn((int) 'b');
        EasyMock.expect(mockReader.read()).andThrow(new IOException());

        ctrl.replay();

        // Act & Assert
        FicheroException exception = assertThrows(FicheroException.class, () -> {
            sut.contarCaracteres(archivo);
        });
        assertEquals(mensajeEsperado, exception.getMessage());

        ctrl.verify();
    }

    @Test
    void FicheroTextoC2() throws Exception {
        // Arrange
        String archivo = "src/test/resources/ficheroC2.txt";
        String mensajeEsperado = archivo + " (Error al cerrar el archivo)";

        // fallo al close
        EasyMock.expect(sut.getFileReader(archivo)).andReturn(mockReader);
        EasyMock.expect(mockReader.read()).andReturn((int) 'a');
        EasyMock.expect(mockReader.read()).andReturn((int) 'b');
        EasyMock.expect(mockReader.read()).andReturn((int) 'c');
        EasyMock.expect(mockReader.read()).andReturn(-1);

        mockReader.close();
        EasyMock.expectLastCall().andThrow(new IOException());

        ctrl.replay();

        // Act & Assert
        FicheroException exception = assertThrows(FicheroException.class, () -> {
            sut.contarCaracteres(archivo);
        });
        assertEquals(mensajeEsperado, exception.getMessage());

        ctrl.verify();
    }
}