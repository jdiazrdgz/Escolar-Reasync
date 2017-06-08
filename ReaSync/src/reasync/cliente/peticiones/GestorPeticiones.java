package reasync.cliente.peticiones;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import peticion.Peticion;
import reasync.cliente.Client;

/**
 *
 * @author jdiaz
 */
public class GestorPeticiones {

    private final Client cliente;
    private ObjectOutputStream oos = null;

    public GestorPeticiones(Client cliente) {
        this.cliente = cliente;
        try {
            oos = new ObjectOutputStream(cliente.getGestorConexion().getOut());
        } catch (IOException ex) {
            Logger.getLogger(GestorPeticiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hacerPeticion(Peticion peticion) {
        try {
            oos.writeObject(peticion);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(GestorPeticiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
