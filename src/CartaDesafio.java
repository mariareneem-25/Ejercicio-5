package modelo;

public class CartaDesafio extends CartaEvento {
    private int creditosExtra;

    public CartaDesafio(int id, String nombre, int costoEnergia, String descripcion, int creditosExtra) {
        super(id, nombre, costoEnergia, descripcion);
        this.creditosExtra = creditosExtra;
    }

    public int getCreditosExtra() {
        return creditosExtra;
    }

    public void setCreditosExtra(int creditosExtra) {
        this.creditosExtra = creditosExtra;
    }

    @Override
    public String getTipo() {
        return "Evento (Desafío)";
    }

    @Override
    public String jugarCarta(Jugador jugador, Director director) {
        jugador.perderEnergia(getCostoEnergia());
        jugador.ganarCreditos(creditosExtra);
        String mensaje = "Sobreviviste a " + getNombre() + ". Perdiste " + getCostoEnergia()
                + " de energía y ganaste " + creditosExtra + " créditos.";
        mensaje += "\n" + director.llamarAtencion("el desafío " + getNombre() + " te dejó con tareas atrasadas");
        return mensaje;
    }

    @Override
    public String toString() {
        return super.toString() + " | Créditos extra: " + creditosExtra + " | Efecto: el Director te llama la atención";
    }
}
