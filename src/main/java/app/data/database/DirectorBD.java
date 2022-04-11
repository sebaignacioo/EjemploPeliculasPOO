package app.data.database;

import app.models.Director;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DirectorBD {

    private ConexionBD conexionBD;

    public DirectorBD(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public HashMap<Integer, Director> getDirectores() {
        HashMap<Integer, Director> directores = new HashMap<>();
        ResultSet res = this.conexionBD.executeSelect("Director");
        try {
            while (res.next()) {
                directores.put(res.getInt("id"),
                        new Director(res.getInt("id"),
                                res.getString("nombre"),
                                res.getString("apodo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return directores;
    }

    public Director getDirector(int idDirector) {
        HashMap<Integer, Director> todosDirectores = getDirectores();
        return todosDirectores.get(idDirector);
    }

    public boolean insertDirector(Director director) {
        String sql = "INSERT INTO Director (id, nombre, apodo) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = this.conexionBD.getConexion().prepareStatement(sql);
            statement.setInt(1, director.getIdDirector());
            statement.setString(2, director.getNombre());
            statement.setString(3, director.getApodo());
            if (this.conexionBD.executeUpdate(statement) > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertDirector(Director director, int[] idPeliculas) {
        String sql = "INSERT INTO Director (id, nombre, apodo) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = this.conexionBD.getConexion().prepareStatement(sql);
            statement.setInt(1, director.getIdDirector());
            statement.setString(2, director.getNombre());
            statement.setString(3, director.getApodo());
            statement = this.conexionBD.getConexion().prepareStatement(sql);
            if (this.conexionBD.executeUpdate(statement) <= 0) return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateDirector(Director newDirector) {
        String sql = "UPDATE Director SET nombre=?, apodo=? WHERE id=?";
        try {
            PreparedStatement statement = this.conexionBD.getConexion().prepareStatement(sql);
            statement.setString(1, newDirector.getNombre());
            statement.setString(2, newDirector.getApodo());
            statement.setInt(3, newDirector.getIdDirector());
            if (this.conexionBD.executeUpdate(statement) > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteDirector(int idDirector) {
        String sql = "DELETE FROM Director WHERE id=?";
        try {
            PreparedStatement statement = this.conexionBD.getConexion().prepareStatement(sql);
            statement.setInt(1, idDirector);
            if (this.conexionBD.executeUpdate(statement) > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
