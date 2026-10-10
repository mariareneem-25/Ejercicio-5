package vista;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.Carta;
import modelo.Partida;

public class VistaConsola {
    private Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarMenuPrincipal() {
        System.out.println();
        System.out.println("===== UVG CARD BATTLE =====");
        System.out.println("1. Listar todas las cartas del mazo");
        System.out.println("2. Buscar una carta por ID o por nombre");
        System.out.println("3. Ordenar el mazo por costo de energía");
        System.out.println("4. Jugar una partida");
        System.out.println("0. Salir");
    }

    public void mostrarMenuTurno() {
        System.out.println();
        System.out.println("¿Qué quieres hacer en este turno?");
        System.out.println("1. Usar una carta");
        System.out.println("2. Tomar una carta");
        System.out.println("3. Pasar el turno");
        System.out.println("0. Abandonar la partida");
    }

    public void mostrarMenuOrden() {
        System.out.println("1. De menor a mayor costo");
        System.out.println("2. De mayor a menor costo");
    }

    public void mostrarCartas(ArrayList<Carta> cartas) {
        if (cartas.isEmpty()) {
            System.out.println("No hay cartas para mostrar.");
            return;
        }
        for (Carta carta : cartas) {
            System.out.println(carta);
        }
    }

    public void mostrarEstado(Partida partida) {
        System.out.println();
        System.out.println("--- Tu estado ---");
        System.out.println(partida.getJugador() + " (meta: " + partida.getCreditosMeta() + " créditos)");
        System.out.println(partida.getDirector());
        System.out.println("--- Tu mano ---");
        mostrarCartas(partida.getMano());
    }

    public void mostrarFinPartida(boolean victoria) {
        System.out.println();
        if (victoria) {
            System.out.println("¡Felicidades! Lograste los créditos necesarios y pasaste el semestre.");
        } else {
            System.out.println("El Director te llamó la atención demasiadas veces. Perdiste el semestre.");
        }
    }

    public int leerNumero(String pregunta) {
        System.out.print(pregunta);
        if (!scanner.hasNextLine()) {
            return 0;
        }
        String texto = scanner.nextLine().trim();
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String leerTexto(String pregunta) {
        System.out.print(pregunta);
        if (!scanner.hasNextLine()) {
            return "";
        }
        return scanner.nextLine().trim();
    }
}
