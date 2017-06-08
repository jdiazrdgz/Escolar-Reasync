package reasyncserver;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import reasyncserver.sistema.configuracion.Configuracion;
import reasyncserver.sistema.configuracion.GestorConfiguracion;
import reasyncserver.sistema.configuracion.ServerInfo;

/**
 *
 * @author jdiaz
 */
public class IniciarSistema {

    private final Path directorioConfiguracion = Paths.get("config.dat");
    File directorio = new File(directorioConfiguracion.toString());

    public void iniciarSistema() {
        if (directorio.exists()) {
            reasyncserver.vistas.ReaSyncServer frame = new reasyncserver.vistas.ReaSyncServer();
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Completa para hacer el primer inicio", "Bienvenido a ReaSyc", JOptionPane.INFORMATION_MESSAGE);
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Elige la carpeta de sincronización");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                Path ruta = chooser.getSelectedFile().toPath();
                String dir = ruta.toString();
                String nombreDirectorio= ruta.getFileName().toString();
                new GestorConfiguracion()
                        .guardarConfiguracion(new Configuracion(new ServerInfo(dir,nombreDirectorio)));
            } else {
                JOptionPane.showMessageDialog(null, "Elija un directorio", "Atención", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
            iniciarSistema();
        }
    }
}
