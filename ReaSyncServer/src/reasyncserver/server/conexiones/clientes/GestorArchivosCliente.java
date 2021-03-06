package reasyncserver.server.conexiones.clientes;

import java.nio.file.Path;
import java.util.Iterator;

/**
 *
 * @author jdiaz
 */
public class GestorArchivosCliente {

    private final Cliente cliente;

    public GestorArchivosCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Path generalizarPathArchivoMusica(Path archivoMusica, Path dirSyncLocal) {
        System.err.println(archivoMusica.toString() + "generalizar archivo musica");
        System.err.println(dirSyncLocal.toString() + "generalizar dir local");
        Path originalPath = null;
        Path fixedPath = null;
        String nombredirSync = dirSyncLocal.getFileName().toString();
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
}
