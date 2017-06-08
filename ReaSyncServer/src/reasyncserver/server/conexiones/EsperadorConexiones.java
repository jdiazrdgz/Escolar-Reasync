package reasyncserver.server.conexiones;

import reasyncserver.server.conexiones.clientes.Cliente;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import reasyncserver.server.Server;
import reasyncserver.server.conexiones.clientes.info.ClienteConectado;

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
                String ipCliente=(((InetSocketAddress)conexion.getRemoteSocketAddress()).getAddress()).toString().replace("/","");
                server.getReaSyncController()
                        .mostrarClienteTabla(new ClienteConectado(Integer.toString(idCliente),ipCliente, "Identificando"));
                server.getReaSyncController().mostrarMensajeLog("Se ha conectado el cliente no: "+idCliente);
                clientes.add(new 
                            Cliente(conexion,
                                    idCliente, 
                                    Paths.get(server.getGestorConfigurcion()
                                            .getConfiguracion()
                                            .getServerInfo()
                                            .getRutaDirectorioSync())));
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
