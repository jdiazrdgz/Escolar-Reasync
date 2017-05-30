/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasync.sistema.directorios;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import reasync.sistema.directorios.filtros.Filtros;

/**
 *
 * @author jdiaz
 */
public class EscaneadorDirectorio {

    private final Path directorioSyncPrincipal;

    public EscaneadorDirectorio(Path directorioSyncPrincipal) {
        this.directorioSyncPrincipal = directorioSyncPrincipal;
    }

    private int getNoArchivosMusica() {
        int noArchivosMusica = 0;
        try (DirectoryStream<Path> stream
                = Files.newDirectoryStream(directorioSyncPrincipal, Filtros.filtroArchivosMusica)) {
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
