package app;

import app.data.database.ConexionBD;
import app.data.database.PeliculaBD;
import app.menu.Menu;
import app.menu.MenuItem;
import app.menu.MenuPrincipal;
import app.models.Pelicula;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws IOException, SQLException {

        ConexionBD conexion = new ConexionBD("localhost", "testing", "root", "ZFgpZmdfpvxXP2Rr");

        PeliculaBD peliculaBD = new PeliculaBD(conexion);
        ArrayList<Pelicula> peliculas = peliculaBD.getPeliculas();
        MenuItem[] mPrincipalItems = {
                new MenuItem("Películas", "Gestionar la información disponible acerca de las películas, listarlas, " +
                        "modificarlas, actualizarlas o eliminarlas."),
                new MenuItem("Reparto", "Gestionar la información disponible sobre actrices, actores y directores de " +
                        "las películas almacenadas"),
                new MenuItem("Búsqueda", "Permite realizar búsquedas de películas, actrices, actores y directores.")
        };

        MenuPrincipal.ejecutar(new Menu(mPrincipalItems), conexion);

    }

}
