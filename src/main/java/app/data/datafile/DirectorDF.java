package app.data.datafile;

import app.models.Director;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DirectorDF {

    private Datafile file;

    public DirectorDF() throws IOException {
        this.file = new Datafile("directores");
    }

    public String directorACSV(Director director) {
        String[] dir = new String[3];
        dir[0] = Integer.toString(director.getIdDirector());
        dir[1] = director.getNombre();
        dir[2] = director.getApodo();
        return Datafile.dataToCSV(dir);
    }

    public Director dataADirector(String[] data) {
        return new Director(Integer.parseInt(data[0]), data[1], data[2]);
    }

    public HashMap<Integer, Director> getDirectores() {
        ArrayList<String[]> data = this.file.obtenerDatos();
        HashMap<Integer, Director> directores = new HashMap<>();
        Director dir;
        for (String[] row: data) {
            dir = dataADirector(row);
            directores.put(dir.getIdDirector(), dir);
        }
        return directores;
    }

    public Director getDirector(int idDirector) {
        HashMap<Integer, Director> directores = getDirectores();
        if (directores.containsKey(idDirector))
            return directores.get(idDirector);
        return null;
    }

    public boolean insertDirector(Director director) {
        return this.file.insertarLinea(directorACSV(director));
    }

    public boolean updateDirector(Director newDirector) {
        return this.file.actualizarLinea(Integer.toString(newDirector.getIdDirector()), 0, directorACSV(newDirector));
    }

    public boolean deleteDirector(int idDirector) {
        return this.deleteDirector(idDirector);
    }

}
