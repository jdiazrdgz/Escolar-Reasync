package reasync.sistema.cambios;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author jdiaz
 */
public class CambiosLocales implements Serializable{
    private List<Path> archivosEliminados;
    private List<Path> archivosSimilares;
    private List<Path> archivosNuevos;

    public CambiosLocales(List<Path> archivosEliminados, List<Path> archivosSimilares, List<Path> archivosNuevos) {
        this.archivosEliminados = archivosEliminados;
        this.archivosSimilares = archivosSimilares;
        this.archivosNuevos = archivosNuevos;
    }

    public CambiosLocales(List<Path> archivosSimilares) {
        this.archivosSimilares = archivosSimilares;
    }
    
    public CambiosLocales() {
    }
    

    public List<Path> getArchivosEliminados() {
        return archivosEliminados;
    }

    public void setArchivosEliminados(List<Path> archivosEliminados) {
        this.archivosEliminados = archivosEliminados;
    }

    public List<Path> getArchivosSimilares() {
        return archivosSimilares;
    }

    public void setArchivosSimilares(List<Path> archivosSimilares) {
        this.archivosSimilares = archivosSimilares;
    }

    public List<Path> getArchivosNuevos() {
        return archivosNuevos;
    }

    public void setArchivosNuevos(List<Path> archivosNuevos) {
        this.archivosNuevos = archivosNuevos;
    }
    
    
}
