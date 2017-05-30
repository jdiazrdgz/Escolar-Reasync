package reasync.cliente.respuestas;

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
                objeto = (Object) cliente.getGestorConexion().getIn();
                if (objeto instanceof Respuesta) {
                    
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(EsperadorRespuestas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}