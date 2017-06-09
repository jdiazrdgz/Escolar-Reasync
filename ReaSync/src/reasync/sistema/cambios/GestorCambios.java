package reasync.sistema.cambios;

import archivos.ArchivoMusica;
import archivos.ArchivosMusica;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import reasync.cliente.Client;
import reasync.sistema.archivos.GestorArchivosMusica;
import reasync.sistema.configuracion.GestorConfiguracion;

/**
 *
 * @author jdiaz
 */
public class GestorCambios {

    private final Client cliente;
    private final Path directorioCambios = Paths.get("cambios.dat");

    public GestorCambios(Client cliente) {
        this.cliente = cliente;
    }

    public CambiosGlobales generarListaCambiosGlobales(CambiosLocales cambiosLocales, ArchivosMusica archivosMusicaRecibidos) {
        return determinarCambiosGlobales(cambiosLocales, archivosMusicaRecibidos);
    }

    public CambiosGlobales determinarCambiosGlobales(CambiosLocales cambiosLocales, ArchivosMusica archivosMusicaRecibidos) {
        if (archivosMusicaRecibidos.getArchivosMusica().isEmpty()
                && cambiosLocales.getArchivosSimilares().isEmpty()
                && cambiosLocales.getArchivosNuevos().isEmpty()
                && cambiosLocales.getArchivosEliminados().isEmpty()) {
            cliente.getReaSyncController().mostrarMensajeLog("No se detectan archivos en server y archivos locales");
            //el server no tiene archivos musica registros y nosotros tampoco, no hay nada que sincronizar
            return null;
        }
        if (archivosMusicaRecibidos.getArchivosMusica().isEmpty()) {
            Object[] options = {"Subir Archivos locales a server",
                "Eliminar los archivos locales", "Cancelar Sincronización"};
            int n = JOptionPane.showOptionDialog(null,
                    "El Server no tiene registros de musica, ¿Qué deseas hacer?",
                    "Atención",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, //do not use a custom Icon
                    options, //the titles of buttons
                    options[0]); //default button title
            switch (n) {
                case 0: {
                    //Subir todos los locales a server
                    List<Path> pathArchivosSubir = new ArrayList<>();
                    if (!cambiosLocales.getArchivosNuevos().isEmpty()) {
                        pathArchivosSubir.addAll(cambiosLocales.getArchivosNuevos());
                    }
                    if (!cambiosLocales.getArchivosSimilares().isEmpty()) {
                        pathArchivosSubir.addAll(cambiosLocales.getArchivosSimilares());
                    }
                    if (!cambiosLocales.getArchivosNuevos().isEmpty() && !cambiosLocales.getArchivosSimilares().isEmpty()) {
                        List<ArchivoMusica> listaArchivosMusica = new ArrayList<>();
                        pathArchivosSubir.forEach(path -> {
                            listaArchivosMusica.add(new ArchivoMusica(path.toString(), path.getFileName().toString(), Long.toString(new File(path.toString()).length())));
                        });
                        ArchivosMusica archivosRemotos_Subir = new ArchivosMusica(listaArchivosMusica);
                        ArchivosMusica archivosRemotos_Eliminar = null;
                        List<Path> archivosLocales_Eliminar = null;
                        List<Path> archivosLocales_Descargar = null;
                        return new CambiosGlobales(archivosLocales_Eliminar, archivosLocales_Descargar, archivosRemotos_Subir, archivosRemotos_Eliminar);
                    } else {
                        return null;
                    }
                }
                case 1: {
                    //Eliminar los locales
                    ArchivosMusica archivosRemotos_Subir = null;
                    ArchivosMusica archivosRemotos_Eliminar = null;
                    List<Path> archivosLocales_Descargar = null;
                    List<Path> archivosLocales_Eliminar = new ArrayList<>();
                    if (!cambiosLocales.getArchivosNuevos().isEmpty()) {
                        archivosLocales_Eliminar.addAll(cambiosLocales.getArchivosNuevos());
                    }
                    if (!cambiosLocales.getArchivosSimilares().isEmpty()) {
                        archivosLocales_Eliminar.addAll(cambiosLocales.getArchivosSimilares());
                    }
                    if (!cambiosLocales.getArchivosNuevos().isEmpty() && !cambiosLocales.getArchivosSimilares().isEmpty()) {
                        return new CambiosGlobales(archivosLocales_Eliminar, archivosLocales_Descargar, archivosRemotos_Subir, archivosRemotos_Eliminar);
                    } else {
                        return null;
                    }
                }
                case 2: {
                    //Nada
                    return null;
                }
            }
            return null;
        } else {
            //De cajon si hay archivos de server
            ArchivosMusica archivosMusicaRecibidosEspecificados
                    = new GestorArchivosMusica(cliente.getGestorConfiguracion())
                            .especificarPathArchivosMusica(archivosMusicaRecibidos);
            List<Path> listaPathArchivosMusicaRecibidos
                    = new GestorArchivosMusica(cliente.getGestorConfiguracion())
                            .obtenerPathArchivosMusica(archivosMusicaRecibidosEspecificados);
            List<Path> archivosLocales_Eliminar = determinarArchivosLocales_Eliminar(cambiosLocales, listaPathArchivosMusicaRecibidos);
            List<Path> archivosLocales_Descargar = determinarArchivosLocales_Descargar(cambiosLocales, listaPathArchivosMusicaRecibidos);
            ArchivosMusica archivosRemotos_Subir = determinarArchivosRemotos_Subir(cambiosLocales, listaPathArchivosMusicaRecibidos);
            ArchivosMusica archivosRemotos_Eliminar = determinarArchivosRemotos_Eliminar(cambiosLocales, listaPathArchivosMusicaRecibidos);
            return new CambiosGlobales(archivosLocales_Eliminar, archivosLocales_Descargar, archivosRemotos_Subir, archivosRemotos_Eliminar);
        }
    }

