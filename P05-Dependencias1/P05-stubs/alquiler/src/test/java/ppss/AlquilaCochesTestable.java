package ppss;

public class AlquilaCochesTestable extends AlquilaCoches {
    private IService servicioStub;

    public void setServicioStub(IService servicioStub) {
        this.servicioStub = servicioStub;
    }

    public void setCalendarioStub(Calendario calendarioStub) {
        this.calendario = calendarioStub; // Accedemos al atributo protected original
    }

    @Override
    protected IService getServicio() {
        return this.servicioStub; // Damos el cambiazo al servicio
    }
}