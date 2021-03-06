package reasyncserver.bd;

import archivos.ArchivoMusica;
import archivos.ArchivosMusica;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import reasyncserver.server.conexiones.clientes.Cliente;

/**
 *
 * @author jdiaz
 */
public class GestorRegistros {

    private GestorConexionBD gestorConexionBD;
    private Connection conexion;
    private Statement statement;
    private ResultSet resultset;
    private Cliente cliente;

    public GestorRegistros(Cliente cliente) {
        this.conexion = null;
        this.statement = null;
        this.resultset = null;
        this.cliente = cliente;
        try {
            gestorConexionBD = new GestorConexionBD();
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(GestorRegistros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean eliminarRegistroArchivoMusica(ArchivoMusica archivoMusica){
        try {
            conexion = gestorConexionBD.conectar();
            String pathEspecialMysql = archivoMusica.getRutaArchivo().replace("\\", "\\\\");
            statement = conexion
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "DELETE from registroarchivosmusica where path = '"+pathEspecialMysql+"';";
            statement.execute(query);
            gestorConexionBD.desconectar();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(GestorRegistros.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public int guardarRegistroArchivoMusica(ArchivoMusica archivoMusica) {
        try {
            conexion = gestorConexionBD.conectar();
            String pathEspecialMysql = archivoMusica.getRutaArchivo().replace("\\", "\\\\");
            statement = conexion
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "INSERT INTO registroarchivosmusica (nombre,peso,path)"
                    + "VALUES("
                    + "'" + archivoMusica.getNombreArchivo() + "',"
                    + "'" + archivoMusica.getPesoArchivo() + "',"
                    + "'" + pathEspecialMysql + "');";
            statement.execute(query);
            gestorConexionBD.desconectar();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(GestorRegistros.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public boolean existeRegistro(ArchivoMusica archivoMusica) {
        try {
            resultset = null;
            conexion = gestorConexionBD.conectar();
            String pathEspecialMysql = archivoMusica.getRutaArchivo().replace("\\", "\\\\");
            statement = conexion
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "select path from registroarchivosmusica where path = '"+pathEspecialMysql+"';";
            resultset = statement.executeQuery(query);
            return resultset.next();
        } catch (SQLException ex) {
            Logger.getLogger(GestorRegistros.class.getName()).log(Level.SEVERE, null, ex);
            return false;
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
        ArchivosMusica archivosMusica = new ArchivosMusica();
        try {
            resultset = null;
            conexion = gestorConexionBD.conectar();
            statement = conexion
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultset = statement.executeQuery("select * from registroarchivosmusica");
            while (resultset.next()) {
                Path localPathArchivo = Paths.get(resultset.getString("path"));
                Path generalPath = cliente.getGestorArchivosCliente()
                        .generalizarPathArchivoMusica(localPathArchivo, cliente.getDirectorio());
                archivosMusica.getArchivosMusica().add(new ArchivoMusica(
                        generalPath.toString(),
                        resultset.getString("nombre"),
                        resultset.getString("peso")));
            }
            gestorConexionBD.desconectar();
            return archivosMusica;
        } catch (SQLException ex) {
            Logger.getLogger(GestorRegistros.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
