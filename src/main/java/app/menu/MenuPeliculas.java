package app.menu;

import app.data.database.ConexionBD;
import app.data.database.DirectorBD;
import app.data.database.PeliculaBD;
import app.models.Actor;
import app.models.Pelicula;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuPeliculas {

    public static void ejecutar(Menu menu, ConexionBD conexion) throws IOException {
        short opt;
        boolean espera;
        ArrayList<Pelicula> peliculas;
        PeliculaBD peliculaBD = new PeliculaBD(conexion);
        DirectorBD directorBD = new DirectorBD(conexion);
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        do {
            menu.mostrar();
            opt = menu.leerOpcion();
            espera = true;

            switch (opt) {
                case 1:
                    peliculas = peliculaBD.getPeliculas();
                    for (Pelicula pel : peliculas) {
                        System.out.println(String.format("***** %s *****", pel.getNombreOriginal()));
                        System.out.println(String.format("  -> ID: %d", pel.getIdPelicula()));
                        System.out.println(String.format("  -> Nombre en español: %s", pel.getNombreEspanol()));
                        System.out.println(String.format("  -> Año de estreno: %d", pel.getAnno()));
                        System.out.println(String.format("  -> Director: %s (id. %d)", pel.getDirector().getApodo(),
                                pel.getDirector().getIdDirector()));
                        System.out.print("  -> Reparto:");
                        if (pel.getReparto().size() == 0) System.out.println("No hay reparto disponible");
                        else System.out.println();

                        for (Map.Entry<Integer, Actor> actEntry : pel.getReparto().entrySet()) {
                            System.out.println(String.format("    - %s (id. %d)", actEntry.getValue().getApodo(),
                                    actEntry.getValue().getIdActor()));
                        }
                        System.out.println();
                    }
                    break;
                case 2:
                    System.out.println(" -> Ingrese datos de la película");
                    System.out.print("  - ID Director: ");
                    int idDirector = Integer.parseInt(lector.readLine());
                    System.out.print("  - Título original: ");
                    String tituloOriginal = lector.readLine();
                    System.out.print("  - Título en español: ");
                    String tituloEsp = lector.readLine();
                    System.out.print("  - Año de estreno: ");
                    int anno = Integer.parseInt(lector.readLine());
                    if (peliculaBD.insertPelicula(new Pelicula(directorBD.getDirector(idDirector), new HashMap<Integer,
                            Actor>(), tituloOriginal, tituloEsp, anno)))
                        System.out.println(" ( i ) Película agregada con éxito!");
                    System.out.println(" ( X ) Error al agregar película.");
                    break;
                case 3:
                    System.out.println("\n\n  --> Funcionalidad no implementada aún <--\n\n");
                    break;
                case 4:
                    System.out.print("   -> Ingrese el ID de la película a eliminar: ");
                    int id = Integer.parseInt(lector.readLine());
                    if (peliculaBD.deletePelicula(id)) System.out.println(" ( i ) Película eliminada con éxito!");
                    else System.out.println(" ( X ) Error al eliminar película.");
                    break;
                case 0:
                    espera = false;
                    break;
                default:
                    System.out.println(" (*) Opción no válida. Intente nuevamente.");
                    break;
            }
            if (espera) menu.esperar();
        } while(opt != 0);
    }

}
