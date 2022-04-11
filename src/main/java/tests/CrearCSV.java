package tests;

import app.data.datafile.Datafile;
import app.models.Actor;
import app.models.Director;
import app.models.Pelicula;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CrearCSV {

    public static String peliculaACSV(Pelicula pelicula) {
        String[] pel = new String[5];
        pel[0] = Integer.toString(pelicula.getIdPelicula());
        pel[1] = Integer.toString(pelicula.getDirector().getIdDirector());
        pel[2] = pelicula.getNombreOriginal();
        pel[3] = pelicula.getNombreEspanol();
        pel[4] = Integer.toString(pelicula.getAnno());
        return Datafile.dataToCSV(pel);
    }

    public static String actorACSV(Actor actor) {
        String[] act = new String[3];
        act[0] = Integer.toString(actor.getIdActor());
        act[1] = actor.getNombre();
        act[2] = actor.getApodo();
        return Datafile.dataToCSV(act);
    }

    public static String directorACSV(Director director) {
        String[] dir = new String[3];
        dir[0] = Integer.toString(director.getIdDirector());
        dir[1] = director.getNombre();
        dir[2] = director.getApodo();
        return Datafile.dataToCSV(dir);
    }

    public static void main(String[] args) {
        // Creamos datos iniciales para el proyecto

        // Película 1
        Director directorp1 = new Director(1, "Sian Heder");
        Actor actor1p1 = new Actor(1, "Marlee Matlin");
        Actor actor2p1 = new Actor(2, "Troy Kotsur");
        Actor actor3p1 = new Actor(3, "Daniel Durant");
        HashMap<Integer, Actor> reparto1 = new HashMap<>();
        reparto1.put(actor1p1.getIdActor(), actor1p1);
        reparto1.put(actor2p1.getIdActor(), actor2p1);
        reparto1.put(actor3p1.getIdActor(), actor3p1);
        Pelicula pelicula1 = new Pelicula(1, directorp1, reparto1, "CODA", "CODA: Señales del corazón", 2021);

        // Película 2
        Director directorp2 = new Director(2, "Kenneth Branagh");
        Actor actor1p2 = new Actor(4, "Caitriona Balfe");
        Actor actor2p2 = new Actor(5, "Judi Dench");
        Actor actor3p2 = new Actor(6, "Jamie Dornan");
        HashMap<Integer, Actor> reparto2 = new HashMap<>();
        reparto1.put(actor1p2.getIdActor(), actor1p2);
        reparto1.put(actor2p2.getIdActor(), actor2p2);
        reparto1.put(actor3p2.getIdActor(), actor3p2);
        Pelicula pelicula2 = new Pelicula(2, directorp2, reparto2, "Belfast", 2021);

        // Película 3
        Director directorp3 = new Director(3, "Adam McKay");
        Actor actor1p3 = new Actor(7, "Leonardo DiCaprio");
        Actor actor2p3 = new Actor(8, "Jennifer Lawrence");
        Actor actor3p3 = new Actor(9, "Rob Morgan");
        HashMap<Integer, Actor> reparto3 = new HashMap<>();
        reparto1.put(actor1p3.getIdActor(), actor1p3);
        reparto1.put(actor2p3.getIdActor(), actor2p3);
        reparto1.put(actor3p3.getIdActor(), actor3p3);
        Pelicula pelicula3 = new Pelicula(3, directorp3, reparto3, "Don't look up", "No mires arriba", 2021);

        Pelicula[] peliculas = {pelicula1, pelicula2, pelicula3};

        // Guardamos las películas en un arreglo de películas
        try {
            Datafile peliculasDF = new Datafile("peliculas");
            Datafile actoresDF = new Datafile("actores");
            Datafile directoresDF = new Datafile("directores");

            for (Pelicula pel: peliculas) {
                peliculasDF.insertarLinea(peliculaACSV(pel));
                directoresDF.insertarLinea(directorACSV(pel.getDirector()));
                for (Map.Entry<Integer, Actor> actEntry: pel.getReparto().entrySet()) {
                    actoresDF.insertarLinea(actorACSV(actEntry.getValue()));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
