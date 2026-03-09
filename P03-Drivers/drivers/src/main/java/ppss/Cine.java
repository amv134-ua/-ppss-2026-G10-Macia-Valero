package ppss;

public class Cine {
    public boolean reservaButacas(boolean[] asientos, int solicitados) throws ButacasException {
        // Controlamos si la solicitud es imposible debido a un array vacío
        if (asientos.length == 0 && solicitados > 0) {
            throw new ButacasException("No se puede procesar la solicitud");
        }

        boolean reserva = false;
        int j = 0;
        int sitiosLibres = 0;
        int primerLibre = 0;

        // Buscamos asientos contiguos libres
        while ((j < asientos.length) && (sitiosLibres < solicitados)) {
            if (!asientos[j]) {
                sitiosLibres++;
            } else {
                sitiosLibres = 0;
            }
            j++;
        }

        // Si encontramos los asientos necesarios, los reservamos
        if (sitiosLibres == solicitados && solicitados > 0) {
            primerLibre = (j - solicitados);
            reserva = true;
            // Corregimos el índice del bucle: debe ser < (primerLibre + solicitados)
            for (int k = primerLibre; k < (primerLibre + solicitados); k++) {
                asientos[k] = true;
            }
        }

        return reserva;
    }
}