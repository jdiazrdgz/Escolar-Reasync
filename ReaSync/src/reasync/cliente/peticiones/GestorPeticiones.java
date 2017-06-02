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

    public GestorPeticiones(Client cliente) {
        this.cliente = cliente;
    }
    public void hacerPeticion(Peticion peticion){
        try {
            ObjectOutputStream oos = null;
            oos= new ObjectOutputStream(cliente.getGestorConexion().getOut());
            oos.writeObject(peticion);
            oos.flush();
            System.out.println("Peticion enviada");
        } catch (IOException ex) {
            Logger.getLogger(GestorPeticiones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
