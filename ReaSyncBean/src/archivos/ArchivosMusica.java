package archivos;

import java.util.List;

/**
 *
 * @author jdiaz
 */
public class ArchivosMusica {

    private List<ArchivoMusica> archivosMusica;

    public ArchivosMusica(List<ArchivoMusica> archivosMusica) {
        this.archivosMusica = archivosMusica;
    }

    public ArchivosMusica() {
    }

    public List<ArchivoMusica> getArchivosMusica() {
        return archivosMusica;
    }

    public void setArchivosMusica(List<ArchivoMusica> archivosMusica) {
        this.archivosMusica = archivosMusica;
    }

}
