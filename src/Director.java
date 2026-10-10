package modelo;

import java.util.Random;

public class Director {
    private static final int maximoLlamadas = 3;
    private static final int turnosPorTarea = 3;
    private static final int energiaPorTarea = 2;
    private static final String[] tiposDeTarea = { "Curso", "Catedrático", "Evento (Misión)" };

    private int llamadasAtencion;
    private String tipoTarea;
    private int turnosRestantes;
    private boolean tareaNueva;
    private Random random;

    public Director() {
        this.llamadasAtencion = 0;
        this.random = new Random();
        asignarTarea();
    }

    public int getLlamadasAtencion() {
        return llamadasAtencion;
    }

    public int getMaximoLlamadas() {
        return maximoLlamadas;
    }

    public String getTipoTarea() {
        return tipoTarea;
    }

    public int getTurnosRestantes() {
        if (tareaNueva) {
            return turnosRestantes + 1;
        }
        return turnosRestantes;
    }

    public boolean haPerdidoElJugador() {
        return llamadasAtencion >= maximoLlamadas;
    }

    public void asignarTarea() {
        tipoTarea = tiposDeTarea[random.nextInt(tiposDeTarea.length)];
        turnosRestantes = turnosPorTarea - 1;
        tareaNueva = true;
    }

    public String llamarAtencion() {
        return llamarAtencion("incumpliste una regla del curso");
    }

    public String llamarAtencion(String motivo) {
        llamadasAtencion++;
        return "El Director te llama la atención porque " + motivo + ". Llamadas de atención: " + llamadasAtencion
                + "/" + maximoLlamadas + ".";
    }

    public String perdonarLlamadas(int cantidad) {
        if (llamadasAtencion == 0) {
            return "El Director no tenía llamadas de atención que perdonarte.";
        }
        int perdonadas = Math.min(cantidad, llamadasAtencion);
        llamadasAtencion -= perdonadas;
        return "El Director te perdona " + perdonadas + " llamada(s) de atención. Llamadas de atención: "
                + llamadasAtencion + "/" + maximoLlamadas + ".";
    }

    public String notificarCartaJugada(Carta carta, Jugador jugador) {
        if (!carta.getTipo().equals(tipoTarea)) {
            return "";
        }
        jugador.ganarEnergia(energiaPorTarea);
        asignarTarea();
        return "El Director ve que cumpliste la mini tarea y te da " + energiaPorTarea
                + " de energía. Nueva mini tarea: jugar una carta de tipo " + tipoTarea + ".";
    }

    public String evaluarFinTurno(Jugador jugador) {
        String mensaje = "";
        if (tareaNueva) {
            tareaNueva = false;
        } else {
            turnosRestantes--;
            if (turnosRestantes <= 0) {
                mensaje += llamarAtencion("no cumpliste la mini tarea de jugar una carta de tipo " + tipoTarea);
                asignarTarea();
                mensaje += "\nNueva mini tarea: jugar una carta de tipo " + tipoTarea + ".";
            }
        }
        if (jugador.getEnergia() == 0) {
            if (!mensaje.isEmpty()) {
                mensaje += "\n";
            }
            mensaje += llamarAtencion("te quedaste sin energía");
        }
        return mensaje;
    }

    @Override
    public String toString() {
        return "Llamadas de atención: " + llamadasAtencion + "/" + maximoLlamadas + " | Mini tarea: jugar una carta de tipo "
                + tipoTarea + " (turnos restantes: " + getTurnosRestantes() + ")";
    }
}
