package modelo;

public abstract class Carta implements Comparable<Carta> {
    private int id;
    private String nombre;
    private int costoEnergia;
    private String descripcion;
    private boolean enMano;

    public Carta(int id, String nombre, int costoEnergia, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.costoEnergia = costoEnergia;
        this.descripcion = descripcion;
        this.enMano = false;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCostoEnergia() {
        return costoEnergia;
    }

    public void setCostoEnergia(int costoEnergia) {
        this.costoEnergia = costoEnergia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEnMano() {
        return enMano;
    }

    public void setEnMano(boolean enMano) {
        this.enMano = enMano;
    }

    public boolean puedeJugarse(Jugador jugador) {
        return jugador.getEnergia() >= costoEnergia;
    }

    public abstract String getTipo();

    public abstract String jugarCarta(Jugador jugador, Director director);

    @Override
    public int compareTo(Carta otra) {
        if (costoEnergia != otra.costoEnergia) {
            return Integer.compare(costoEnergia, otra.costoEnergia);
        }
        return Integer.compare(id, otra.id);
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }
        if (objeto == null || getClass() != objeto.getClass()) {
            return false;
        }
        Carta otra = (Carta) objeto;
        return id == otra.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "ID " + id + " | " + nombre + " | Tipo: " + getTipo() + " | Costo de energía: " + costoEnergia
                + " | " + descripcion;
    }
}
