package reasync.vistas.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

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
                reaSyncController.getCliente().getGestorConfiguracion().actualizarDirectorioConfiguracion(directorio);
                break;
            }
            case "connectServerButton": {
                int puerto = Integer.parseInt(reaSyncController.getFrame().portServerField.getText());
                String ipServer = reaSyncController.getFrame().urlServerField.getText();
                int error = reaSyncController.getCliente().conectarConServidor(puerto, ipServer);
                System.err.println("Conectado");
                if (error == 1) {
                    reaSyncController.getFrame().statusConnectionServerLabel
                            .setText("Conectado con el servidor de ReaSync");
                    reaSyncController.getFrame().connectServerButton.setEnabled(false);
                    reaSyncController.getFrame().disconectServerButton.setEnabled(true);

                } else {
                    reaSyncController.getFrame().statusConnectionServerLabel
                            .setText("Error al conectarse con el servidor de ReaSync");
                    reaSyncController.getFrame().connectServerButton.setEnabled(true);
                    reaSyncController.getFrame().disconectServerButton.setEnabled(false);
                }
                break;
            }
            case "disconectServerButton": {
                int error = reaSyncController.getCliente().desconectarConServidor();
                if (error == 1) {
                    reaSyncController.getFrame().statusConnectionServerLabel
                            .setText("No estas conectado con el servidor de ReaSync");
                } else {
                    reaSyncController.getFrame().statusConnectionServerLabel
                            .setText("Ocurrio un error al cerrar la conexión con el servidor");
                }
                break;
            }
            case "syncNowButton": {
                int noArtchivosMusica=reaSyncController.getCliente()
                        .getGestorDirectorio()
                        .getEscaneadorDirectorio()
                        .getTotalArchivosMusicaDirectorioPrincipal();
                System.out.println(noArtchivosMusica);
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
