package ppss;

public class OperacionStub extends Operacion {
    public arrayList<string> excepAsigNoEx = new arrayList<>();
    public arrayList<string> excepAsigCursada = new arrayList<>();

    @Override
    public void compruebaMatricula(String dni, String asignatura) throws AsignaturaIncorrectaException, AsignaturaCursadaException {
        if (excepAsigNoEx.contains(asignatura)) {
            throw new AsignaturaIncorrectaException();
        }
        if (excepAsigCursada.contains(asignatura)) {
            throw new AsignaturaCursadaException();
        }
        // Si no es ninguna de esas, la matrícula es correcta
    }
}