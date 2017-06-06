package reasync.cliente.respuestas;

import archivos.ArchivosMusica;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import reasync.cliente.Client;
import respuesta.Respuesta;

/**
 *
 * @author jdiaz
 */
public class EsperadorRespuestas implements Runnable {

    private final Client cliente;
    private ObjectInputStream ois;

    public EsperadorRespuestas(Client cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        Object objeto;
        try {
            ois = new ObjectInputStream(cliente.getGestorConexion().getIn());
            while (true) {
                objeto = (Object) ois.readObject();
                if (objeto instanceof Respuesta) {
                    System.err.println("Es respuesta");
                }
                if (objeto instanceof ArchivosMusica) {
                    cliente.getReaSyncController().mostrarMensajeLog("Registros de musica recibidos desde el Server");
                    ArchivosMusica archivosMusicaRecibidos = (ArchivosMusica) objeto;
                    cliente.getGestorSincronizacion().setArchivosMusicaRecibidos(archivosMusicaRecibidos);
                    cliente.getGestorSincronizacion().continarProcesoSincronizacion();
                    //Si el server no tiene registros lleva adentro nulo
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(EsperadorRespuestas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
