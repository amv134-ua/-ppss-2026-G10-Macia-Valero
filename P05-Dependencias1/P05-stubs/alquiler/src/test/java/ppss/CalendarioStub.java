package ppss;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarioStub extends Calendario {
    private List<LocalDate> festivos = new ArrayList<>();
    private List<LocalDate> errores = new ArrayList<>();

    public void addFestivo(LocalDate fecha) { festivos.add(fecha); }
    public void addError(LocalDate fecha) { errores.add(fecha); }

    @Override
    public boolean es_festivo(LocalDate fecha) throws CalendarioException {
        if (errores.contains(fecha)) {
            throw new CalendarioException();
        }
        return festivos.contains(fecha);
    }
}