package ppss;

public class OperacionStub extends Operacion {
    @Override
    public void compruebaMatricula(String dni, String asignatura) throws AsignaturaIncorrectaException, AsignaturaCursadaException {
        if (asignatura.equals("ZZ") || asignatura.equals("YYY")) {
            throw new AsignaturaIncorrectaException();
        }
        if (asignatura.equals("P1") || asignatura.equals("FC") || asignatura.equals("FFI")) {
            throw new AsignaturaCursadaException();
        }
        // Si no es ninguna de esas, la matrícula es correcta
    }
}