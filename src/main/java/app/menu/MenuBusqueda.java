package app.menu;

import app.data.database.ConexionBD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuBusqueda {

    public static void ejecutar(Menu menu, ConexionBD conexion) throws IOException, SQLException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        short opt;
        boolean espera;
        do {
            espera = true;
            menu.mostrar();
            opt = menu.leerOpcion();

            switch (opt) {
                case 1, 2, 3:
                    System.out.print("Ingrese término a buscar: ");
                    String[] tables;
                    String termino = lector.readLine();
                    if (opt == 1) tables = new String[]{"Pelicula", "titulo_original", "titulo_espanol"};
                    else if (opt == 2) tables = new String[]{"Actor", "nombre", "apodo"};
                    else tables = new String[]{"Director", "nombre", "apodo"};
                    String sql = String.format("SELECT * FROM `%s` WHERE ", tables[0]);
                    for (int i = 1; i < tables.length; i++) {
                        if (i > 1) sql = sql.concat(" OR");
                        sql = sql.concat(String.format(" `%s` LIKE %s", tables[i], "'%" + termino + "%'"));
                    }
                    sql = sql.concat(";");
                    ResultSet rs = conexion.executeSQL(sql);
                    if (rs.next())
                        while (rs.next()) {
                            if (opt == 1) {
                                System.out.println(String.format("***** %s *****", rs.getString("titulo_original")));
                                System.out.println(String.format("  -> ID: %d", rs.getInt("id")));
                                System.out.println(String.format("  -> Nombre en español: %s",
                                        rs.getString("titulo_espanol")));
                                System.out.println(String.format("  -> Año de estreno: %d", rs.getInt("anno")));
                                System.out.println();
                            } else {
                                System.out.println("**************");
                                System.out.println(String.format("  -> Nombre: %s", rs.getString("nombre")));
                                System.out.println(String.format("  -> Apodo: %s", rs.getString("apodo")));
                                System.out.println();
                            }
                        }
                    else System.out.println("  ( ? ) No se encontraron elementos con su búsqueda.");
                    break;
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
