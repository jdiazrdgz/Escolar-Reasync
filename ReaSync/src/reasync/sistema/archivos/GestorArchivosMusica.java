package reasync.sistema.archivos;

import archivos.ArchivoMusica;
import archivos.ArchivosMusica;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    public ArchivosMusica generalizarPathArchivosMusica(ArchivosMusica archivosMusica) {
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
            archivoMusica.setRutaArchivo(fixedPath);
        }
        return archivosMusica;
    }

    public Path generalizarPathArchivoMusica(Path archivoMusica) {
        Path originalPath = null;
        Path fixedPath = null;
        String nombredirSync = gestorConfiguracion.getConfiguracion().getNombreDirectorioConfiguracion();
        originalPath = archivoMusica;
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
        return fixedPath = originalPath.subpath(inicio, fin);
    }

    public ArchivosMusica especificarPathArchivosMusica(ArchivosMusica archivosMusica) {
        List<ArchivoMusica> listaArchivosMusica = archivosMusica.getArchivosMusica();
        Path generalPath = null;
        Path localPath = null;
        String localdirSync = gestorConfiguracion.getConfiguracion().getNombreDirectorioConfiguracion();
        for (ArchivoMusica archivoMusica : listaArchivosMusica) {
            generalPath = archivoMusica.getRutaArchivo();
            localPath = Paths.get(localdirSync, generalPath.toString());
            archivoMusica.setRutaArchivo(localPath);
        }
        return archivosMusica;
    }

    public Path especificarPathArchivoMusica(Path archivoMusica) {
        Path generalPath = null;
        Path localPath = null;
        String localdirSync = gestorConfiguracion.getConfiguracion().getNombreDirectorioConfiguracion();
        generalPath = archivoMusica;
        return localPath = Paths.get(localdirSync, generalPath.toString());
    }

    public List<Path> obtenerPathArchivosMusica(ArchivosMusica archivosMusica) {
        List<Path> listPathArchivos = new ArrayList<>();
        archivosMusica.getArchivosMusica().forEach(archivoMusica -> {
            listPathArchivos.add(archivoMusica.getRutaArchivo());
        });
        return listPathArchivos;
    }
}
