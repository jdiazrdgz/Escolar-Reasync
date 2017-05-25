package reasync.cliente.conexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdiaz
 */
public class GestorConexion {

    private Socket conexion;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public GestorConexion(Socket conexion) {
        this.conexion = conexion;
        try {
            out = new ObjectOutputStream(conexion.getOutputStream());
            in = new ObjectInputStream(conexion.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(GestorConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int cerrarConexion(){
        try {
            out.close();
            out=null;
            in.close();
            in=null;
            conexion.close();
            conexion=null;
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(GestorConexion.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public ObjectOutputStream getOut() {
        return out;
    }

    public ObjectInputStream getIn() {
        return in;
    }
}
