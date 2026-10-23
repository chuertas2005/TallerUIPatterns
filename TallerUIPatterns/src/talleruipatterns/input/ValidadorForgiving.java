
package talleruipatterns.input;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

public class ValidadorForgiving {

    private static final Pattern SOLO_DIGITOS =
        Pattern.compile("\\D");

    private static final List<DateTimeFormatter> FORMATOS_FECHA =
        List.of(
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ofPattern("d/M/uuuu"),
            DateTimeFormatter.ofPattern("d-M-uuuu"),
            DateTimeFormatter.ofPattern("d.M.uuuu")
        );

    private ValidadorForgiving() {
        // Clase de utilidades: no necesita instancias.
    }

    public static String normalizarTelefono(String entrada) {
        if (entrada == null || entrada.isBlank()) {
            return "";
        }

        // Elimina caracteres sobrantes y convierte el prefijo +57 a formato local
        String digitos = SOLO_DIGITOS
            .matcher(entrada)
            .replaceAll("");

        if (digitos.startsWith("57") && digitos.length() == 12) {
            digitos = digitos.substring(2);
        }

        return digitos;
    }

    public static String normalizarFecha(String entrada) {
        if (entrada == null || entrada.isBlank()) {
            return "";
        }

        String valor = entrada.trim();

        for (DateTimeFormatter formato : FORMATOS_FECHA) {
            try {
                LocalDate fecha = LocalDate.parse(valor, formato);
                return fecha.format(DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException excepcion) {
            }
        }

        return valor;
    }
}
