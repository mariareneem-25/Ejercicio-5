package modelo;

public abstract class CartaEvento extends Carta {

    public CartaEvento(int id, String nombre, int costoEnergia, String descripcion) {
        super(id, nombre, costoEnergia, descripcion);
    }
}
