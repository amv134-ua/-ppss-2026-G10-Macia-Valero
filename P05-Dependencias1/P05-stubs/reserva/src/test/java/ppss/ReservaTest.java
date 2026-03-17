package ppss;

import org.junit.jupiter.api.Test;
import ppss.excepciones.ReservaException;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {

    @Test
    void ReservaC1() {
        ReservaTestable sut = new ReservaTestable();
        String[] isbns = {"11111"};

        ReservaException exception = assertThrows(ReservaException.class, () -> {
            sut.realizaReserva("xxxx", "xxxx", "Luis", isbns);
        });
        assertEquals("ERROR de permisos; ", exception.getMessage());
    }

    @Test
    void ReservaC2() {
        ReservaTestable sut = new ReservaTestable();
        OperacionStub stub = new OperacionStub();
        Factoria.setServicio(stub); // Inyect Stub usando Factoría

        String[] isbns = {"11111", "22222"};

        assertDoesNotThrow(() -> {
            sut.realizaReserva("ppss", "ppss", "Luis", isbns);
        });
    }

    @Test
    void ReservaC3() {
        ReservaTestable sut = new ReservaTestable();
        OperacionStub stub = new OperacionStub();
        Factoria.setServicio(stub);

        String[] isbns = {"11111", "33333", "44444"};

        ReservaException exception = assertThrows(ReservaException.class, () -> {
            sut.realizaReserva("ppss", "ppss", "Luis", isbns);
        });
        assertEquals("ISBN invalido:33333; ISBN invalido:44444; ", exception.getMessage());
    }

    @Test
    void ReservaC4() {
        ReservaTestable sut = new ReservaTestable();
        OperacionStub stub = new OperacionStub();
        Factoria.setServicio(stub);

        String[] isbns = {"11111"};

        ReservaException exception = assertThrows(ReservaException.class, () -> {
            sut.realizaReserva("ppss", "ppss", "Pepe", isbns);
        });
        assertEquals("SOCIO invalido; ", exception.getMessage());
    }

    @Test
    void ReservaC5() {
        ReservaTestable sut = new ReservaTestable();
        OperacionStub stub = new OperacionStub();
        stub.setForzarFalloConexion(true); // Forzamos el fallo para el test
        Factoria.setServicio(stub);

        String[] isbns = {"11111", "22222"};

        ReservaException exception = assertThrows(ReservaException.class, () -> {
            sut.realizaReserva("ppss", "ppss", "Luis", isbns);
        });
        assertEquals("CONEXION invalida; ", exception.getMessage());
    }
}