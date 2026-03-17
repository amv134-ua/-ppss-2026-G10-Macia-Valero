package ppss;

public class MatriculaAlumnoTestable extends MatriculaAlumno {
    private Operacion operacionStub;

    public void setOperacionStub(Operacion operacionStub) {
        this.operacionStub = operacionStub;
    }

    @Override
    protected Operacion getOperacion() {
        return this.operacionStub;
    }
}