package ppss;

import ppss.excepciones.*;
import java.util.ArrayList;

public class OperacionStub implements IOperacionBO {

    // ¡La magia de las listas dinámicas! Creamos una lista negra para cada fallo posible.
    public ArrayList<String> isbnsInvalidos = new ArrayList<>();
    public ArrayList<String> sociosInvalidos = new ArrayList<>();
    public ArrayList<String> isbnsConFalloJDBC = new ArrayList<>();

    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        // 1. Si el socio está en la lista de morosos/inválidos -> ¡Bum!
        if (sociosInvalidos.contains(socio)) {
            throw new SocioInvalidoException();
        }

        // 2. Si el ISBN es inventado -> ¡Bum!
        if (isbnsInvalidos.contains(isbn)) {
            throw new IsbnInvalidoException();
        }

        // 3. Si el servidor de BD falla justo con este ISBN -> ¡Bum!
        if (isbnsConFalloJDBC.contains(isbn)) {
            throw new JDBCException();
        }

        // Si el socio y el ISBN no están en ninguna lista, la reserva se hace con éxito.
    }
}