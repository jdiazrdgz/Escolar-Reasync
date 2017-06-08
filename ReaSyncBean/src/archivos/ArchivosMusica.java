package archivos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jdiaz
 */
public class ArchivosMusica implements Serializable{

    private List<ArchivoMusica> archivosMusica;

    public ArchivosMusica(List<ArchivoMusica> archivosMusica) {
        this.archivosMusica = archivosMusica;
    }

    public ArchivosMusica() {
        archivosMusica = new ArrayList<>();
    }

    public List<ArchivoMusica> getArchivosMusica() {
        return archivosMusica;
    }

    public void setArchivosMusica(List<ArchivoMusica> archivosMusica) {
        this.archivosMusica = archivosMusica;
    }

}
