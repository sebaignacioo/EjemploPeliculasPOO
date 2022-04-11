package app.data.database;

import java.sql.*;

public class ConexionBD {

    private Connection conexion;

    /**
     * Permite construir un objeto conexion
     * @param host Host de la BD MySQL (localhost)
     * @param bd Base de datos a conectar (TestBD)
     * @param user Usuario de la BD MySQL (root)
     * @param pass Password del usuario de la BD MySQL (Vacio)
     * @throws SQLException Posibles errores de SQL
     */
    public ConexionBD(String host, String bd, String user, String pass) throws SQLException {
        String url = String.format("jdbc:mysql://%s/%s", host, bd);
        this.conexion = DriverManager.getConnection(url, user, pass);
    }

    /**
     * Permite obtener la conexion
     * @return Conexion
     */
    public Connection getConexion() {
        return conexion;
    }

    /**
     * Permite ejecutar una operacion de actualizacion de BD
     * @param ps Statement preparado para ejecutarse en la BD
     * @return Numero de filas afectadas en la operacion
     */
    public int executeUpdate(PreparedStatement ps) {
        try {
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Permite ejecutar una operacion de seleccion en la BD
     * @param tabla String con el nombre de la tabla a obtener los datos
     * @return ResultSet con el resultado de la operacion
     */
    public ResultSet executeSelect(String tabla) {
        String sql = String.format("SELECT * FROM %s", tabla);
        return executeSQL(sql);
    }

    /**
     * Permite ejecutar una query en la BD
     * @param sql String con la instrucción SQL a ejecutar
     * @return ResultSet con el resultado de la operacion
     */
    public ResultSet executeSQL(String sql) {
        try {
            Statement statament = this.conexion.createStatement();
            return statament.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
