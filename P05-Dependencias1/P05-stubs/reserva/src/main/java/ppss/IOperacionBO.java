package ppss;
import ppss.excepciones.*;

public interface IOperacionBO {
    void operacionReserva(String socio, String isbn) throws IsbnInvalidoException, JDBCException, SocioInvalidoException;
}