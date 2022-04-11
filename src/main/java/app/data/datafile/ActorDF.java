package app.data.datafile;

import app.models.Actor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ActorDF {

    private Datafile actorDF, actorPeliculaDF;

    public ActorDF() throws IOException {
        this.actorDF = new Datafile("actores");
        this.actorPeliculaDF = new Datafile("actores-peliculas");
    }

    public String actorACSV(Actor actor) {
        String[] act = new String[3];
        act[0] = Integer.toString(actor.getIdActor());
        act[1] = actor.getNombre();
        act[2] = actor.getApodo();
        return Datafile.dataToCSV(act);
    }

    public static String actorPelACSV(int idActor, int idPelicula) {
        String[] act = new String[2];
        act[0] = Integer.toString(idActor);
        act[1] = Integer.toString(idPelicula);
        return Datafile.dataToCSV(act);
    }

    public Actor dataAActor(String[] data) {
        return new Actor(Integer.parseInt(data[0]), data[1], data[2]);
    }

    public HashMap<Integer, Actor> getActores() {
        ArrayList<String[]> data = this.actorDF.obtenerDatos();
        HashMap<Integer, Actor> actores = new HashMap<>();
        Actor act;
        for (String[] row: data) {
            act = dataAActor(row);
            actores.put(act.getIdActor(), act);
        }
        return actores;
    }

    public HashMap<Integer, Actor> getActores(int idPelicula) {
        ArrayList<String[]> data = this.actorPeliculaDF.obtenerDatos();
        HashMap<Integer, Actor> actores = new HashMap<>();
        Actor act;
        for (String[] row: data) {
            if (Integer.parseInt(row[0]) == idPelicula) {
                act = getActor(Integer.parseInt(row[1]));
                actores.put(act.getIdActor(), act);
            }
        }
        return actores;
    }

    public Actor getActor(int idActor) {
        HashMap<Integer, Actor> actores = getActores();
        if (actores.containsKey(idActor))
            return actores.get(idActor);
        return null;
    }

    public boolean insertActor(Actor actor) {
        return this.actorDF.insertarLinea(actorACSV(actor));
    }

    public boolean updateActor(Actor newActor) {
        return this.actorDF.actualizarLinea(Integer.toString(newActor.getIdActor()), 0, actorACSV(newActor));
    }

    public boolean deleteActor(int idActor) {
        return this.deleteActor(idActor);
    }

}
