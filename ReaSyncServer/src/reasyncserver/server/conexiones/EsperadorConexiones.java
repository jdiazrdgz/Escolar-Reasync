package reasyncserver.server.conexiones;

import reasyncserver.server.conexiones.clientes.Cliente;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import reasyncserver.server.Server;

/**
 *
 * @author Bryan Ramos
 */
public class EsperadorConexiones implements Runnable {

    private Server server;
    private final ExecutorService manejadorHilosCliente;
    private ArrayList<Cliente> clientes=new ArrayList<>();
    

    public EsperadorConexiones(Server server) {
        this.server=server;
        manejadorHilosCliente= Executors.newCachedThreadPool();
    }

    @Override
    public void run() {
        while(true){
            try {
                Socket conexion = server.getServerSocket().accept();
                int idCliente=clientes.size();
                clientes.add(new Cliente(conexion,idCliente));
                manejadorHilosCliente.execute(clientes.get(idCliente));
            } catch (IOException ex) {
                Logger.getLogger(EsperadorConexiones.class.getName()).log(Level.SEVERE, null, ex);
                manejadorHilosCliente.shutdownNow();
                break;
            }
        }
        server.getReaSyncController().mostrarError("Conexión", "Ha terminado la espera de clientes");
    }
}
