package ppss;
import ppss.excepciones.*;

public class OperacionStub implements IOperacionBO {
    private boolean forzarFalloConexion = false;

    public void setForzarFalloConexion(boolean forzar) {
        this.forzarFalloConexion = forzar;
    }

    @Override
    public void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException {
        if ("Pepe".equals(socio)) {
            throw new SocioInvalidoException();
        }
        if (!"11111".equals(isbn) && !"22222".equals(isbn)) {
            throw new IsbnInvalidoException();
        }
        // simulamos que falla la base de datos al meter el segundo libro (C5)
        if (forzarFalloConexion && "22222".equals(isbn)) {
            throw new JDBCException();
        }
    }
}