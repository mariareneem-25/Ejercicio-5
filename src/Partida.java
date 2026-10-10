package modelo;

import java.util.ArrayList;

public class Partida {
    private static final int creditosMeta = 15;
    private static final int energiaInicial = 5;
    private static final int energiaPorTurno = 1;
    private static final int cartasIniciales = 4;

    private Mazo mazo;
    private Jugador jugador;
    private Director director;
    private int turno;

    public Partida(Mazo mazo) {
        this.mazo = mazo;
        this.mazo.reiniciar();
        this.jugador = new Jugador(energiaInicial);
        this.director = new Director();
        this.turno = 0;
        for (int i = 0; i < cartasIniciales; i++) {
            this.mazo.robarCarta();
        }
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Director getDirector() {
        return director;
    }

    public int getTurno() {
        return turno;
    }

    public int getCreditosMeta() {
        return creditosMeta;
    }

    public ArrayList<Carta> getMano() {
        return mazo.getMano();
    }

    public boolean isDerrota() {
        return director.haPerdidoElJugador();
    }

    public boolean isVictoria() {
        return !isDerrota() && jugador.getCreditos() >= creditosMeta;
    }

    public boolean isTerminada() {
        return isDerrota() || isVictoria();
    }

    public String iniciarTurno() {
        turno++;
        jugador.ganarEnergia(energiaPorTurno);
        return "===== Turno " + turno + " =====\nGanaste " + energiaPorTurno + " de energía por iniciar el turno.";
    }

    public Carta buscarCartaEnMano(int id) {
        for (Carta carta : mazo.getMano()) {
            if (carta.getId() == id) {
                return carta;
            }
        }
        return null;
    }

    public boolean puedeJugar(Carta carta) {
        return carta.puedeJugarse(jugador);
    }

    public String usarCarta(Carta carta) {
        carta.setEnMano(false);
        String mensaje = carta.jugarCarta(jugador, director);
        String notificacion = director.notificarCartaJugada(carta, jugador);
        if (!notificacion.isEmpty()) {
            mensaje += "\n" + notificacion;
        }
        return mensaje;
    }

    public String tomarCarta() {
        Carta carta = mazo.robarCarta();
        if (carta == null) {
            return "Ya tienes todas las cartas del mazo en la mano.";
        }
        return "Tomaste la carta: " + carta.getNombre() + " (ID " + carta.getId() + ").";
    }

    public String pasarTurno() {
        return "Pasaste el turno sin hacer nada.";
    }

    public String finalizarTurno() {
        return director.evaluarFinTurno(jugador);
    }
}
