package reasync.cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import reasync.cliente.conexion.GestorConexion;
import reasync.cliente.respuestas.EsperadorRespuestas;
import reasync.vistas.controladores.ReaSyncController;

/**
 *
 * @author jdiaz
 */
public class Client {

    private final ReaSyncController reaSyncController;
    private Socket conexion;
    private GestorConexion gestorConexion;
    private ExecutorService esperadorRespuestas;

    public Client(ReaSyncController reaSyncController) {
        this.reaSyncController = reaSyncController;
        gestorConexion = null;
    }

    public int conectarConServidor(int puerto, String host) {
        try {
            conexion = new Socket(host, puerto);
            gestorConexion = new GestorConexion(conexion);
            int error= gestorConexion.iniciarConexion();
            if(error==1){
                esperarRespuestas();
            }
            return 1;
        } catch (IOException ex) {
            return 0;
        }
    }
    
    public int desconectarConServidor() {
        int error = gestorConexion.cerrarConexion();
        if (error == 1) {
            gestorConexion = null;
            return 1;
        } else {
            return 0;
        }
    }
    public void esperarRespuestas(){
        esperadorRespuestas = Executors.newCachedThreadPool();
        esperadorRespuestas.execute(new EsperadorRespuestas(this));
    }

    public GestorConexion getGestorConexion() {
        return gestorConexion;
    }
    
}
