package reasync.cliente;

import java.io.IOException;
import java.net.Socket;
import reasync.cliente.conexion.GestorConexion;
import reasync.vistas.controladores.ReaSyncController;

/**
 *
 * @author jdiaz
 */
public class Client {

    private final ReaSyncController reaSyncController;
    private Socket conexion;
    private GestorConexion gestorConexion;

    public Client(ReaSyncController reaSyncController) {
        this.reaSyncController = reaSyncController;
        gestorConexion = null;
    }

    public int conectarConServidor(int puerto, String host) {
        try {
            conexion = new Socket(host, puerto);
            gestorConexion = new GestorConexion(conexion);
            return 1;
        } catch (IOException ex) {
            return 0;
        }
    }

    public void desconectarConServidor() {
        int error=gestorConexion.cerrarConexion();
        if(error==1){
            gestorConexion=null;
        }else{
            reaSyncController.mostrarError("Conexión", "Ocurrio un error al cerrar la conexión con el servidor");
        }
        System.gc();
    }
}
