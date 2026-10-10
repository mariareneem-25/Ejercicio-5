package modelo;

public class CartaCatedratico extends Carta {
    private String departamento;
    private int llamadasAtencion;
    private int tiempoAtencion;

    public CartaCatedratico(int id, String nombre, int costoEnergia, String descripcion, String departamento,
            int llamadasAtencion, int tiempoAtencion) {
        super(id, nombre, costoEnergia, descripcion);
        this.departamento = departamento;
        this.llamadasAtencion = llamadasAtencion;
        this.tiempoAtencion = tiempoAtencion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getLlamadasAtencion() {
        return llamadasAtencion;
    }

    public void setLlamadasAtencion(int llamadasAtencion) {
        this.llamadasAtencion = llamadasAtencion;
    }

    public int getTiempoAtencion() {
        return tiempoAtencion;
    }

    public void setTiempoAtencion(int tiempoAtencion) {
        this.tiempoAtencion = tiempoAtencion;
    }

    @Override
    public String getTipo() {
        return "Catedrático";
    }

    @Override
    public String jugarCarta(Jugador jugador, Director director) {
        jugador.perderEnergia(getCostoEnergia());
        jugador.ganarEnergia(tiempoAtencion);
        String mensaje = "Fuiste a la atención de " + getNombre() + ". Perdiste " + getCostoEnergia()
                + " de energía y ganaste " + tiempoAtencion + " de energía.";
        mensaje += "\n" + director.perdonarLlamadas(llamadasAtencion);
        return mensaje;
    }

    @Override
    public String toString() {
        return super.toString() + " | Departamento: " + departamento + " | Llamadas de atención que perdona: "
                + llamadasAtencion + " | Tiempo de atención (energía): " + tiempoAtencion;
    }
}
