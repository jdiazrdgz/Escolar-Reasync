package reasync.sistema.configuracion;

import java.io.Serializable;

/**
 *
 * @author jdiaz
 */
public class Configuracion implements Serializable {

    private String directorioSincronizacion;
    private String nombreDirectorioConfiguracion;

    public Configuracion() {
        directorioSincronizacion = null;
        nombreDirectorioConfiguracion = null;
    }

    public Configuracion(String directorioSincronizacion, String nombreDirectorioConfiguracion) {
        this.directorioSincronizacion = directorioSincronizacion;
        this.nombreDirectorioConfiguracion = nombreDirectorioConfiguracion;
    }

    public String getDirectorioSincronizacion() {
        return directorioSincronizacion;
    }

    public void setDirectorioSincronizacion(String directorioSincronizacion) {
        this.directorioSincronizacion = directorioSincronizacion;
    }

    public String getNombreDirectorioConfiguracion() {
        return nombreDirectorioConfiguracion;
    }

    public void setNombreDirectorioConfiguracion(String nombreDirectorioConfiguracion) {
        this.nombreDirectorioConfiguracion = nombreDirectorioConfiguracion;
    }

}
