package reasync.cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import reasync.cliente.conexion.GestorConexion;
import reasync.cliente.peticiones.GestorPeticiones;
import reasync.cliente.respuestas.EsperadorRespuestas;
import reasync.ftp.GestorFTP;
import reasync.sistema.cambios.GestorCambios;
import reasync.sistema.configuracion.GestorConfiguracion;
import reasync.sistema.directorios.GestorDirectorio;
import reasync.sistema.sync.GestorSincronizacion;
import reasync.sistema.sync.SincronizadorAutomatico;
import reasync.vistas.controladores.ReaSyncController;

/**
 *
 * @author jdiaz
 */
public class Client {

    private final ReaSyncController reaSyncController;
    private Socket conexion;
    private GestorConexion gestorConexion;
    private ExecutorService ejecutorEsperadorRespuestas;
    private ExecutorService sincronizadorAutomatico;
    private final GestorDirectorio gestorDirectorio;
    private final GestorConfiguracion gestorConfiguracion;
    private final GestorSincronizacion gestorSincronizacion;
    private final GestorCambios gestorCambios;
    private GestorFTP gestorFTP;
    private GestorPeticiones gestorPeticiones;

    public Client(ReaSyncController reaSyncController) {
        this.reaSyncController = reaSyncController;
        gestorConexion = null;
        gestorConfiguracion = new GestorConfiguracion();
        gestorDirectorio = new GestorDirectorio(this);
        gestorSincronizacion = new GestorSincronizacion(this);
        gestorCambios = new GestorCambios(this);
    }

    public int conectarConServidor(int puerto, String host) {
        try {
            conexion = new Socket(host, puerto);
            gestorConexion = new GestorConexion(conexion);
            int error = gestorConexion.iniciarConexion();
            if (error == 1) {
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

    public void esperarRespuestas() {
        ejecutorEsperadorRespuestas = Executors.newCachedThreadPool();
        ejecutorEsperadorRespuestas.execute(new EsperadorRespuestas(this));
    }

    public void iniciarSincronizacionAutomatica() {
        sincronizadorAutomatico = Executors.newCachedThreadPool();
        sincronizadorAutomatico.execute(new SincronizadorAutomatico(this));
    }

    public void detenerSincronizacionAutomatica() {
        sincronizadorAutomatico.shutdownNow();
    }

    public GestorConexion getGestorConexion() {
        return gestorConexion;
    }

    public ReaSyncController getReaSyncController() {
        return reaSyncController;
    }

    public GestorDirectorio getGestorDirectorio() {
        return gestorDirectorio;
    }

    public GestorConfiguracion getGestorConfiguracion() {
        return gestorConfiguracion;
    }

    public GestorSincronizacion getGestorSincronizacion() {
        return gestorSincronizacion;
    }

    public GestorCambios getGestorCambios() {
        return gestorCambios;
    }

    public void iniciarGestorFTP(String ipServer, int port) {
        gestorFTP = new GestorFTP(this, ipServer, port);
    }

    public void terminarGestorFTP() {
        gestorFTP = null;
    }

    public GestorFTP getGestorFTP() {
        return gestorFTP;
    }

    public void iniciarGestorPeticiones() {
        gestorPeticiones = new GestorPeticiones(this);
    }

    public GestorPeticiones getGestorPeticiones() {
        return gestorPeticiones;
    }

}
