package reasync.sistema.cambios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import reasync.cliente.Client;
import reasync.sistema.configuracion.GestorConfiguracion;

/**
 *
 * @author jdiaz
 */
public class GestorCambios {

    private final Client cliente;
    //private final Cambios cambios;
    private final Path directorioCambios = Paths.get("cambios.dat");

    public GestorCambios(Client cliente) {
        this.cliente = cliente;
        //cambios = new Cambios();
    }

    public Cambios generarListaCambios() { //cambios locales
        List<Path> estadoArchivosAnterior = obtenerEstadoArchivosAnterior();
        List<Path> estadoArchivosActual = obtenerEstadoArchivosActual();
        if(estadoArchivosAnterior==null){
            System.err.println("no hay estado anterior");
            return new Cambios(estadoArchivosActual);
        }
        return determinarCambiosLocales(estadoArchivosAnterior,estadoArchivosActual);
    }

    private Cambios determinarCambiosLocales(List<Path> estadoArchivosAnterior,List<Path> estadoArchivosActual){
        List<Path> archivosNuevos = deterinarArchivosNuevos(estadoArchivosAnterior, estadoArchivosActual);
        List<Path> archivosIguales = determinarArchivosIguales(estadoArchivosAnterior, estadoArchivosActual);
        List<Path> archivosEliminados = determinarArchivosEliminados(estadoArchivosAnterior, estadoArchivosActual);
        Cambios cambios = new Cambios(archivosEliminados, archivosIguales, archivosNuevos);
        return cambios;
    }
    private List<Path> determinarArchivosIguales(List<Path> estadoArchivosAnterior,List<Path> estadoArchivosActual){
        List<Path> archivosIguales = estadoArchivosAnterior;
        archivosIguales.retainAll(estadoArchivosActual);
        return archivosIguales;
    }
    private List<Path> determinarArchivosEliminados(List<Path> estadoArchivosAnterior, List<Path> estadoArchivosActual){
        List<Path> archivosEliminados  = estadoArchivosAnterior;
        archivosEliminados.removeAll(estadoArchivosActual);
        return archivosEliminados;
    }
    private List<Path> deterinarArchivosNuevos(List<Path> estadoArchivosAnterior, List<Path> estadoArchivosActual){
        List<Path> archivosNuevos  = estadoArchivosActual;
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
            Logger.getLogger(GestorConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(GestorConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorCambios.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } catch (IOException ex) {
            Logger.getLogger(GestorCambios.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(GestorCambios.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
    }
}
