package app.data.datafile;

import app.models.Pelicula;

import java.io.IOException;
import java.util.ArrayList;

public class PeliculaDF {

    private Datafile file;
    private DirectorDF directorDF;
    private ActorDF actorDF;

    public PeliculaDF() throws IOException {
        this.file = new Datafile("peliculas");
        this.directorDF = new DirectorDF();
        this.actorDF = new ActorDF();
    }

    public String peliculaACSV(Pelicula pelicula) {
        String[] pel = new String[5];
        pel[0] = Integer.toString(pelicula.getIdPelicula());
        pel[1] = Integer.toString(pelicula.getDirector().getIdDirector());
        pel[2] = pelicula.getNombreOriginal();
        pel[3] = pelicula.getNombreEspanol();
        pel[4] = Integer.toString(pelicula.getAnno());
        return Datafile.dataToCSV(pel);
    }

    public Pelicula dataAPelicula(String[] data) {
        return new Pelicula(Integer.parseInt(data[0]),
                            this.directorDF.getDirector(Integer.parseInt(data[1])),
                            this.actorDF.getActores(Integer.parseInt(data[0])),
                            data[2],
                            data[3],
                            Integer.parseInt(data[4]));
    }

    public ArrayList<Pelicula> getPeliculas() {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        ArrayList<String []> data = this.file.obtenerDatos();
        for (String[] row: data) {
            Pelicula pel = new Pelicula(Integer.parseInt(row[0]), this.directorDF.getDirector(Integer.parseInt(row[1])),
                    this.actorDF.getActores(Integer.parseInt(row[0])), row[2], row[3], Integer.parseInt(row[4]));
            peliculas.add(pel);
        }
        return peliculas;
    }

    public Pelicula getPelicula(int idPelicula) {
        ArrayList<Pelicula> peliculas = getPeliculas();
        for (Pelicula pel: peliculas) {
            if (pel.getIdPelicula() == idPelicula) return pel;
        }
        return null;
    }

    public boolean insertPelicula(Pelicula pelicula) {
        return this.file.insertarLinea(peliculaACSV(pelicula));
    }

    public boolean updatePelicula(Pelicula newPelicula) {
        return this.file.actualizarLinea(Integer.toString(newPelicula.getIdPelicula()), 0, peliculaACSV(newPelicula));
    }

    public boolean deletePelicula(int idPelicula) {
        return this.deletePelicula(idPelicula);
    }

}
