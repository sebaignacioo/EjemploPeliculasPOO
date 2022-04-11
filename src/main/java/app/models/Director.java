package app.models;

public class Director {
    
    private int idDirector;
    private String nombre, apodo;

    public Director(int idDirector, String nombre) {
        this.idDirector = idDirector;
        this.nombre = nombre;
        this.apodo = nombre;
    }

    public Director(int idActor, String nombre, String apodo) {
        this.idDirector = idActor;
        this.nombre = nombre;
        this.apodo = apodo;
    }

    public int getIdActor() {
        return idDirector;
    }

    public void setIdActor(int idActor) {
        this.idDirector = idActor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }
    
    
    
}
