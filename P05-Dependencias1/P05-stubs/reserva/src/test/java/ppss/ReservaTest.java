package ppss;

import org.junit.jupiter.api.Test;
import ppss.excepciones.ReservaException;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {

    @Test
    void C3_realizaReserva_should_throw_ReservaException_when_isbns_invalidos() {
        // --- 1. ARRANGE ---
        ReservaTestable sut = new ReservaTestable();
        OperacionStub stub = new OperacionStub();
        Factoria.setServicio(stub); // Inyectamos el doble

        String[] isbns = {"11111", "33333", "44444"};

        // ¡CONFIGURAMOS EL STUB AL VUELO!
        // Le decimos que el 33333 y el 44444 van a dar error de ISBN
        stub.isbnsInvalidos.add("33333");
        stub.isbnsInvalidos.add("44444");

        // --- 2. ACT & 3. ASSERT ---
        ReservaException exception = assertThrows(ReservaException.class, () -> {
            sut.realizaReserva("ppss", "ppss", "Luis", isbns);
        });
        assertEquals("ISBN invalido:33333; ISBN invalido:44444; ", exception.getMessage());
    }

    @Test
    void C4_realizaReserva_should_throw_ReservaException_when_socio_invalido() {
        // --- 1. ARRANGE ---
        ReservaTestable sut = new ReservaTestable();
        OperacionStub stub = new OperacionStub();
        Factoria.setServicio(stub);

        String[] isbns = {"11111"};

        // ¡CONFIGURAMOS EL STUB! Pepe está vetado de la biblioteca.
        stub.sociosInvalidos.add("Pepe");

        // --- 2. ACT & 3. ASSERT ---
        ReservaException exception = assertThrows(ReservaException.class, () -> {
            sut.realizaReserva("ppss", "ppss", "Pepe", isbns);
        });
        assertEquals("SOCIO invalido; ", exception.getMessage());
    }

    @Test
    void C5_realizaReserva_should_throw_ReservaException_when_jdbc_fails() {
        // --- 1. ARRANGE ---
        ReservaTestable sut = new ReservaTestable();
        OperacionStub stub = new OperacionStub();
        Factoria.setServicio(stub);

        String[] isbns = {"11111", "22222"};

        // ¡CONFIGURAMOS EL STUB! Simulamos un corte de internet justo en el 2º libro.
        stub.isbnsConFalloJDBC.add("22222");

        // --- 2. ACT & 3. ASSERT ---
        ReservaException exception = assertThrows(ReservaException.class, () -> {
            sut.realizaReserva("ppss", "ppss", "Luis", isbns);
        });
        assertEquals("CONEXION invalida; ", exception.getMessage());
    }
}


oo

si en stub
package ppss;

import ppss.excepciones.*;
        import java.util.ArrayList;
import java.util.List;

public class OperacionStub implements IOperacionBO {

    public List<String> isbnsInvalidos = new ArrayList<>();
    public List<String> sociosInvalidos = new ArrayList<>();
    public List<String> isbnsConFalloJDBC = new ArrayList<>();

    // 1. Constructor vacío (Para el test C2, donde todo es correcto y las listas están vacías)
    public OperacionStub() {}

    // 2. Constructor con las listas (¡Tu pedazo de idea!)
    public OperacionStub(List<String> isbnsInvalidos, List<String> sociosInvalidos, List<String> isbnsConFalloJDBC) {
        this.isbnsInvalidos = isbnsInvalidos;
        this.sociosInvalidos = sociosInvalidos;
        this.isbnsConFalloJDBC = isbnsConFalloJDBC;
    }

    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        if (sociosInvalidos.contains(socio)) throw new SocioInvalidoException();
        if (isbnsInvalidos.contains(isbn)) throw new IsbnInvalidoException();
        if (isbnsConFalloJDBC.contains(isbn)) throw new JDBCException();
    }
}
@Test
void C3_realizaReserva_should_throw_ReservaException_when_isbns_invalidos() {
    // --- 1. ARRANGE ---
    ReservaTestable sut = new ReservaTestable();

    // ¡Magia! Instanciamos el Stub pasándole las listas por constructor usando Arrays.asList()
    OperacionStub stub = new OperacionStub(
            Arrays.asList("33333", "44444"), // Lista de ISBNs malos
            new ArrayList<>(),               // Lista de socios malos (vacía)
            new ArrayList<>()                // Lista de fallos JDBC (vacía)
    );

    FactoriaBOs.setOperacionBO(stub); // Inyectamos
    String[] isbns = {"11111", "33333", "44444"};

    // --- 2. ACT & 3. ASSERT ---
    ReservaException exception = assertThrows(ReservaException.class, () -> {
        sut.realizaReserva("ppss", "ppss", "Luis", isbns);
    });
    assertEquals("ISBN invalido:33333; ISBN invalido:44444; ", exception.getMessage());
}
