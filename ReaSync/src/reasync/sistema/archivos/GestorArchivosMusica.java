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
            originalPath = Paths.get(archivoMusica.getRutaArchivo());
            Iterator<Path> ittotal = originalPath.iterator();
            Iterator<Path> itparcial = originalPath.iterator();
            int i = 0;
            int inicio = 0;
            int fin = 0;
            while (ittotal.hasNext()) {
                ittotal.next();
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
            archivoMusica.setRutaArchivo(fixedPath.toString());
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
        int inicio = 0;
        int fin = 0;
        while (ittotal.hasNext()) {
            ittotal.next();
            fin++;
        }
        while (itparcial.hasNext()) {
            String parcial = itparcial.next().toString();
            if (parcial.equals(nombredirSync)) {
                break;
            } else {
                inicio++;
            }
        }
        fixedPath = originalPath.subpath(inicio + 1, fin);
        return fixedPath;
    }

    public ArchivosMusica especificarPathArchivosMusica(ArchivosMusica archivosMusica) {
        List<ArchivoMusica> listaArchivosMusica = archivosMusica.getArchivosMusica();
        Path generalPath = null;
        Path localPath = null;
        String dirSync = gestorConfiguracion.getConfiguracion().getDirectorioSincronizacion();
        for (ArchivoMusica archivoMusica : listaArchivosMusica) {
            generalPath = Paths.get(archivoMusica.getRutaArchivo());
            localPath = Paths.get(dirSync,generalPath.toString());
            archivoMusica.setRutaArchivo(localPath.toString());
        }
        return archivosMusica;
    }

    public Path especificarPathArchivoMusica(Path archivoMusica) {
        Path generalPath = null;
        Path localPath = null;
        String nombreDirSync = gestorConfiguracion.getConfiguracion().getNombreDirectorioConfiguracion();
        String dirSync = gestorConfiguracion.getConfiguracion().getDirectorioSincronizacion();
        generalPath = archivoMusica;
        localPath = Paths.get(dirSync, nombreDirSync, generalPath.toString());
        return localPath;
    }

    public List<Path> obtenerPathArchivosMusica(ArchivosMusica archivosMusica) {
        List<Path> listPathArchivos = new ArrayList<>();
        archivosMusica.getArchivosMusica().forEach(archivoMusica -> {
            listPathArchivos.add(Paths.get(archivoMusica.getRutaArchivo()));
        });
        return listPathArchivos;
    }
}
