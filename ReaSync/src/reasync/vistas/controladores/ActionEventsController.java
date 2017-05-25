package reasync.vistas.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import javax.swing.JFileChooser;
import reasync.sistema.configuracion.GestorConfiguracion;

/**
 *
 * @author jdiaz
 */
class ActionEventsController implements ActionListener {

    private final ReaSyncController reaSyncController;

    public ActionEventsController(ReaSyncController reaSyncController) {
        this.reaSyncController = reaSyncController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "chooseDirectoryButton": {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Carpeta de sincronización");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    Path ruta = chooser.getSelectedFile().toPath();
                    reaSyncController.getFrame().urlDirectoryLabel.setText(ruta.toString());
                    reaSyncController.getFrame().saveUrlButton.setEnabled(true);
                } else {
                    reaSyncController.getFrame().urlDirectoryLabel
                            .setText("Elija una ruta para hacer la sincronizacion");
                }
                break;
            }
            case "saveUrlButton": {
                reaSyncController.getFrame().saveUrlButton.setEnabled(false);
                String directorio = reaSyncController.getFrame().urlDirectoryLabel.getText();
                reaSyncController.getGestorConfiguracion().actualizarDirectorioConfiguracion(directorio);
                break;
            }
            default: {
            }
            break;
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
