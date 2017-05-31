package reasync.sistema.directorios;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import reasync.cliente.Client;
import archivos.ArchivoMusica;

/**
 *
 * @author jdiaz
 */
public class GestorDirectorio {

    private List<ArchivoMusica> listaCambios;
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
        listaCambios = new ArrayList<>();
        directorioSyncPrincipal = sistemaFicheros.getPath(cliente
                .getGestorConfiguracion()
                .getConfiguracion()
                .getDirectorioSincronizacion());
        escaneadorDirectorio = new EscaneadorDirectorio(directorioSyncPrincipal);
    }

    public EscaneadorDirectorio getEscaneadorDirectorio() {
        return escaneadorDirectorio;
    }

    public List<ArchivoMusica> getListaCambios() {
        return listaCambios;
    }

}
