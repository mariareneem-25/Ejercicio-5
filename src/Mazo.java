package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Mazo {
    private ArrayList<Carta> cartas;
    private Random random;

    public Mazo() {
        this.cartas = new ArrayList<>();
        this.random = new Random();
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public ArrayList<Carta> getCartas() {
        return new ArrayList<>(cartas);
    }

    public ArrayList<Carta> getMano() {
        ArrayList<Carta> mano = new ArrayList<>();
        for (Carta carta : cartas) {
            if (carta.isEnMano()) {
                mano.add(carta);
            }
        }
        return mano;
    }

    public Carta buscar(int id) {
        for (Carta carta : cartas) {
            if (carta.getId() == id) {
                return carta;
            }
        }
        return null;
    }

    public Carta buscar(String nombre) {
        for (Carta carta : cartas) {
            if (carta.getNombre().equalsIgnoreCase(nombre.trim())) {
                return carta;
            }
        }
        for (Carta carta : cartas) {
            if (carta.getNombre().toLowerCase().contains(nombre.trim().toLowerCase())) {
                return carta;
            }
        }
        return null;
    }

    public void ordenarPorCosto() {
        Collections.sort(cartas);
    }

    public void ordenarPorCosto(boolean ascendente) {
        Collections.sort(cartas);
        if (!ascendente) {
            Collections.reverse(cartas);
        }
    }

    public Carta robarCarta() {
        ArrayList<Carta> disponibles = new ArrayList<>();
        for (Carta carta : cartas) {
            if (!carta.isEnMano()) {
                disponibles.add(carta);
            }
        }
        if (disponibles.isEmpty()) {
            return null;
        }
        Carta elegida = disponibles.get(random.nextInt(disponibles.size()));
        elegida.setEnMano(true);
        return elegida;
    }

    public void reiniciar() {
        for (Carta carta : cartas) {
            carta.setEnMano(false);
        }
    }

    public int getCantidad() {
        return cartas.size();
    }
}
