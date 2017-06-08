package archivos;

import java.io.Serializable;
import java.nio.file.Path;

/**
 *
 * @author jdiaz
 */
public class ArchivoMusica implements Serializable {

    private String rutaArchivo;
    private String nombreArchivo;
    private String pesoArchivo;

    public ArchivoMusica() {
    }

    public ArchivoMusica(String rutaArchivo, String nombreArchivo, String pesoArchivo) {
        this.rutaArchivo = rutaArchivo;
        this.nombreArchivo = nombreArchivo;
        this.pesoArchivo = pesoArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
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
