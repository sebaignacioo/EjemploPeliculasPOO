package app.menu;

import app.data.database.ActorBD;
import app.data.database.ConexionBD;
import app.data.database.DirectorBD;
import app.models.Actor;
import app.models.Director;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MenuReparto {

    public static void ejecutar(Menu menu, ConexionBD conexion) throws IOException {
        short opt;
        int id;
        boolean espera;
        String nombre, apodo;
        HashMap<Integer, Actor> reparto;
        HashMap<Integer, Director> directores;
        DirectorBD directorBD = new DirectorBD(conexion);
        ActorBD actorBD = new ActorBD(conexion);
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        do {
            menu.mostrar();
            opt = menu.leerOpcion();
            espera = true;

            switch (opt) {
                case 1:
                    System.out.print("   -> Ingrese el ID de la película a mostrar reparto: ");
                    id = Integer.parseInt(lector.readLine());
                    reparto = actorBD.getActores(id);
                    System.out.print("  -> Reparto:");
                    if (reparto.size() == 0) System.out.println("No hay reparto disponible");
                    else System.out.println();
                    for (Map.Entry<Integer, Actor> rep : reparto.entrySet()) {
                        System.out.println(String.format("    - %s (id. %d)", rep.getValue().getApodo(),
                                rep.getValue().getIdActor()));
                        System.out.println();
                    }
                    break;
                case 2:
                    System.out.println(" -> Ingrese datos de actriz/actor");
                    System.out.print("  - Nombre y apellido: ");
                    nombre = lector.readLine();
                    System.out.print("  - Apodo: ");
                    apodo = lector.readLine();
                    if (actorBD.insertActor(new Actor(nombre, apodo))) System.out.println(" ( i ) Actriz/actor " +
                            "agregado con éxito!");
                    else System.out.println(" ( X ) Error al agregar actriz/actor.");
                    break;
                case 3:
                    System.out.println(" -> Ingrese datos de director");
                    System.out.print("  - Nombre y apellido: ");
                    nombre = lector.readLine();
                    System.out.print("  - Apodo: ");
                    apodo = lector.readLine();
                    if (actorBD.insertActor(new Actor(nombre, apodo))) System.out.println(" ( i ) Director agregado " +
                            "con éxito!");
                    else System.out.println(" ( X ) Error al agregar director.");
                    break;
                case 4, 5:
                    System.out.println("\n\n  --> Funcionalidad no implementada aún <--\n\n");
                    break;
                case 6:
                    System.out.print("   -> Ingrese el ID del actor/actriz a eliminar:");
                    id = Integer.parseInt(lector.readLine());
                    if (actorBD.deleteActor(id)) System.out.println(" ( i ) Actriz/actor eliminado con éxito!");
                    else System.out.println(" ( X ) Error al eliminar actriz/actor.");
                    break;
                case 7:
                    System.out.print("   -> Ingrese el ID del director a eliminar:");
                    id = Integer.parseInt(lector.readLine());
                    if (actorBD.deleteActor(id)) System.out.println(" ( i ) Director eliminado con éxito!");
                    else System.out.println(" ( X ) Error al eliminar director.");
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
