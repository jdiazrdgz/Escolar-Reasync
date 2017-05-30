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

    public List<Path> getSubdirectorios() {
        List<Path> pathDirectorios = new ArrayList<>();
        getDirectorios(pathDirectorios);
        pathDirectorios.forEach((pathDirectorio) -> {
            System.err.println(pathDirectorio);
        });
        return pathDirectorios;
    }

    public int getTotalArchivosMusicaDirectorioPrincipal() {
        int noArchivosMusica = 0;
        List<Path> pathDirectorios = getSubdirectorios();
        for (int i = 0; i < pathDirectorios.size(); i++) {
            noArchivosMusica += getNoArchivosMusica(pathDirectorios.get(i));
        }
        return noArchivosMusica;
    }
}
