package reasyncserver.bd;

import archivos.ArchivoMusica;
import archivos.ArchivosMusica;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import reasyncserver.server.Server;

/**
 *
 * @author jdiaz
 */
public class GestorRegistros {
    private Server server;
    private GestorConexionBD gestorConexionBD;
    private Connection conexion;
    private Statement statement;
    private ResultSet resultset;

    public GestorRegistros() {
        this.conexion = null;
        this.statement = null;
        this.resultset = null;
        try {
            gestorConexionBD= new GestorConexionBD();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(GestorRegistros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int guardarRegistroArchivoMusica(ArchivoMusica archivoMusica) {
        try {
            conexion = gestorConexionBD.conectar();
            statement = conexion
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "INSERT INTO registroarchivosmusica (nombre,peso,path)"
                    + "VALUES("
                    + "'" + archivoMusica.getNombreArchivo() + "',"
                    + "'" + archivoMusica.getPesoArchivo() + "',"
                    + "'" + archivoMusica.getRutaArchivo() + "');";
            statement.execute(query);
            gestorConexionBD.desconectar();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(GestorRegistros.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int guardarRegistroArchivosMusica(ArchivosMusica archivosMusica) {
        List<ArchivoMusica> listaArchivosMusica = archivosMusica.getArchivosMusica();
        int error = 0;
        for (ArchivoMusica archivoMusica : listaArchivosMusica) {
            int errorRegistro = guardarRegistroArchivoMusica(archivoMusica);
            if (errorRegistro == 0) {
                error = -1;
                break;
            }
        }
        return error;
    }

    public ArchivosMusica getArchivosMusica() {
        ArchivosMusica archivosMusica=new ArchivosMusica();
        try {
            resultset = null;
            conexion = gestorConexionBD.conectar();
            statement = conexion
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultset = statement.executeQuery("select * registroarchivosmusica");
            while (resultset.next()) {
                archivosMusica.getArchivosMusica().add(new ArchivoMusica(
                        Paths.get(resultset.getString("path")), 
                        resultset.getString("nombre"), 
                        Integer.parseInt(resultset.getString("peso"))));
            }
            gestorConexionBD.desconectar();
            return archivosMusica;
        } catch (SQLException ex) {
            Logger.getLogger(GestorRegistros.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
