package reasync.sistema.cambios;

import archivos.ArchivosMusica;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author jdiaz
 */
public class CambiosGlobales {

    private List<Path> archivosLocales_Eliminar;
    private List<Path> archivosLocales_Descargar;
    private ArchivosMusica archivosRemotos_Subir;
    private ArchivosMusica archivosRemotos_Eliminar;

    public CambiosGlobales() {
    }

    public CambiosGlobales(List<Path> archivosLocales_Eliminar, List<Path> archivosLocales_Descargar, ArchivosMusica archivosRemotos_Subir, ArchivosMusica archivosRemotos_Eliminar) {
        this.archivosLocales_Eliminar = archivosLocales_Eliminar;
        this.archivosLocales_Descargar = archivosLocales_Descargar;
        this.archivosRemotos_Subir = archivosRemotos_Subir;
        this.archivosRemotos_Eliminar = archivosRemotos_Eliminar;
    }
    
    public List<Path> getArchivosLocales_Eliminar() {
        return archivosLocales_Eliminar;
    }

    public void setArchivosLocales_Eliminar(List<Path> archivosLocales_Eliminar) {
        this.archivosLocales_Eliminar = archivosLocales_Eliminar;
    }

    public List<Path> getArchivosLocales_Descargar() {
        return archivosLocales_Descargar;
    }

    public void setArchivosLocales_Descargar(List<Path> archivosLocales_Descargar) {
        this.archivosLocales_Descargar = archivosLocales_Descargar;
    }

    

    public ArchivosMusica getArchivosRemotos_Subir() {
        return archivosRemotos_Subir;
    }

    public void setArchivosRemotos_Subir(ArchivosMusica archivosRemotos_Subir) {
        this.archivosRemotos_Subir = archivosRemotos_Subir;
    }

    public ArchivosMusica getArchivosRemotos_Eliminar() {
        return archivosRemotos_Eliminar;
    }

    public void setArchivosRemotos_Eliminar(ArchivosMusica archivosRemotos_Eliminar) {
        this.archivosRemotos_Eliminar = archivosRemotos_Eliminar;
    }
    
    
}
