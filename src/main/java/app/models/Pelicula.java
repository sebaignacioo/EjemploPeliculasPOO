package app.models;

public class Pelicula {
    
    private int idPelicula;
    private Director director;
    private Actor[] reparto;
    private String nombreOriginal, nombreEspanol, sinopsis;

    public Pelicula(int idPelicula, Director director, Actor[] reparto, String nombreOriginal, String nombreEspanol) {
        this.idPelicula = idPelicula;
        this.director = director;
        this.reparto = reparto;
        this.nombreOriginal = nombreOriginal;
        this.nombreEspanol = nombreEspanol;
    }

    public Pelicula(int idPelicula, Director director, Actor[] reparto, String nombreOriginal, String nombreEspanol, String sinopsis) {
        this.idPelicula = idPelicula;
        this.director = director;
        this.reparto = reparto;
        this.nombreOriginal = nombreOriginal;
        this.nombreEspanol = nombreEspanol;
        this.sinopsis = sinopsis;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Actor[] getReparto() {
        return reparto;
    }

    public void setReparto(Actor[] reparto) {
        this.reparto = reparto;
    }

    public String getNombreOriginal() {
        return nombreOriginal;
    }

    public void setNombreOriginal(String nombreOriginal) {
        this.nombreOriginal = nombreOriginal;
    }

    public String getNombreEspanol() {
        return nombreEspanol;
    }

    public void setNombreEspanol(String nombreEspanol) {
        this.nombreEspanol = nombreEspanol;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    
    
    
}