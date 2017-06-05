package reasync.sistema.cambios;

import com.sun.javafx.scene.control.skin.VirtualFlow;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import reasync.cliente.Client;
import reasync.sistema.configuracion.Configuracion;
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
        return determinarCambiosLocales(estadoArchivosAnterior,estadoArchivosActual);
    }

    private Cambios determinarCambiosLocales(List<Path> estadoArchivosAnterior,List<Path> estadoArchivosActual){
        Cambios cambios;
        Set<Path> mathListas = new HashSet<>();
        mathListas.addAll(estadoArchivosAnterior);
        estadoArchivosAnterior.addAll(estadoArchivosActual);
        Set<Path> archivosNuevos = deterinarArchivosNuevos(estadoArchivosAnterior, mathListas);
        return cambios;
    }
    
    private Set<Path> deterinarArchivosNuevos(List<Path> estadoArchivosAnterior, Set<Path> matchListas){
        Set<Path> matchListasAux  = matchListas;
        matchListasAux.removeAll(estadoArchivosAnterior);
        return matchListasAux;
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
