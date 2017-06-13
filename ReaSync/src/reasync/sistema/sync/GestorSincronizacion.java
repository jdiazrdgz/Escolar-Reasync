package reasync.sistema.sync;

import archivos.ArchivoMusica;
import archivos.ArchivosMusica;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import peticion.Peticion;
import reasync.cliente.Client;
import reasync.sistema.archivos.GestorArchivosMusica;
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
                if (cambiosGlobales.getArchivosLocales_Descargar() == null
                        && cambiosGlobales.getArchivosLocales_Eliminar() == null
                        && cambiosGlobales.getArchivosRemotos_Eliminar() == null
                        && cambiosGlobales.getArchivosRemotos_Subir() == null) {
                    cliente.getReaSyncController().mostrarMensajeLog("Nada que sincronizar");
                    System.err.println("Nada que sincronizar");
                    cliente.getGestorCambios().guardarRegistroActual();
                } else {
                    System.err.println("cambios encontrados");
                    cliente.getReaSyncController().mostrarMensajeLog("Cambios Encontrados, Se ejecutaran acciones de sincronización");
                    ejecutarAccionesSincronizacion(cambiosGlobales);
                    if(cliente.getGestorCambios().guardarRegistroActual()){
                        cliente.getReaSyncController()
                            .mostrarMensajeLog("SYNC terminada, copia de estado actual guardada");
                    }else{
                        cliente.getReaSyncController()
                            .mostrarMensajeLog("SYNC terminada, problema al guardar el estado actual");
                    }
                }
            }
        } else {
            System.err.println("error recibiendo archivos de musica o generando los cambios locales");
        }
    }

    public void ejecutarAccionesSincronizacion(CambiosGlobales cambiosGlobales) {
        determinarAccionesRemotas(cambiosGlobales);
        determinarAccionesLocales(cambiosGlobales);
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
        //System.err.println("Se descargaran los siguientes archivos");
        //pathArchivosMusica.forEach(path -> System.err.println(path.toString()));
        if (cliente.getGestorFTP().conectarClienteFTP() == 1) {
            pathArchivosMusica.forEach(archivo -> {
                if (cliente.getGestorFTP().descargarArchivo(archivo) == 1) {
                    cliente.getReaSyncController()
                            .mostrarMensajeLog("Archivo "
                                    + archivo.toString()
                                    + " Descargado");
                } else {
                    cliente.getReaSyncController()
                            .mostrarMensajeLog("El Archivo "
                                    + archivo.toString()
                                    + " No ha podido ser descargado");
                }
            });
            cliente.getGestorFTP().desconectarClienteFTP();
        } else {
            cliente.getReaSyncController()
                    .mostrarMensajeLog("Error al conectarse con el server FTP");
        }
    }

    public void eliminarArchivosMusicaLocales(List<Path> pathArchivosMusica) {
        //System.err.println("Se eliminaran los siguientes archivos");
        pathArchivosMusica.forEach(path -> {
            try {
                Files.delete(path);
            } catch (NoSuchFileException x) {
                cliente.getReaSyncController()
                            .mostrarMensajeLog("El Archivo a eliminar"
                                    + path.toString()
                                    + " No se encuentra");
            } catch (DirectoryNotEmptyException x) {
                cliente.getReaSyncController()
                            .mostrarMensajeLog("Error al tratar el archivo "
                                    + path.toString());
            } catch (IOException x) {
                // File permission problems are caught here.
                cliente.getReaSyncController()
                            .mostrarMensajeLog("No hay permisos para eliminar el archivo "
                                    + path.toString());
            }
        });
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
        String comandoPeticion = "guardarRegistroArchivo";
        if (cliente.getGestorFTP().conectarClienteFTP() == 1) {
            archivosMusica.forEach(archivo -> {
                int enviado = cliente.getGestorFTP().subirArchivo(Paths.get(archivo.getRutaArchivo()));
                if (enviado == 1) {
                    cliente.getReaSyncController()
                            .mostrarMensajeLog("Archivo "
                                    + archivo.getRutaArchivo()
                                    + " Enviado");
                    Peticion peticion = new Peticion(comandoPeticion, new GestorArchivosMusica(cliente.getGestorConfiguracion())
                            .generalizarPathArchivoMusica(Paths.get(archivo.getRutaArchivo())).toString());
                    cliente.getGestorPeticiones()
                            .hacerPeticion(peticion);
                } else {
                    cliente.getReaSyncController()
                            .mostrarMensajeLog("Error al enviar el archivo "
                                    + Paths.get(archivo.getRutaArchivo()).getFileName());
                }
            });
            cliente.getGestorFTP().desconectarClienteFTP();
        } else {
            cliente.getReaSyncController()
                    .mostrarMensajeLog("Error al conectarse con el server FTP");
        }

    }

    public void eliminarArchivosMusicaRemotos(List<ArchivoMusica> archivosMusica) {
        //System.err.println("Se eliminaran los siguientes archivos del servidor");
        String comandoPeticion = "eliminarRegistroMusica";
        if (cliente.getGestorFTP().conectarClienteFTP() == 1) {
            archivosMusica.forEach(archivo -> {
                boolean eliminado = cliente.getGestorFTP().eliminarArchivo(Paths.get(archivo.getRutaArchivo()));
                if (eliminado) {
                    cliente.getReaSyncController()
                            .mostrarMensajeLog("Archivo "
                                    + archivo.getRutaArchivo()
                                    + "eliminado del servidor");
                    Peticion peticion = new Peticion(comandoPeticion, new GestorArchivosMusica(cliente.getGestorConfiguracion())
                            .generalizarPathArchivoMusica(Paths.get(archivo.getRutaArchivo())).toString());
                    cliente.getGestorPeticiones()
                            .hacerPeticion(peticion);
                } else {
                    cliente.getReaSyncController()
                            .mostrarMensajeLog("Error al enviar eliminar el archivo "
                                    + archivo.getRutaArchivo() + " del servidor");
                }
            });
            cliente.getGestorFTP().desconectarClienteFTP();
        } else {
            cliente.getReaSyncController()
                    .mostrarMensajeLog("Error al conectarse con el server FTP");
        }

    }

    public CambiosLocales encontrarCambiosLocales() {
        return cliente.getGestorCambios().generarListaCambiosLocales();
    }

    public CambiosGlobales encontrarCambiosGlobales(CambiosLocales cambiosLocales, ArchivosMusica archivosMusicaRecibidos) {
        return cliente.getGestorCambios().generarListaCambiosGlobales(cambiosLocales, archivosMusicaRecibidos);
    }

    private void hacerPeticionRegistrosServer() {
        cliente.getGestorPeticiones()
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
        //archivosMusicaRecibidos = new GestorArchivosMusica(cliente.getGestorConfiguracion()).especificarPathArchivosMusica(archivosMusicaRecibidos);
        this.archivosMusicaRecibidos = archivosMusicaRecibidos;
    }

}
