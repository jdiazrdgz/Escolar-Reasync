package reasyncserver.sistema.configuracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jdiaz
 */
public class GestorConfiguracion {

    private Configuracion configuracion;
    private final Path directorioConfiguracion = Paths.get("config.dat");

    public GestorConfiguracion() {
        File directorio = new File(directorioConfiguracion.toString());
        if (directorio.exists()) {
            this.configuracion = leerConfiguracion();
        } else {
            configuracion = new Configuracion();
        }
    }

    public int guardarConfiguracion(Configuracion configuracion) {
        try (FileOutputStream fos = new FileOutputStream(directorioConfiguracion.getFileName().toString())) {
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(configuracion);
            return 1;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } catch (IOException ex) {
            Logger.getLogger(GestorConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public Configuracion leerConfiguracion() {
        try (FileInputStream fis = new FileInputStream(directorioConfiguracion.getFileName().toString())) {
            ObjectInputStream in = new ObjectInputStream(fis);
            return this.configuracion = (Configuracion) in.readObject();
        } catch (FileNotFoundException | ClassNotFoundException ex) {
            Logger.getLogger(GestorConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(GestorConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean existeConfiguracion() {
        File directorio = new File(directorioConfiguracion.toString());
        return directorio.exists();
    }

    public Configuracion getConfiguracion() {
        return configuracion;
    }

}
