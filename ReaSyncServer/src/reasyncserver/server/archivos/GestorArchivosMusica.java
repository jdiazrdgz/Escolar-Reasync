package reasyncserver.server.archivos;

import archivos.ArchivoMusica;
import java.nio.file.Path;
import java.nio.file.Paths;
import reasyncserver.sistema.configuracion.GestorConfiguracion;

/**
 *
 * @author jdiaz
 */
public class GestorArchivosMusica {
    
    private final GestorConfiguracion gestorConfiguracion;

    public GestorArchivosMusica(GestorConfiguracion gestorConfiguracion) {
        this.gestorConfiguracion = gestorConfiguracion;
    }
    
    
    public Path especificarPathArchivoMusica(Path pathArchivo) {
        Path generalPath = pathArchivo;
        Path localPath = Paths.get(gestorConfiguracion.getConfiguracion()
                .getServerInfo()
                .getRutaDirectorioSync());
        return localPath;
    }
    
}
