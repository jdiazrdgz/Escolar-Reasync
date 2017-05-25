package reasyncserver.server.conexiones;

import java.net.Socket;
import reasyncserver.server.Server;

/**
 *
 * @author jdiaz
 */
public class Cliente implements Runnable {

    //private Server server;
    private Socket conexion;
    private int id;

    public Cliente(Socket conexion,int id) {
        this.conexion = conexion;
        this.id=id;
    }

    @Override
    public void run() {
        while (!conexion.isClosed()) {
            
        }
        //El cliente se ha desconectado
        System.out.println("El cliente "+id+" Se ha desconectado");
    }

}
