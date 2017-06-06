package reasyncserver.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import reasyncserver.server.conexiones.EsperadorConexiones;
import reasyncserver.vistas.controles.ReaSyncController;

public class Server {

    private ServerSocket serverSocket;
    private int puerto;
    private final ReaSyncController reaSyncController;
    private ExecutorService esperadorConexiones;

    public Server(ReaSyncController reaSyncController) {
        this.reaSyncController = reaSyncController;
        serverSocket = null;
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

    public int cerrarServidor() {
        try {
            this.serverSocket.close();
            serverSocket = null;
            System.gc();
            return 1;
        } catch (IOException ex) {
            return 0;
        }
    }

    public boolean isLocalPortInUse(int port) { //14147
        try {
            // ServerSocket try to open a LOCAL port
            new ServerSocket(port).close();
            // local port can be opened, it's available
            return false;
        } catch (IOException e) {
            // local port cannot be opened, it's in use
            return true;
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

    public String getPublicIP() {
        try {
            String ip = null;
            InetAddress IP = InetAddress.getLocalHost();
            return IP.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public ReaSyncController getReaSyncController() {
        return reaSyncController;
    }

}
