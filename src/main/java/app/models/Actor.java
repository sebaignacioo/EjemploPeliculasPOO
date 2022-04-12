package app.models;

public class Actor {
    
    private int idActor;
    private String nombre, apodo;

    public Actor(int idActor, String nombre) {
        this.idActor = idActor;
        this.nombre = nombre;
        this.apodo = nombre;
    }

    public Actor(int idActor, String nombre, String apodo) {
        this.idActor = idActor;
        this.nombre = nombre;
        this.apodo = apodo;
    }

    public Actor(String nombre, String apodo) {
        this.nombre = nombre;
        this.apodo = apodo;
    }

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
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
