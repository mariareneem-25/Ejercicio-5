package modelo;

public class Jugador {
    private int energia;
    private int creditos;
    private int nivelAprobado;

    public Jugador(int energiaInicial) {
        this.energia = energiaInicial;
        this.creditos = 0;
        this.nivelAprobado = 0;
    }

    public int getEnergia() {
        return energia;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getNivelAprobado() {
        return nivelAprobado;
    }

    public void ganarEnergia(int cantidad) {
        energia += cantidad;
    }

    public void perderEnergia(int cantidad) {
        energia = Math.max(0, energia - cantidad);
    }

    public void ganarCreditos(int cantidad) {
        creditos += cantidad;
    }

    public void aprobarNivel(int nivel) {
        nivelAprobado = Math.max(nivelAprobado, nivel);
    }

    @Override
    public String toString() {
        return "Energía: " + energia + " | Créditos: " + creditos + " | Nivel aprobado: " + nivelAprobado;
    }
}
