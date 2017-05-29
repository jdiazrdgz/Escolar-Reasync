package reasync.sistema.sync;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import reasync.cliente.Client;
import reasync.sistema.archivos.ArchivoMusica;

/**
 *
 * @author jdiaz
 */
public class GestorCambiosDirectorio {
    
    private List <ArchivoMusica> listaCambios;
    private EscaneadorDirectorio escaneadorDirectorio;
    private Client cliente;

    public GestorCambiosDirectorio(Client client) {
        
         listaCambios= new ArrayList<>();
         Path directorioSyncPrincipal = Paths.get(cliente.getReaSyncController()
                 .getGestorConfiguracion()
                 .getConfiguracion()
                 .getDirectorioSincronizacion());
         escaneadorDirectorio = new EscaneadorDirectorio(directorioSyncPrincipal);
    }
    
    
}
