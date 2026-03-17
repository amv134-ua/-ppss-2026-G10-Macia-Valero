package ppss;

public class ReservaTestable extends Reserva {
    // Sobreescribimos dependencia int
    @Override
    public boolean compruebaPermisos(String login, String password, Usuario tipoUsu) {
        return "ppss".equals(login) && "ppss".equals(password) && Usuario.BIBLIOTECARIO == tipoUsu;
    }
}