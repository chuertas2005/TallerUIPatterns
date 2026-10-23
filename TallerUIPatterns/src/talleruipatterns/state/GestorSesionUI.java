
package talleruipatterns.state;

import talleruipatterns.input.ValidadorForgiving;

import java.util.LinkedHashMap;
import java.util.Map;

public class GestorSesionUI {

    private final Map<String, String> formulario;
    private Map<String, String> borradorTemporal;

    private boolean usuarioActivo;
    private boolean borradorGuardado;
    private boolean reservaGuardada;

    public GestorSesionUI() {
        formulario = new LinkedHashMap<>();
        formulario.put("Nombre", "");
        formulario.put("Telefono", "");
        formulario.put("Correo", "");

        borradorTemporal = new LinkedHashMap<>();

        usuarioActivo = false;
        borradorGuardado = false;
        reservaGuardada = false;
    }

    public void actualizarCampo(String campo, String valor) {
        if (!formulario.containsKey(campo)) {
            System.out.println(
                "[Entrada] Campo desconocido: " + campo
            );
            return;
        }

        String valorNormalizado = valor == null ? "" : valor.trim();

        if ("Telefono".equals(campo)) {
            valorNormalizado =
                ValidadorForgiving.normalizarTelefono(valorNormalizado);
        }

        formulario.put(campo, valorNormalizado);

        borradorGuardado = false;
        reservaGuardada = false;

        mostrarProgreso();
    }

    public int calcularProgreso() {
        int completados = 0;

        for (String valor : formulario.values()) {
            if (valor != null && !valor.isBlank()) {
                completados++;
            }
        }

        return Math.round(
            completados * 100.0f / formulario.size()
        );
    }

    public void mostrarProgreso() {
        int completados = 0;
        String campoPendiente = null;

        for (Map.Entry<String, String> entrada :
                formulario.entrySet()) {

            if (entrada.getValue() != null
                    && !entrada.getValue().isBlank()) {
                completados++;
            } else if (campoPendiente == null) {
                campoPendiente = entrada.getKey();
            }
        }

        int porcentaje = calcularProgreso();

        System.out.println(
            "[Progreso: " + porcentaje + "%] Campos listos: "
            + completados + " de " + formulario.size()
        );

        if (campoPendiente != null) {
            System.out.println(
                "Falta completar: " + campoPendiente
            );
        } else {
            System.out.println(
                "[Formulario completo] Todos los campos estan listos."
            );
        }
    }

    public boolean guardarReserva() {
        if (reservaGuardada) {
            System.out.println(
                "[Estado] Esta reserva ya fue guardada."
            );
            return true;
        }

        if (calcularProgreso() < 100) {
            System.out.println(
                "[Estado] Complete los campos pendientes para guardar."
            );
            mostrarProgreso();
            return false;
        }

        if (!usuarioActivo) {

            borradorTemporal = new LinkedHashMap<>(formulario);
            borradorGuardado = true;

            System.out.println(
                "[Lazy Registration] Avance guardado en borrador temporal."
            );
            System.out.println(
                "Por favor, cree su cuenta o inicie sesion para finalizar."
            );

            return false;
        }

        reservaGuardada = true;
        borradorGuardado = false;

        mostrarToast("Reserva guardada exitosamente.");
        return true;
    }

    public void iniciarSesion() {
        usuarioActivo = true;

        System.out.println(
            "[Sesion] Sesion iniciada correctamente."
        );

        if (borradorGuardado) {

            formulario.clear();
            formulario.putAll(borradorTemporal);

            System.out.println(
                "[Borrador] Datos recuperados correctamente."
            );
        }
    }

    public void cerrarSesion() {
        usuarioActivo = false;
        System.out.println("[Sesion] Sesion cerrada.");
    }

    public void cancelarReserva() {
        formulario.replaceAll((campo, valor) -> "");
        borradorTemporal.clear();

        borradorGuardado = false;
        reservaGuardada = false;

        System.out.println(
            "[Reserva] Reserva cancelada. Puede crear otra cuando lo desee."
        );
    }

    private void mostrarToast(String mensaje) {
        System.out.println("[Toast de Estado] " + mensaje);
    }

    public boolean isUsuarioActivo() {
        return usuarioActivo;
    }

    public boolean isBorradorGuardado() {
        return borradorGuardado;
    }

    public boolean isReservaGuardada() {
        return reservaGuardada;
    }

    public Map<String, String> obtenerDatosFormulario() {
        return new LinkedHashMap<>(formulario);
    }

    public Map<String, String> obtenerBorradorTemporal() {
        return new LinkedHashMap<>(borradorTemporal);
    }
}
