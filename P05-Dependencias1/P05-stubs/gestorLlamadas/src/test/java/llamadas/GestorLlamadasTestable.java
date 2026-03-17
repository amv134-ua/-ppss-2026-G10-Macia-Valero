package llamadas;

public class GestorLlamadasTestable extends GestorLlamadas {
    private Calendario calendarioStub;

    public void setCalendarioStub(Calendario calendarioStub) {
        this.calendarioStub = calendarioStub;
    }

    @Override
    public Calendario getCalendario() {
        return this.calendarioStub;
    }
}