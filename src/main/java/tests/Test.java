package tests;

import app.data.datafile.Datafile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws IOException, SQLException {
        short opt = 1;
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
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
        System.out.println(sql);
    }

}
