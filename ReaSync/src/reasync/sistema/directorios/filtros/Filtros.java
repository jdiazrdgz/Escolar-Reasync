package reasync.sistema.directorios.filtros;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.DirectoryStream;
import static java.nio.file.Files.newDirectoryStream;
import java.nio.file.Path;
import org.apache.commons.io.FilenameUtils;
import reasync.sistema.archivos.FormatoMusica;

/**
 *
 * @author jdiaz
 */
public class Filtros {

    public static DirectoryStream.Filter<Path> filtroArchivosMusica;

    /*public FileFilter getFiltroArchivosMusica() {
        filtroArchivosMusica = (File archivoMusica) -> 
                FormatoMusica.find(FilenameUtils.getExtension(archivoMusica.getName()));
        return filtroArchivosMusica;
    }*/
    public DirectoryStream.Filter<Path> getFiltro() {
        filtroArchivosMusica = (Path archivoMusica) -> {
            String extension = FilenameUtils.getExtension(archivoMusica.getFileName().toString());
            return FormatoMusica.find(extension);
        };
        return filtroArchivosMusica;
    }
}
