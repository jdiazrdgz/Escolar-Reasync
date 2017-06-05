package archivos;

import java.io.Serializable;
import java.nio.file.Path;

/**
 *
 * @author jdiaz
 */
public class ArchivoMusica implements Serializable{

    private Path rutaArchivo;
    private String nombreArchivo;
    private String pesoArchivo;

    public ArchivoMusica() {
    }

    public ArchivoMusica(Path rutaArchivo, String nombreArchivo, String pesoArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.nombreArchivo = nombreArchivo;
        this.pesoArchivo = pesoArchivo;
    }

    public Path getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(Path rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getPesoArchivo() {
        return pesoArchivo;
    }

    public void setPesoArchivo(String pesoArchivo) {
        this.pesoArchivo = pesoArchivo;
    }

}
