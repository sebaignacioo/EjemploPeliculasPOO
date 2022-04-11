package app;

import app.models.Actor;
import app.models.Director;
import app.models.Pelicula;

public class App {

    public static void main(String[] args) {
        // Creamos datos iniciales para el proyecto

        // Película 1
        Director directorp1 = new Director(1, "Sian Heder");
        Actor actor1p1 = new Actor(1, "Marlee Matlin");
        Actor actor2p1 = new Actor(2, "Troy Kotsur");
        Actor actor3p1 = new Actor(3, "Daniel Durant");
        Actor[] reparto1 = {actor1p1, actor2p1, actor3p1};
        Pelicula pelicula1 = new Pelicula(1, directorp1, reparto1, "CODA", "CODA: Señales del corazón", 2021);

        // Película 2
        Director directorp2 = new Director(2, "Kenneth Branagh");
        Actor actor1p2 = new Actor(4, "Caitriona Balfe");
        Actor actor2p2 = new Actor(5, "Judi Dench");
        Actor actor3p2 = new Actor(6, "Jamie Dornan");
        Actor[] reparto2 = {actor1p1, actor2p1, actor3p1};
        Pelicula pelicula2 = new Pelicula(2, directorp1, reparto1, "Belfast", 2021);

        // Película 3
        Director directorp3 = new Director(3, "Adam McKay");
        Actor actor1p3 = new Actor(7, "Leonardo DiCaprio");
        Actor actor2p3 = new Actor(8, "Jennifer Lawrence");
        Actor actor3p3 = new Actor(9, "Rob Morgan");
        Actor[] reparto3 = {actor1p1, actor2p1, actor3p1};
        Pelicula pelicula3 = new Pelicula(3, directorp1, reparto1, "Don't look up", "No mires arriba", 2021);

        // Guardamos las películas en un arreglo de películas
        Pelicula[] peliculas = {pelicula1, pelicula2, pelicula3};

        // Recorremos las películas e imprimimos sus valores
        for (Pelicula pel: peliculas) {
            System.out.println(String.format("***** %s *****", pel.getNombreOriginal()));
            System.out.println(String.format("  -> Nombre en español: %s", pel.getNombreEspanol()));
            System.out.println(String.format("  -> Año de estreno: %d", pel.getAnno()));
            System.out.println(String.format("  -> Director: %s", pel.getDirector().getApodo()));
            System.out.println("  -> Reparto:");
            for (Actor act: pel.getReparto()) {
                System.out.println(String.format("    - %s", act.getApodo()));
            }
            System.out.println();
        }
    }
    
}
