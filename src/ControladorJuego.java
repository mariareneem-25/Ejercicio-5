package controlador;

import modelo.Carta;
import modelo.FabricaCartas;
import modelo.Mazo;
import modelo.Partida;
import vista.VistaConsola;

public class ControladorJuego {
    private Mazo mazo;
    private VistaConsola vista;
    private Partida partida;

    public ControladorJuego() {
        FabricaCartas fabrica = new FabricaCartas();
        this.mazo = fabrica.crearMazo();
        this.vista = new VistaConsola();
    }

    public void iniciar() {
        vista.mostrarMensaje("Se cargaron " + mazo.getCantidad() + " cartas en el mazo.");
        boolean salir = false;
        while (!salir) {
            vista.mostrarMenuPrincipal();
            int opcion = vista.leerNumero("Elige una opción: ");
            switch (opcion) {
                case 1:
                    listarCartas();
                    break;
                case 2:
                    buscarCarta();
                    break;
                case 3:
                    ordenarCartas();
                    break;
                case 4:
                    jugarPartida();
                    break;
                case 0:
                    salir = true;
                    vista.mostrarMensaje("Hasta pronto.");
                    break;
                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        }
    }

    private void listarCartas() {
        vista.mostrarCartas(mazo.getCartas());
    }

    private void buscarCarta() {
        String texto = vista.leerTexto("Escribe el ID o el nombre de la carta: ");
        if (texto.isEmpty()) {
            vista.mostrarMensaje("No escribiste nada.");
            return;
        }
        Carta carta;
        try {
            carta = mazo.buscar(Integer.parseInt(texto));
        } catch (NumberFormatException e) {
            carta = mazo.buscar(texto);
        }
        if (carta == null) {
            vista.mostrarMensaje("No se encontró ninguna carta con ese ID o nombre.");
        } else {
            vista.mostrarMensaje(carta.toString());
        }
    }

    private void ordenarCartas() {
        vista.mostrarMenuOrden();
        int opcion = vista.leerNumero("Elige el orden: ");
        if (opcion == 1) {
            mazo.ordenarPorCosto();
        } else if (opcion == 2) {
            mazo.ordenarPorCosto(false);
        } else {
            vista.mostrarMensaje("Opción no válida.");
            return;
        }
        vista.mostrarMensaje("Mazo ordenado por costo de energía:");
        vista.mostrarCartas(mazo.getCartas());
    }

    private void jugarPartida() {
        partida = new Partida(mazo);
        boolean abandonada = false;
        while (!partida.isTerminada() && !abandonada) {
            vista.mostrarMensaje(partida.iniciarTurno());
            boolean turnoTerminado = false;
            while (!turnoTerminado) {
                vista.mostrarEstado(partida);
                vista.mostrarMenuTurno();
                int opcion = vista.leerNumero("Elige una opción: ");
                switch (opcion) {
                    case 1:
                        turnoTerminado = usarCarta();
                        break;
                    case 2:
                        vista.mostrarMensaje(partida.tomarCarta());
                        turnoTerminado = true;
                        break;
                    case 3:
                        vista.mostrarMensaje(partida.pasarTurno());
                        turnoTerminado = true;
                        break;
                    case 0:
                        abandonada = true;
                        turnoTerminado = true;
                        break;
                    default:
                        vista.mostrarMensaje("Opción no válida.");
                }
            }
            if (!abandonada) {
                String mensaje = partida.finalizarTurno();
                if (!mensaje.isEmpty()) {
                    vista.mostrarMensaje(mensaje);
                }
            }
        }
        if (abandonada) {
            vista.mostrarMensaje("Abandonaste la partida.");
        } else {
            vista.mostrarFinPartida(partida.isVictoria());
        }
    }

    private boolean usarCarta() {
        int id = vista.leerNumero("Escribe el ID de la carta que quieres usar: ");
        Carta carta = partida.buscarCartaEnMano(id);
        if (carta == null) {
            vista.mostrarMensaje("Esa carta no está en tu mano.");
            return false;
        }
        if (!partida.puedeJugar(carta)) {
            vista.mostrarMensaje("No puedes jugar esa carta ahora: te falta energía o debes aprobar el curso anterior.");
            return false;
        }
        vista.mostrarMensaje(partida.usarCarta(carta));
        return true;
    }
}
