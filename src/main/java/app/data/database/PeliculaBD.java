package app.data.database;

import app.models.Pelicula;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PeliculaBD {

    private ConexionBD conexionBD;
    private DirectorBD directorBD;
    private ActorBD actorBD;

    public PeliculaBD(ConexionBD conexionBD) {
        this.conexionBD = conexionBD;
        this.directorBD = new DirectorBD(conexionBD);
        this.actorBD = new ActorBD(conexionBD);
    }

    public ArrayList<Pelicula> getPeliculas() {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        ResultSet res = this.conexionBD.executeSelect("Pelicula");
        try {
            while (res.next()) {
                peliculas.add(new Pelicula(res.getInt("id"),
                                           this.directorBD.getDirector(res.getInt("id_director")),
                                           this.actorBD.getActores(res.getInt("id")),
                                           res.getString("titulo_original"),
                                           res.getString("titulo_espanol"),
                                           res.getInt("anno")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peliculas;
    }

    public Pelicula getPelicula(int idPelicula) {
        ArrayList<Pelicula> todosPeliculas = getPeliculas();
        for (Pelicula peli: todosPeliculas) {
            if (peli.getIdPelicula() == idPelicula) return peli;
        }
        return null;
    }

    private int getIDUltimaPelicula() throws SQLException {
        ResultSet res = this.conexionBD.executeSQL("SELECT * from Pelicula ORDER BY id DESC LIMIT 1");
        res.next();
        return res.getInt("id");
    }

    public boolean insertPelicula(Pelicula pelicula) {
        String sql = "INSERT INTO Pelicula (id_director, titulo_original, titulo_espanol, anno) VALUES (?, ?, " +
                "?, ?)";
        try {
            PreparedStatement statement = this.conexionBD.getConexion().prepareStatement(sql);
            statement.setInt(1, pelicula.getDirector().getIdDirector());
            statement.setString(2, pelicula.getNombreOriginal());
            statement.setString(3, pelicula.getNombreEspanol());
            statement.setInt(4, pelicula.getAnno());
            pelicula.setIdPelicula(getIDUltimaPelicula());
            if (this.conexionBD.executeUpdate(statement) > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePelicula(Pelicula newPelicula) {
        String sql = "UPDATE Pelicula SET id_director=?, titulo_original=?, titulo_espanol=?, anno=? WHERE id=?";
        try {
            PreparedStatement statement = this.conexionBD.getConexion().prepareStatement(sql);
            statement.setInt(1, newPelicula.getDirector().getIdDirector());
            statement.setString(2, newPelicula.getNombreOriginal());
            statement.setString(3, newPelicula.getNombreEspanol());
            statement.setInt(4, newPelicula.getAnno());
            statement.setInt(5, newPelicula.getIdPelicula());
            if (this.conexionBD.executeUpdate(statement) > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePelicula(int idPelicula) {
        String sql = "DELETE FROM Pelicula WHERE id=?";
        try {
            PreparedStatement statement = this.conexionBD.getConexion().prepareStatement(sql);
            statement.setInt(1, idPelicula);
            if (this.conexionBD.executeUpdate(statement) > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
