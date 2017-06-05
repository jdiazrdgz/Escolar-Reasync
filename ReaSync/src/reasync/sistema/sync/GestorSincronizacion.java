package reasync.sistema.sync;

import archivos.ArchivosMusica;
import peticion.Peticion;
import reasync.cliente.Client;
import reasync.cliente.peticiones.GestorPeticiones;
import reasync.sistema.cambios.Cambios;
import reasync.sistema.cambios.GestorCambios;

/**
 *
 * @author jdiaz
 */
public class GestorSincronizacion {

    private Client cliente;
    private ArchivosMusica archivosMusicaRecibidos;

    public GestorSincronizacion(Client cliente) {
        this.cliente = cliente;
        archivosMusicaRecibidos = null;
    }

    public void hacerSincronizacion() {
        Cambios cambios = cliente.getGestorCambios().generarListaCambios();
        //pedir lista de archivos a server
        new GestorPeticiones(cliente)
                .hacerPeticion(new Peticion("registroArchivosMusica"));
        cliente.getReaSyncController()
                .mostrarMensajeLog("Petición de registros de musica enviada al servidor");
    }
    public void encontrarDiferencias(){
        
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