    public List<Path> determinarArchivosLocales_Eliminar(CambiosLocales cambiosLocales, List<Path> listaPathArchivosMusicaRecibidos) {
        if (cambiosLocales.getArchivosNuevos().isEmpty() && cambiosLocales.getArchivosSimilares().isEmpty()) {
            //nada que hacer, no se comprueba eliminados porque ya no existen en este equipo
            return null;
        }
        List<Path> archivosEliminar = new ArrayList<>();
        if (!cambiosLocales.getArchivosNuevos().isEmpty()) {
            archivosEliminar.addAll(cambiosLocales.getArchivosNuevos());
        }
        if (!cambiosLocales.getArchivosSimilares().isEmpty()) {
            archivosEliminar.addAll(cambiosLocales.getArchivosSimilares());
        }
        archivosEliminar.removeAll(listaPathArchivosMusicaRecibidos);
        if (archivosEliminar.isEmpty()) {
            //no hay nada que eliminar localmente
            return null;
        } else {
            //estos son los que se eliminan
            return archivosEliminar;
        }
    }

    public List<Path> determinarArchivosLocales_Descargar(List<Path> archivosLocalesSimilares, List<Path> listaPathArchivosMusicaRecibidos) {
        listaPathArchivosMusicaRecibidos.removeAll(archivosLocalesSimilares);
        if (listaPathArchivosMusicaRecibidos.isEmpty()) {
            //siginifca que tenemos todos los archivos no nos hace falta ninguno
            return null;
        } else {
            //son los que hay que descargar
            return listaPathArchivosMusicaRecibidos;
        }
    }

    public ArchivosMusica determinarArchivosRemotos_Subir(List<Path> archivosLocalesNuevos, List<Path> listaPathArchivosMusicaRecibidos) {
        List<ArchivoMusica> archivosMusica_subir = new ArrayList<>();
        if (archivosLocalesNuevos == null) {
            //no hay archivos nuevos
        }
        archivosLocalesNuevos.removeAll(listaPathArchivosMusicaRecibidos);
        if (archivosLocalesNuevos.isEmpty()) {
            //server ya tiene estos archivos
        } else {
            //son los que vamos a subir
            archivosLocalesNuevos.forEach(path -> archivosMusica_subir
                    .add(new ArchivoMusica(path.toString(), path.getFileName().toString(), Long.toString(new File(path.toString()).length()))));
        }
        return new ArchivosMusica(archivosMusica_subir);
    }

    public ArchivosMusica determinarArchivosRemotos_Eliminar(List<Path> archivosLocalesEliminados, List<Path> listaPathArchivosMusicaRecibidos) {;
        List<Path> pathArchivosMusica_Eliminar = archivosLocalesEliminados;
        List<ArchivoMusica> archivosMusica_Eliminar = new ArrayList<>();
        pathArchivosMusica_Eliminar.retainAll(listaPathArchivosMusicaRecibidos);
        if (pathArchivosMusica_Eliminar.isEmpty()) {
            //nada que eliminar en el server
        } else {
            //son los que vamos a subir
            pathArchivosMusica_Eliminar.forEach(path -> archivosMusica_Eliminar
                    .add(new ArchivoMusica(path.toString(), path.getFileName().toString(), Long.toString(new File(path.toString()).length()))));
        }
        return new ArchivosMusica(archivosMusica_Eliminar);
    }
//-------------------------CambiosLocales Locales----------------------------------------------------

