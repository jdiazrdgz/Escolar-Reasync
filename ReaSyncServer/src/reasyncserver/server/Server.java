package reasyncserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import reasyncserver.server.conexiones.EsperadorConexiones;
import reasyncserver.vistas.controles.ReaSyncController;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int puerto;
    private final ReaSyncController control;
    private ExecutorService esperadorConexiones;

    public Server(ReaSyncController control) {
        this.control = control;
        serverSocket = null;
        clientSocket = null;
        esperadorConexiones = null;
        puerto = 0;
    }

    public int iniciarServidor(int puerto) {
        this.puerto = puerto;
        try {
            serverSocket = new ServerSocket(puerto);// se crea el server
            esperarConexiones();
            return 1;
        } catch (IOException e) {
            serverSocket = null;
            return 0;
        }
    }
    
    public void esperarConexiones() {
        esperadorConexiones = Executors.newCachedThreadPool();
        esperadorConexiones.execute(new EsperadorConexiones(this));
    }

    public void terminarEsperaConexion() {
        esperadorConexiones.shutdown();
        esperadorConexiones = null;
        System.gc();
    }
}
