package reasyncserver.bd;

import java.sql.*;

public class GestorConexionBD {
    
    private Connection conexion;
    private final String urlBD;
    private final String contrasenia;
    private final String nombreU;
	
    public GestorConexionBD(String urlBD,String contrasenia,String nombreU) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            this.urlBD=urlBD;
            this.contrasenia=contrasenia;
            this.nombreU=nombreU;
    }
    public GestorConexionBD() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            urlBD ="jdbc:mysql://127.0.0.1:3306/carrito";
            contrasenia="46143**/";
            nombreU="root";
    }

    public Connection conectar() throws SQLException {
            conexion = DriverManager.getConnection(urlBD, nombreU, contrasenia);
            return conexion;
    }
    public void desconectar() throws SQLException {
            conexion.close();
    }
}
