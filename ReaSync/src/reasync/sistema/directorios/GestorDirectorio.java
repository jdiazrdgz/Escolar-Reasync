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
public class GestorDirectorio {

    private List<ArchivoMusica> listaCambios;
    private EscaneadorDirectorio escaneadorDirectorio;
    private final Client cliente;
    private FileSystem sistemaFicheros;

    public GestorDirectorio(Client cliente) {
        this.cliente = cliente;
        iniciarParametros();
    }

    private void iniciarParametros(){
        sistemaFicheros=FileSystems.getDefault();
        listaCambios= new ArrayList<>();
        Path directorioSyncPrincipal = sistemaFicheros.getPath(cliente
                 .getGestorConfiguracion()
                 .getConfiguracion()
                 .getDirectorioSincronizacion());
         escaneadorDirectorio = new EscaneadorDirectorio(directorioSyncPrincipal);
    }

    public EscaneadorDirectorio getEscaneadorDirectorio() {
        return escaneadorDirectorio;
    }

}