    public CambiosLocales generarListaCambiosLocales() { //cambios locales
        List<Path> estadoArchivosAnterior = obtenerEstadoArchivosAnterior();
        List<Path> estadoArchivosActual = obtenerEstadoArchivosActual();
        if (estadoArchivosAnterior == null) {
            System.err.println("no hay estado anterior");
            return new CambiosLocales(estadoArchivosActual);
        }
        return determinarCambiosLocales(estadoArchivosAnterior, estadoArchivosActual);
    }

    private CambiosLocales determinarCambiosLocales(List<Path> estadoArchivosAnterior, List<Path> estadoArchivosActual) {
        List<Path> archivosNuevos = deterinarArchivosNuevos(estadoArchivosAnterior, estadoArchivosActual);
        List<Path> archivosIguales = determinarArchivosIguales(estadoArchivosAnterior, estadoArchivosActual);
        List<Path> archivosEliminados = determinarArchivosEliminados(estadoArchivosAnterior, estadoArchivosActual);
        CambiosLocales cambios = new CambiosLocales(archivosEliminados, archivosIguales, archivosNuevos);
        return cambios;
    }

    private List<Path> determinarArchivosIguales(List<Path> estadoArchivosAnterior, List<Path> estadoArchivosActual) {
        List<Path> archivosIguales = estadoArchivosAnterior;
        archivosIguales.retainAll(estadoArchivosActual);
        return archivosIguales;
    }

    private List<Path> determinarArchivosEliminados(List<Path> estadoArchivosAnterior, List<Path> estadoArchivosActual) {
        List<Path> archivosEliminados = estadoArchivosAnterior;
        archivosEliminados.removeAll(estadoArchivosActual);
        return archivosEliminados;
    }

    private List<Path> deterinarArchivosNuevos(List<Path> estadoArchivosAnterior, List<Path> estadoArchivosActual) {
        List<Path> archivosNuevos = estadoArchivosActual;
        archivosNuevos.removeAll(estadoArchivosAnterior);
        return archivosNuevos;
    }

    private List<Path> obtenerEstadoArchivosAnterior() {
        File directorio = new File(directorioCambios.toString());
        if (directorio.exists()) {
            return leerEstadoArchivosAnterior();
        } else {
            return null;
        }
    }

    private List<Path> obtenerEstadoArchivosActual() {
        List<Path> arbolDirectorios = cliente.getGestorDirectorio()
                .getEscaneadorDirectorio().obtenerArbolDirectorios();
        List<Path> estadoArchivosActual = cliente.getGestorDirectorio()
                .getEscaneadorDirectorio().obtenerPathArchivos(arbolDirectorios);
        return estadoArchivosActual;
    }

    public List<Path> leerEstadoArchivosAnterior() {
        try (FileInputStream fis = new FileInputStream(directorioCambios.getFileName().toString())) {
            ObjectInputStream in = new ObjectInputStream(fis);
            List<Path> estadoanterior = (List<Path>) in.readObject();
            return estadoanterior;

        } catch (FileNotFoundException | ClassNotFoundException ex) {
            Logger.getLogger(GestorConfiguracion.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;

        } catch (IOException ex) {
            Logger.getLogger(GestorConfiguracion.class
                    .getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private int guardarRegistroActual() {
        FileOutputStream fos = null;
        try {
            List<Path> arbolDirectorios = cliente.getGestorDirectorio()
                    .getEscaneadorDirectorio().obtenerArbolDirectorios();
            List<Path> archivos = cliente.getGestorDirectorio()
                    .getEscaneadorDirectorio().obtenerPathArchivos(arbolDirectorios);
            fos = new FileOutputStream(directorioCambios.getFileName().toString());
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(archivos); //se guarda y serializa al estudiante
            return 1;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorCambios.class
                    .getName()).log(Level.SEVERE, null, ex);
            return 0;

        } catch (IOException ex) {
            Logger.getLogger(GestorCambios.class
                    .getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            try {
                fos.close();

            } catch (IOException ex) {
                Logger.getLogger(GestorCambios.class
                        .getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
    }
}
