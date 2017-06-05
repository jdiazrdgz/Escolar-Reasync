package reasync.sistema.directorios;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import reasync.cliente.Client;

/**
 *
 * @author jdiaz
 */
public class GestorDirectorio {

    private Path directorioSyncPrincipal;
    private EscaneadorDirectorio escaneadorDirectorio;
    private final Client cliente;
    private FileSystem sistemaFicheros;

    public GestorDirectorio(Client cliente) {
        this.cliente = cliente;
        iniciarParametros();
    }

    private void iniciarParametros() {
        sistemaFicheros = FileSystems.getDefault();
        directorioSyncPrincipal = sistemaFicheros.getPath(cliente
                .getGestorConfiguracion()
                .getConfiguracion()
                .getDirectorioSincronizacion());
        escaneadorDirectorio = new EscaneadorDirectorio(directorioSyncPrincipal);
    }

    public EscaneadorDirectorio getEscaneadorDirectorio() {
        return escaneadorDirectorio;
    }



}
