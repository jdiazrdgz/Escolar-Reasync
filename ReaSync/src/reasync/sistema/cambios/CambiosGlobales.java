package reasync.sistema.cambios;

import archivos.ArchivosMusica;
import java.io.Serializable;

/**
 *
 * @author jdiaz
 */
public class CambiosGlobales {

    private ArchivosMusica archivosLocales_Eliminar;
    private ArchivosMusica archivosLocales_Descargar;
    private ArchivosMusica archivosRemotos_Subir;
    private ArchivosMusica archivosRemotos_Eliminar;

    public CambiosGlobales() {
    }

    public ArchivosMusica getArchivosLocales_Eliminar() {
        return archivosLocales_Eliminar;
    }

    public void setArchivosLocales_Eliminar(ArchivosMusica archivosLocales_Eliminar) {
        this.archivosLocales_Eliminar = archivosLocales_Eliminar;
    }

    public ArchivosMusica getArchivosLocales_Descargar() {
        return archivosLocales_Descargar;
    }

    public void setArchivosLocales_Descargar(ArchivosMusica archivosLocales_Descargar) {
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
