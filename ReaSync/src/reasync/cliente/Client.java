package reasync.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import reasync.vistas.controladores.ReaSyncController;

/**
 *
 * @author jdiaz
 */
public class Client {

    private final ReaSyncController reaSyncController;
    private Socket conexion;

    public Client(ReaSyncController reaSyncController) {
        this.reaSyncController = reaSyncController;
    }

    public int conectarConServidor(int puerto, String host) {
        try {
            conexion = new Socket(host, puerto);
            return 1;
        } catch (IOException ex) {
            return 0;
        }
    }

    public void desconectarConServidor() {
        try {
            conexion.close();
            conexion = null;
            System.gc();
            System.exit(0);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
