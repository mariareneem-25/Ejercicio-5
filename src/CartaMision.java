package modelo;

public class CartaMision extends CartaEvento {
    private int energiaGanada;

    public CartaMision(int id, String nombre, int costoEnergia, String descripcion, int energiaGanada) {
        super(id, nombre, costoEnergia, descripcion);
        this.energiaGanada = energiaGanada;
    }

    public int getEnergiaGanada() {
        return energiaGanada;
    }

    public void setEnergiaGanada(int energiaGanada) {
        this.energiaGanada = energiaGanada;
    }

    @Override
    public String getTipo() {
        return "Evento (Misión)";
    }

    @Override
    public String jugarCarta(Jugador jugador, Director director) {
        jugador.perderEnergia(getCostoEnergia());
        jugador.ganarEnergia(energiaGanada);
        return "Cumpliste la misión " + getNombre() + ". Perdiste " + getCostoEnergia() + " de energía y ganaste "
                + energiaGanada + " de energía.";
    }

    @Override
    public String toString() {
        return super.toString() + " | Energía que otorga: " + energiaGanada;
    }
}
