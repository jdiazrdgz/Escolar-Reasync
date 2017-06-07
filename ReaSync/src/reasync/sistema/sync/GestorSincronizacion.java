package reasync.sistema.sync;

import archivos.ArchivoMusica;
import archivos.ArchivosMusica;
import java.nio.file.Path;
import java.util.List;
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

    public void continarProcesoSincronizacion() {
        if (cambiosLocales != null && archivosMusicaRecibidos != null) {
            CambiosGlobales cambiosGlobales = encontrarCambiosGlobales(cambiosLocales, archivosMusicaRecibidos);
            if (cambiosGlobales == null) {
                //nada que sincronizar
                cliente.getReaSyncController().mostrarMensajeLog("Nada que sincronizar");
                System.err.println("Nada que sincronizar");
            } else {
                ejecutarAccionesSincronizacion(cambiosGlobales);
            }
        } else {
            System.err.println("error recibiendo archivos de musica o generando los cambios locales");
        }
    }

    public void ejecutarAccionesSincronizacion(CambiosGlobales cambiosGlobales) {
        determinarAccionesLocales(cambiosGlobales);
        determinarAccionesRemotas(cambiosGlobales);
    }

    public void determinarAccionesLocales(CambiosGlobales cambiosGlobales) {
        if (cambiosGlobales.getArchivosLocales_Descargar() != null) {
            cliente.getReaSyncController()
                    .mostrarMensajeLog("Se han encontrado archivos que descargar desde el servidor");
            descargarArchivosMusica(cambiosGlobales.getArchivosLocales_Descargar());
        }
        if (cambiosGlobales.getArchivosLocales_Eliminar() != null) {
            cliente.getReaSyncController()
                    .mostrarMensajeLog("Se han encontrado archivos que se deben eliminar localmente");
            eliminarArchivosMusicaLocales(cambiosGlobales.getArchivosLocales_Eliminar());
        }
    }

    public void descargarArchivosMusica(List<Path> pathArchivosMusica) {
        System.err.println("Se descargaran los siguientes archivos");
        pathArchivosMusica.forEach(path -> System.err.println(path.toString()));
        cliente.getGestorFTP().conectarClienteFTP();
        pathArchivosMusica.forEach(archivo -> {
            cliente.getGestorFTP().descargarArchivo(archivo);
        });
        cliente.getGestorFTP().desconectarClienteFTP();
    }

    public void eliminarArchivosMusicaLocales(List<Path> pathArchivosMusica) {
        System.err.println("Se eliminaran los siguientes archivos");
        pathArchivosMusica.forEach(path -> System.err.println(path.toString()));
    }

    public void determinarAccionesRemotas(CambiosGlobales cambiosGlobales) {
        if (cambiosGlobales.getArchivosRemotos_Eliminar() != null) {
            cliente.getReaSyncController()
                    .mostrarMensajeLog("Se han encontrado archivos en el servidor que se deben eliminar");
            eliminarArchivosMusicaRemotos(cambiosGlobales.getArchivosRemotos_Eliminar().getArchivosMusica());
        }
        if (cambiosGlobales.getArchivosRemotos_Subir() != null) {
            cliente.getReaSyncController()
                    .mostrarMensajeLog("Se han encontrado archivos que se deben subir al servidor");
            subirArchivosMusica(cambiosGlobales.getArchivosRemotos_Subir().getArchivosMusica());
        }
    }

    public void subirArchivosMusica(List<ArchivoMusica> archivosMusica) {
        System.err.println("Se subiran los siguientes archivos al servidor");
        //archivosMusica.forEach(archivoMusica -> System.err.println(archivoMusica.getRutaArchivo().toString()));
        if (cliente.getGestorFTP().conectarClienteFTP() == 1) {
            archivosMusica.forEach(archivo -> {
                cliente.getGestorFTP().subirArchivo(archivo.getRutaArchivo());
            });
            cliente.getGestorFTP().desconectarClienteFTP();
        }else{
            
        }
        
    }

    public void eliminarArchivosMusicaRemotos(List<ArchivoMusica> archivosMusica) {
        System.err.println("Se eliminaran los siguientes archivos del servidor");
        archivosMusica.forEach(archivoMusica -> System.err.println(archivoMusica.getRutaArchivo().toString()));
    }

    public CambiosLocales encontrarCambiosLocales() {
        return cliente.getGestorCambios().generarListaCambiosLocales();
    }

    public CambiosGlobales encontrarCambiosGlobales(CambiosLocales cambiosLocales, ArchivosMusica archivosMusicaRecibidos) {
        return cliente.getGestorCambios().generarListaCambiosGlobales(cambiosLocales, archivosMusicaRecibidos);
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
