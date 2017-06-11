package reasyncserver.server.conexiones.clientes.respuesta;

import archivos.ArchivosMusica;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import reasyncserver.server.conexiones.clientes.Cliente;
import respuesta.Respuesta;

/**
 *
 * @author jdiaz
 */
public class GestorRespuestas {

    private Cliente cliente;
    private ObjectOutputStream out;

    public GestorRespuestas(Cliente cliente) {
        try {
            this.cliente = cliente;
            out=new ObjectOutputStream(cliente.getOut());
        } catch (IOException ex) {
            Logger.getLogger(GestorRespuestas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int enviarArchivosMusicaRegistrados(ArchivosMusica archivosMusica) {
        try {
            out.writeObject(archivosMusica);
            out.flush();
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(GestorRespuestas.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public int enviarRespuesta(Respuesta respuesta){
        try {
            out.writeObject(respuesta);
            out.flush();
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(GestorRespuestas.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
