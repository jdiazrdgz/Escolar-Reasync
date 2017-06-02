package reasync.sistema.sync;

import archivos.ArchivosMusica;
import peticion.Peticion;
import reasync.cliente.Client;
import reasync.cliente.peticiones.GestorPeticiones;

/**
 *
 * @author jdiaz
 */
public class GestorSincronizacion {

    private Client cliente;
    private ArchivosMusica archivosMusicaRecibidos;
    

    public GestorSincronizacion(Client cliente) {
        this.cliente = cliente;
        archivosMusicaRecibidos=null;
    }

    public void hacerSincronizacionManual() {
        //pedir lista de archivos a server
        new GestorPeticiones(cliente)
                .hacerPeticion(new Peticion("registroArchivosMusica"));
        while(archivosMusicaRecibidos==null){
            if(archivosMusicaRecibidos!=null) break;
        }
        
        
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public ArchivosMusica getArchivosMusicaRecibidos() {
        return archivosMusicaRecibidos;
    }

    public void setArchivosMusicaRecibidos(ArchivosMusica archivosMusicaRecibidos) {
        this.archivosMusicaRecibidos = archivosMusicaRecibidos;
    }
    
    
}
