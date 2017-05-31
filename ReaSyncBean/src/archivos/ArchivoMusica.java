package archivos;

import java.nio.file.Path;

/**
 *
 * @author jdiaz
 */
public class ArchivoMusica {

    private Path rutaArchivo;
    private String nombreArchivo;
    private int pesoArchivo;

    public ArchivoMusica() {
    }

    public ArchivoMusica(Path rutaArchivo, String nombreArchivo, int pesoArchivo) {
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

    public int getPesoArchivo() {
        return pesoArchivo;
    }

    public void setPesoArchivo(int pesoArchivo) {
        this.pesoArchivo = pesoArchivo;
    }

}
