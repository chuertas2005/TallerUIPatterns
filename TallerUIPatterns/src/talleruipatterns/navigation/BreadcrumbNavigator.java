
package talleruipatterns.navigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class BreadcrumbNavigator {

    private final List<String> ruta;
    private String pestanaActual;

    public BreadcrumbNavigator() {
        ruta = new ArrayList<>();
        ruta.add("Inicio");
        pestanaActual = "Inicio";
    }

    public void entrarEn(String seccion) {
        Objects.requireNonNull(seccion, "La seccion no puede ser null");

        if (seccion.isBlank()) {
            throw new IllegalArgumentException(
                "La seccion no puede estar vacía"
            );
        }

        ruta.add(seccion);
        pestanaActual = seccion;
    }

    public void cambiarPestana(String nuevaPestana) {
        Objects.requireNonNull(
            nuevaPestana,
            "La pestaña no puede ser null"
        );

        if (nuevaPestana.isBlank()) {
            throw new IllegalArgumentException(
                "La pestaña no puede estar vacia"
            );
        }

        // Cambiar de pestaña sustituye el nivel actual.
        if (ruta.size() > 1) {
            ruta.set(ruta.size() - 1, nuevaPestana);
        } else {
            ruta.add(nuevaPestana);
        }

        pestanaActual = nuevaPestana;
    }

    public void volverA(String nivel) {
        int indice = ruta.lastIndexOf(nivel);

        if (indice >= 0) {
            while (ruta.size() > indice + 1) {
                ruta.remove(ruta.size() - 1);
            }

            pestanaActual = ruta.get(ruta.size() - 1);
        }
    }

    public String obtenerRuta() {
        StringJoiner separador = new StringJoiner(" > ");

        for (String nivel : ruta) {
            separador.add(nivel);
        }

        return separador.toString();
    }

    public String getPestanaActual() {
        return pestanaActual;
    }
}
