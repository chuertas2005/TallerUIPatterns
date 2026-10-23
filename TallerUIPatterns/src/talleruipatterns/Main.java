
package talleruipatterns;

import talleruipatterns.navigation.BreadcrumbNavigator;
import talleruipatterns.content.TarjetaElemento;
import talleruipatterns.input.ValidadorForgiving;
import talleruipatterns.state.GestorSesionUI;

public class Main {

    public static void main(String[] args) {

        // casos de prueba
        System.out.println(
            "\n--- 1. PATRON DE NAVEGACION (BREADCRUMBS + TABS) ---"
        );

        BreadcrumbNavigator navegador = new BreadcrumbNavigator();

        navegador.entrarEn("Reservas");
        navegador.entrarEn("Laboratorio de Redes");

        System.out.println("Ruta actual: " + navegador.obtenerRuta());

        navegador.cambiarPestana("Equipos");

        System.out.println(
            "Despues de cambiar de pestaña: "
            + navegador.obtenerRuta()
        );

        System.out.println(
            "\n--- 2. PATRON DE CONTENIDO (PROGRESSIVE DISCLOSURE) ---"
        );

        TarjetaElemento servidor = new TarjetaElemento(
            "Servidor Principal",
            "Activo",
            "111.111.1.11.11",
            8080,
            "4GB/16GB"
        );

        servidor.mostrarResumen();
        servidor.mostrarDetalles();

        System.out.println(
            "[Usuario solicita ver detalles avanzados...]"
        );

        servidor.desplegarDetalles();
        servidor.ocultarDetalles();

        System.out.println(
            "\n--- 3. PATRON DE ENTRADA (FORGIVING FORMAT) ---"
        );

        String telefono = "+57 (310) 999-8877";

        System.out.println("Entrada del usuario: \"" + telefono + "\"");

        System.out.println(
            "Telefono normalizado: \""
            + ValidadorForgiving.normalizarTelefono(telefono) + "\""
        );

        String fecha = "23/10/2026";

        System.out.println(
            "Fecha normalizada: "
            + ValidadorForgiving.normalizarFecha(fecha)
        );

        System.out.println(
            "\n--- 4. PATRON DE FEEDBACK (COMPLETENESS METER) ---"
        );

        GestorSesionUI gestor = new GestorSesionUI();

        gestor.actualizarCampo("Telefono", "+57 (310) 999-8877");
        gestor.actualizarCampo("Nombre", "Estudiante");
        gestor.actualizarCampo("Correo", "estudiante@udistrital.edu.co");

        System.out.println(
            "\n--- 5. LAZY REGISTRATION + TOAST ---"
        );

        System.out.println(
            "Usuario intenta guardar sin iniciar sesion..."
        );

        gestor.guardarReserva();

        System.out.println(
            "Usuario completa el registro rapido..."
        );

        gestor.iniciarSesion();

        System.out.println(
            "Usuario intenta guardar nuevamente..."
        );

        gestor.guardarReserva();

    }
}
