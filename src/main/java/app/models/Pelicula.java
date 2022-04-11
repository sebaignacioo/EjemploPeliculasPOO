package app.models;

import java.util.HashMap;

public class Pelicula {
    
    private int idPelicula, anno;
    private Director director;
    private HashMap<Integer, Actor> reparto;
    private String nombreOriginal, nombreEspanol, sinopsis;

    public Pelicula(int idPelicula, Director director, HashMap<Integer, Actor> reparto, String nombreOriginal, int anno) {
        this.idPelicula = idPelicula;
        this.director = director;
        this.reparto = reparto;
        this.anno = anno;
        this.nombreOriginal = nombreOriginal;
        this.nombreEspanol = nombreOriginal;
    }

    public Pelicula(int idPelicula, Director director, HashMap<Integer, Actor> reparto, String nombreOriginal, String nombreEspanol, int anno) {
        this.idPelicula = idPelicula;
        this.director = director;
        this.reparto = reparto;
        this.anno = anno;
        this.nombreOriginal = nombreOriginal;
        this.nombreEspanol = nombreEspanol;
    }

    public Pelicula(int idPelicula, Director director, HashMap<Integer, Actor> reparto, String nombreOriginal, String nombreEspanol,
                    int anno, String sinopsis) {
        this.idPelicula = idPelicula;
        this.director = director;
        this.reparto = reparto;
        this.anno = anno;
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

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public HashMap<Integer, Actor> getReparto() {
        return reparto;
    }

    public void setReparto(HashMap<Integer, Actor> reparto) {
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
