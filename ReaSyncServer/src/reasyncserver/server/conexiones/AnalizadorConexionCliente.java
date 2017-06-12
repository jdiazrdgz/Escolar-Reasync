package reasyncserver.server.conexiones;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import reasyncserver.server.conexiones.clientes.Cliente;
import reasyncserver.server.conexiones.clientes.info.ClienteConectado;

/**
 *
 * @author jdiaz
 */
public class AnalizadorConexionCliente implements Runnable {

    private EsperadorConexiones esperadorConexiones;

    public AnalizadorConexionCliente(EsperadorConexiones esperadorConexiones) {
        this.esperadorConexiones = esperadorConexiones;
    }

    @Override
    public void run() {
        ArrayList<Cliente> clientes;
        while (true) {
            try {
                System.err.println("checando clientes");
                clientes = esperadorConexiones.getClientes();
                if (!clientes.isEmpty()) {
                    Iterator<Cliente> itClientes = clientes.iterator();
                    Cliente cliente;
                    while (itClientes.hasNext()) {
                        cliente = itClientes.next();
                        if (cliente.isAlive()) {
                            System.err.println("esta conectado");
                            esperadorConexiones
                                    .getServer().getReaSyncController()
                                    .actualizarClienteTabla(new ClienteConectado(Integer.toString(cliente.getId()), cliente.getIp(), cliente.getNombreUsuario()));
                        } else {
                            System.err.println("no esta conectado");
                            /*esperadorConexiones
                                    .getServer().getReaSyncController()
                                    .eliminarClienteTabla(new ClienteConectado(Integer.toString(cliente.getId()), cliente.getIp(), cliente.getNombreUsuario()));*/
                            //esperadorConexiones.eliminarClienteLista(cliente.getId());
                        }
                    }
                }
                sleep(10000);
                }catch (InterruptedException ex) {
                Logger.getLogger(AnalizadorConexionCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }

    }
