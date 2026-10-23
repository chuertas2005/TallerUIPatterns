
package talleruipatterns.content;

public class TarjetaElemento {

    private final String nombre;
    private final String estado;
    private final String ip;
    private final int puerto;
    private final String ram;

    private boolean verAvanzado;

    public TarjetaElemento(
            String nombre,
            String estado,
            String ip,
            int puerto,
            String ram) {

        this.nombre = nombre;
        this.estado = estado;
        this.ip = ip;
        this.puerto = puerto;
        this.ram = ram;
        this.verAvanzado = false;
    }

    public void mostrarResumen() {
        System.out.println(
            "[Vista Resumen] " + nombre + " Estado: " + estado
        );
    }

    public void desplegarDetalles() {
        verAvanzado = true;
        mostrarDetalles();
    }

    public void ocultarDetalles() {
        verAvanzado = false;
        System.out.println(
            "[Acordeon] Los detalles tecnicos estan ocultos."
        );
    }

    public void mostrarDetalles() {
        if (!verAvanzado) {
            System.out.println(
                "[Acordeon] Los detalles tecnicos estan ocultos."
            );
            return;
        }

        System.out.println(
            "[Acordeon Desplegado] IP: " + ip
            + " | Puerto: " + puerto
            + " | RAM: " + ram
        );
    }

    public boolean isVerAvanzado() {
        return verAvanzado;
    }
}
