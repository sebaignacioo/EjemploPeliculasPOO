package app.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Menu {

    private String titulo;
    private TreeMap<Short, MenuItem> items = new TreeMap<>();
    private boolean principal = false;
    private final BufferedReader lector;

    public Menu(MenuItem[] items, String titulo) {
        this.lector = new BufferedReader(new InputStreamReader(System.in));
        int i = 1;
        for (MenuItem it: items) {
            this.items.put((short) i, it);
            i++;
        }
        this.items.put((short) 9, new MenuItem("Salir del sistema", "Finaliza la ejecución del programa."));
        this.items.put((short) 0, new MenuItem("Volver al menú anterior", "Permite volver al menú anterior"));
        this.titulo = titulo;
    }

    public Menu(MenuItem[] items) {
        this.lector = new BufferedReader(new InputStreamReader(System.in));
        int i = 1;
        for (MenuItem it: items) {
            this.items.put((short) i, it);
            i++;
        }
        this.items.put((short) 9, new MenuItem("Salir del sistema", "Finaliza la ejecución del programa."));
        this.titulo = "principal";
        this.principal = true;
    }

    public void mostrar() {
        System.out.println(String.format("***** Menú %s *****", this.titulo.toLowerCase(Locale.ROOT)));
        System.out.println(" - Opciones numéricas:");
        for (Map.Entry<Short, MenuItem> entry: this.items.entrySet()) {
            System.out.println(String.format("  ( %d ) => %s", entry.getKey(), entry.getValue().getTitulo()));
            System.out.println(String.format("           %s", entry.getValue().getDescripcion()));
            System.out.println();
        }
    }

    public short leerOpcion() throws IOException {
        System.out.print("-> ( ? ) Por favor, ingrese su opción (numérica): ");
        short opt = Short.parseShort(lector.readLine());
        if (opt == 9) {
            System.out.println("-> ( X ) Saliendo del sistema...");
            System.exit(0);
        }
        else if (!this.items.containsKey(opt)) return -1;
        return opt;
    }

    public void esperar() {
    String input;
    Scanner readInput;
        do {
            System.out.println("-> (...) Presione Enter para continuar");
            readInput = new Scanner(System.in);
            input = readInput.nextLine();
        } while (!input.equals(""));
    }

}
