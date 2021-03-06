package reasync.sistema.directorios;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jdiaz
 */
public class EscaneadorDirectorio {

    private final Path directorioSyncPrincipal;

    public EscaneadorDirectorio(Path directorioSyncPrincipal) {
        this.directorioSyncPrincipal = directorioSyncPrincipal;
    }

    public List<Path> obtenerPathArchivos(List<Path> arbolDirectorios) {
        List<Path> archivos = new ArrayList<>();
        arbolDirectorios.forEach((directorio) -> {
            obtenerArchivosDirectorio(directorio,archivos);
        });
        return archivos;
    }

    public List<Path> obtenerArbolDirectorios() {
        List<Path> pathDirectorios = new ArrayList<>();
        getDirectorios(pathDirectorios);
        return pathDirectorios;
    }

    public void obtenerArchivosDirectorio(Path directorio,List<Path> archivos) {
        try (DirectoryStream<Path> stream
                = Files.newDirectoryStream(directorio, "*.{mp3,wav,ogg,midi}")) {
            for (Path entry : stream) {
                archivos.add(entry);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public void getDirectorios(List<Path> pathDirectorios) {
        try (DirectoryStream<Path> stream
                = Files.newDirectoryStream(directorioSyncPrincipal)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    pathDirectorios.add(entry);
                    getDirectorios(entry, pathDirectorios);
                }
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public void getDirectorios(Path directorio, List<Path> pathDirectorios) {
        try (DirectoryStream<Path> stream
                = Files.newDirectoryStream(directorio)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    pathDirectorios.add(entry);
                    getDirectorios(entry, pathDirectorios);
                }
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public int getTotalArchivosMusicaDirectorioPrincipal() {
        int noArchivosMusica = 0;
        List<Path> pathDirectorios = obtenerArbolDirectorios();
        for (int i = 0; i < pathDirectorios.size(); i++) {
            noArchivosMusica += getNoArchivosMusica(pathDirectorios.get(i));
        }
        return noArchivosMusica;
    }

    public int getNoArchivosMusica(Path directorio) {
        int noArchivosMusica = 0;
        try (DirectoryStream<Path> stream
                = Files.newDirectoryStream(directorio, "*.{mp3,wav,ogg,midi}")) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
                noArchivosMusica++;
            }
            return noArchivosMusica;
        } catch (IOException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can // only be thrown by newDirectoryStream.
            System.err.println(x);
            return -1;
        }
    }
}
