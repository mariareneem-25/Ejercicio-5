package modelo;

public class CartaCurso extends Carta {
    private int creditos;
    private int nivelDificultad;

    public CartaCurso(int id, String nombre, int costoEnergia, String descripcion, int creditos,
            int nivelDificultad) {
        super(id, nombre, costoEnergia, descripcion);
        this.creditos = creditos;
        this.nivelDificultad = nivelDificultad;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(int nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    @Override
    public boolean puedeJugarse(Jugador jugador) {
        return super.puedeJugarse(jugador) && nivelDificultad <= jugador.getNivelAprobado() + 1;
    }

    @Override
    public String jugarCarta(Jugador jugador, Director director) {
        jugador.perderEnergia(getCostoEnergia());
        jugador.ganarCreditos(creditos);
        jugador.aprobarNivel(nivelDificultad);
        return "Aprobaste " + getNombre() + ". Perdiste " + getCostoEnergia() + " de energía y ganaste " + creditos
                + " créditos.";
    }

    @Override
    public String toString() {
        return super.toString() + " | Créditos: " + creditos + " | Nivel de dificultad: " + nivelDificultad;
    }
}
