package app.menu;

import app.data.database.ConexionBD;

import java.io.IOException;
import java.sql.SQLException;

public class MenuPrincipal {

    public static void ejecutar(Menu menu, ConexionBD conexion) throws IOException, SQLException {
        MenuItem[] mPeliculasItems = {
                new MenuItem("Ver películas", "Listar las películas y sus datos disponibles."),
                new MenuItem("Agregar película", "Permite agregar una nueva película"),
                new MenuItem("Actualizar película", "Actualiza los datos de una película."),
                new MenuItem("Eliminar película", "Permite eliminar una película"),
        };
        MenuItem[] mRepartoItems = {
                new MenuItem("Ver reparto", "Ver detalles del reparto de una película."),
                new MenuItem("Agregar actriz/actor", "Permite agregar actrices y actores."),
                new MenuItem("Agregar director", "Permite agregar directores de películas."),
                new MenuItem("Actualizar actriz/actor", "Actualiza los datos de actrices y actores."),
                new MenuItem("Actualizar director", "Actualiza los datos de directores."),
                new MenuItem("Eliminar actriz/actor", "Permite eliminar actrices y actores"),
                new MenuItem("Eliminar director", "Permite eliminar directores"),
        };
        MenuItem[] mBusquedaItems = {
                new MenuItem("Buscar película", "Permite buscar una película por su título."),
                new MenuItem("Buscar actriz/actor", "Permite buscar actrices y actores por su nombre o apodo"),
                new MenuItem("Buscar director", "Permite buscar directores por su nombre o apodo"),
        };
        Menu menuPeliculas = new Menu(mPeliculasItems, "peliculas");
        Menu menuReparto = new Menu(mRepartoItems, "reparto");
        Menu menuBusqueda = new Menu(mBusquedaItems, "búsqueda");
        short opt;
        boolean espera;
        do {
            espera = true;
            menu.mostrar();
            opt = menu.leerOpcion();

            switch (opt) {
                case 1:
                    espera = false;
                    MenuPeliculas.ejecutar(menuPeliculas, conexion);
                    break;
                case 2:
                    espera = false;
                    MenuReparto.ejecutar(menuReparto, conexion);
                    break;
                case 3:
                    espera = false;
                    MenuBusqueda.ejecutar(menuBusqueda, conexion);
                case 0:
                    break;
                default:
                    System.out.println(" (*) Opción no válida. Intente nuevamente.");
                    break;
            }
            if (espera) menu.esperar();

        } while(opt != 0);
    }

}
