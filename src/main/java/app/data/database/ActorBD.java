package app.data.database;

import app.models.Actor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ActorBD {

    private ConexionBD conexionBD;

    public ActorBD(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public HashMap<Integer, Actor> getActores() {
        HashMap<Integer, Actor> actores = new HashMap<>();
        ResultSet res = this.conexionBD.executeSelect("Actor");
        try {
            while (res.next()) {
                actores.put(res.getInt("id"),
                        new Actor(res.getInt("id"),
                                res.getString("nombre"),
                                res.getString("apodo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actores;
    }

    public HashMap<Integer, Actor> getActores(int idPelicula) {
        String sql = String.format("SELECT * FROM ActorPelicula WHERE id_pelicula = %d", idPelicula);
        HashMap<Integer, Actor> todosActores = getActores(), actoresPeli = new HashMap<>();
        ResultSet res = this.conexionBD.executeSQL(sql);
        try {
            while (res.next()) {
                Actor actor = todosActores.get(res.getInt("id_actor"));
                actoresPeli.put(actor.getIdActor(), actor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actoresPeli;
    }

    public Actor getActor(int idActor) {
        HashMap<Integer, Actor> todosActores = getActores();
        return todosActores.get(idActor);
    }

    public boolean insertActor(Actor actor) {
        String sql = "INSERT INTO Actor (id, nombre, apodo) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = this.conexionBD.getConexion().prepareStatement(sql);
            statement.setInt(1, actor.getIdActor());
            statement.setString(2, actor.getNombre());
            statement.setString(3, actor.getApodo());
            if (this.conexionBD.executeUpdate(statement) > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertActor(Actor actor, int[] idPeliculas) {
        String sql1 = "INSERT INTO ActorPelicula (id_actor, id_pelicula) VALUES (?, ?)";
        String sql2 = "INSERT INTO Actor (id, nombre, apodo) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = this.conexionBD.getConexion().prepareStatement(sql2);
            statement.setInt(1, actor.getIdActor());
            statement.setString(2, actor.getNombre());
            statement.setString(3, actor.getApodo());
            statement = this.conexionBD.getConexion().prepareStatement(sql1);
            if (this.conexionBD.executeUpdate(statement) <= 0) return false;
            for (int idPel: idPeliculas) {
                statement.setInt(1, actor.getIdActor());
                statement.setInt(2, idPel);
                if (this.conexionBD.executeUpdate(statement) <= 0) return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateActor(Actor newActor) {
        String sql = "UPDATE Actor SET nombre=?, apodo=? WHERE id=?";
        try {
            PreparedStatement statement = this.conexionBD.getConexion().prepareStatement(sql);
            statement.setString(1, newActor.getNombre());
            statement.setString(2, newActor.getApodo());
            statement.setInt(3, newActor.getIdActor());
            if (this.conexionBD.executeUpdate(statement) > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteActor(int idActor) {
        String sql = "DELETE FROM Actor WHERE id=?";
        try {
            PreparedStatement statement = this.conexionBD.getConexion().prepareStatement(sql);
            statement.setInt(1, idActor);
            if (this.conexionBD.executeUpdate(statement) > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
