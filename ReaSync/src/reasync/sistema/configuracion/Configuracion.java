package reasync.sistema.configuracion;

import java.io.Serializable;

/**
 *
 * @author jdiaz
 */
public class Configuracion implements Serializable{
    private String directorioSincronizacion;

    public Configuracion() {
        directorioSincronizacion=null;
    }

    public String getDirectorioSincronizacion() {
        return directorioSincronizacion;
    }

    public void setDirectorioSincronizacion(String directorioSincronizacion) {
        this.directorioSincronizacion = directorioSincronizacion;
    }
    
}
