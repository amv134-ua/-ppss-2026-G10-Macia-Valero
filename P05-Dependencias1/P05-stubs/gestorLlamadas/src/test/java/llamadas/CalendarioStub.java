package llamadas;

public class CalendarioStub extends Calendario {
    private int horaPredef;

    public void setHoraPredef(int horaPredef) {
        this.horaPredef = horaPredef;
    }
    @Override
    public int getHoraActual() {
        return this.horaPredef;
    }
}