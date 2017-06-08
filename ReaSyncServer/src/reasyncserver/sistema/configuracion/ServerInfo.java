package reasyncserver.sistema.configuracion;

import java.io.Serializable;

public class ServerInfo implements Serializable{

    private String rutaDirectorioSync;
    private String nombreDirectorio;

    public ServerInfo(String rutaDirectorioSync, String nombreDirectorio) {
        this.rutaDirectorioSync = rutaDirectorioSync;
        this.nombreDirectorio = nombreDirectorio;
    }

    public ServerInfo(String rutaDirectorioSync) {
        this.rutaDirectorioSync = rutaDirectorioSync;
    }

    public String getRutaDirectorioSync() {
        return rutaDirectorioSync;
    }

    public void setRutaDirectorioSync(String rutaDirectorioSync) {
        this.rutaDirectorioSync = rutaDirectorioSync;
    }

    public String getNombreDirectorio() {
        return nombreDirectorio;
    }

    public void setNombreDirectorio(String nombreDirectorio) {
        this.nombreDirectorio = nombreDirectorio;
    }
    

}
