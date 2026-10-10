package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FabricaCartas {
    private Random random;
    private int siguienteId;

    public FabricaCartas() {
        this.random = new Random();
        this.siguienteId = 1;
    }

    public Mazo crearMazo() {
        Mazo mazo = new Mazo();
        agregarCursos(mazo);
        agregarCatedraticos(mazo, 3);
        agregarMisiones(mazo, 3);
        agregarDesafios(mazo);
        return mazo;
    }

    private void agregarCursos(Mazo mazo) {
        mazo.agregarCarta(new CartaCurso(siguienteId++, "Álgebra", 2, "Primer curso de matemática de la carrera", 3, 1));
        mazo.agregarCarta(new CartaCurso(siguienteId++, "Precálculo", 3, "Prepara las bases para el cálculo", 4, 2));
        mazo.agregarCarta(new CartaCurso(siguienteId++, "Cálculo 1", 4, "Límites, derivadas y mucho estudio", 5, 3));
        mazo.agregarCarta(new CartaCurso(siguienteId++, "Cálculo 2", 5, "Integrales y series, el jefe final", 6, 4));
    }

    private void agregarCatedraticos(Mazo mazo, int cantidad) {
        String[] nombres = { "Ing. Morales", "Lic. Pérez", "Dr. López", "Licda. Castillo", "Ing. Herrera" };
        String[] departamentos = { "Matemática", "Física", "Electrónica", "Ciencias Sociales", "Computación" };
        ArrayList<Integer> indices = indicesAleatorios(nombres.length);
        for (int i = 0; i < cantidad; i++) {
            int indice = indices.get(i);
            int llamadas = 1 + random.nextInt(2);
            int tiempo = 2 + random.nextInt(3);
            int costo = 2 + random.nextInt(2);
            mazo.agregarCarta(new CartaCatedratico(siguienteId++, nombres[indice], costo,
                    "Catedrático del departamento de " + departamentos[indice], departamentos[indice], llamadas,
                    tiempo));
        }
    }

    private void agregarMisiones(Mazo mazo, int cantidad) {
        String[] nombres = { "Charla Delva", "Tour en la U", "Curso Extracurricular", "Feria de Clubes" };
        String[] descripciones = { "Asistes a una charla que te motiva", "Muestras la universidad a nuevos estudiantes",
                "Te inscribes en un curso fuera del pénsum", "Visitas los clubes de la universidad" };
        ArrayList<Integer> indices = indicesAleatorios(nombres.length);
        for (int i = 0; i < cantidad; i++) {
            int indice = indices.get(i);
            int energia = 3 + random.nextInt(3);
            mazo.agregarCarta(new CartaMision(siguienteId++, nombres[indice], 1, descripciones[indice], energia));
        }
    }

    private void agregarDesafios(Mazo mazo) {
        mazo.agregarCarta(new CartaDesafio(siguienteId++, "Semana de Parciales", 2,
                "Semana cargada de exámenes", 2 + random.nextInt(2)));
        mazo.agregarCarta(new CartaDesafio(siguienteId++, "Entrega de Proyecto", 2,
                "Fecha límite de un proyecto grande", 2 + random.nextInt(2)));
    }

    private ArrayList<Integer> indicesAleatorios(int total) {
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices, random);
        return indices;
    }
}
