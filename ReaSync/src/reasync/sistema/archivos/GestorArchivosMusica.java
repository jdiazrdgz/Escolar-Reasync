package reasync.sistema.archivos;

import archivos.ArchivoMusica;
import archivos.ArchivosMusica;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import reasync.sistema.configuracion.GestorConfiguracion;

/**
 *
 * @author jdiaz
 */
public class GestorArchivosMusica {

    private final GestorConfiguracion gestorConfiguracion;

    public GestorArchivosMusica(GestorConfiguracion gestorConfiguracion) {
        this.gestorConfiguracion = gestorConfiguracion;
    }

    public ArchivosMusica fixPathArchivosMusica(ArchivosMusica archivosMusica) {
        List<ArchivoMusica> listaArchivosMusica = archivosMusica.getArchivosMusica();
        Path originalPath = null;
        Path fixedPath = null;
        String nombredirSync = gestorConfiguracion.getConfiguracion().getNombreDirectorioConfiguracion();
        for (ArchivoMusica archivoMusica : listaArchivosMusica) {
            originalPath = archivoMusica.getRutaArchivo();
            Iterator<Path> ittotal = originalPath.iterator();
            Iterator<Path> itparcial = originalPath.iterator();
            int i = 0;
            int inicio = 0;
            int fin = 0;
            while (ittotal.hasNext()) {
                fin++;
            }
            while (itparcial.hasNext()) {
                if (itparcial.next().toString().equals(nombredirSync)) {
                    break;
                } else {
                    inicio++;
                }
            }
            fixedPath = originalPath.subpath(inicio, fin);

        }
        return archivosMusica;
    }
}
