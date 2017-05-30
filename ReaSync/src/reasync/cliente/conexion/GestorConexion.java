package reasync.cliente.conexion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdiaz
 */
public class GestorConexion {

    private Socket conexion;
    private OutputStream out;
    private InputStream in;

    public GestorConexion(Socket conexion) {
        this.conexion = conexion;
        
    }
    public int iniciarConexion(){
        try {
            in = conexion.getInputStream();
            out = conexion.getOutputStream();
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(GestorConexion.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int cerrarConexion() {
        try {
            out.close();
            out = null;
            in.close();
            in = null;
            conexion.close();
            conexion = null;
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(GestorConexion.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public OutputStream getOut() {
        return out;
    }

    public InputStream getIn() {
        return in;
    }
}
