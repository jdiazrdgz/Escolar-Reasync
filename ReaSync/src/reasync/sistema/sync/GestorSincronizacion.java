package reasync.sistema.sync;

import archivos.ArchivosMusica;
import peticion.Peticion;
import reasync.cliente.Client;
import reasync.cliente.peticiones.GestorPeticiones;
import reasync.sistema.cambios.CambiosGlobales;
import reasync.sistema.cambios.CambiosLocales;

/**
 *
 * @author jdiaz
 */
public class GestorSincronizacion {

    private Client cliente;
    private ArchivosMusica archivosMusicaRecibidos;
    private CambiosLocales cambiosLocales;

    public GestorSincronizacion(Client cliente) {
        this.cliente = cliente;
        archivosMusicaRecibidos = null;
    }

    public void iniciarProcesoSincronizacion() {
        this.cambiosLocales = encontrarCambiosLocales();
        hacerPeticionRegistrosServer();
    }

    public int continarProcesoSincronizacion() {
        if (cambiosLocales != null && archivosMusicaRecibidos != null) {
            CambiosGlobales cambiosGlobales=encontrarCambiosGlobales(cambiosLocales,archivosMusicaRecibidos);
            return 1;
        } else {
            return 0;
        }
    }

    public CambiosLocales encontrarCambiosLocales() {
        return cliente.getGestorCambios().generarListaCambiosLocales();
    }

    public CambiosGlobales encontrarCambiosGlobales(CambiosLocales cambiosLocales, ArchivosMusica archivosMusicaRecibidos) {
        return cliente.getGestorCambios().generarListaCambiosGlobales(cambiosLocales,archivosMusicaRecibidos);
    }
    
    private void hacerPeticionRegistrosServer() {
        new GestorPeticiones(cliente)
                .hacerPeticion(new Peticion("registroArchivosMusica"));
        cliente.getReaSyncController()
                .mostrarMensajeLog("Petición de registros de musica enviada al servidor");
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
