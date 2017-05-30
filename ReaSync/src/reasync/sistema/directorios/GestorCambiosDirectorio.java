package reasync.sistema.directorios;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
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
    private FileSystem sistemaFicheros;

    public GestorCambiosDirectorio(Client client) {
        sistemaFicheros=FileSystems.getDefault();
        listaCambios= new ArrayList<>();
        Path directorioSyncPrincipal = sistemaFicheros.getPath(cliente.getReaSyncController()
                 .getGestorConfiguracion()
                 .getConfiguracion()
                 .getDirectorioSincronizacion());
         escaneadorDirectorio = new EscaneadorDirectorio(directorioSyncPrincipal);
    }
    
    
}
