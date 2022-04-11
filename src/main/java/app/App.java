package app;

import app.data.database.ConexionBD;
import app.data.database.PeliculaBD;
import app.models.Actor;
import app.models.Pelicula;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class App {

    public static void main(String[] args) throws IOException, SQLException {

        ConexionBD conexion = new ConexionBD("localhost", "proyecto_peliculas", "root", "");

        PeliculaBD peliculaBD = new PeliculaBD(conexion);
        ArrayList<Pelicula> peliculas = peliculaBD.getPeliculas();

        for (Pelicula pel: peliculas) {
            System.out.println(String.format("***** %s *****", pel.getNombreOriginal()));
            System.out.println(String.format("  -> Nombre en español: %s", pel.getNombreEspanol()));
            System.out.println(String.format("  -> Año de estreno: %d", pel.getAnno()));
            System.out.println(String.format("  -> Director: %s", pel.getDirector().getApodo()));
            System.out.println("  -> Reparto:");

            for (Map.Entry<Integer, Actor> actEntry: pel.getReparto().entrySet()) {
                System.out.println(String.format("    - %s", actEntry.getValue().getApodo()));
            }
            System.out.println();
        }

    }

}
