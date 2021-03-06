package reasync.vistas.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
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
                    reaSyncController.getFrame().nombreDirectorioSync.setVisible(false);
                    reaSyncController.getFrame().nombreDirectorioSync.setText(ruta.getFileName().toString());
                } else {
                    reaSyncController.getFrame().urlDirectoryLabel
                            .setText("Elija una ruta para hacer la sincronizacion");
                }
                break;
            }
            case "saveUrlButton": {
                reaSyncController.getFrame().saveUrlButton.setEnabled(false);
                String directorio = reaSyncController.getFrame().urlDirectoryLabel.getText();
                String nombreDirectorio = reaSyncController.getFrame().urlDirectoryLabel.getText();
                reaSyncController.getCliente().getGestorConfiguracion()
                        .actualizarDirectorioConfiguracion(directorio);
                reaSyncController.getCliente().getGestorConfiguracion()
                        .actualizarNombreDirectorioConfiguracion(nombreDirectorio);
                break;
            }
            case "connectServerButton": {
                int puerto = Integer.parseInt(reaSyncController.getFrame().portServerField.getText());
                String ipServer = reaSyncController.getFrame().urlServerField.getText();
                int error = reaSyncController.getCliente().conectarConServidor(puerto, ipServer);
                if (error == 1) {
                    reaSyncController.getCliente().iniciarGestorPeticiones();
                    reaSyncController.mostrarMensajeLog("Conexion establecida con el servidor de ReaSync");
                    reaSyncController.getFrame().statusConnectionServerLabel
                            .setText("Conectado con el servidor de ReaSync");
                    reaSyncController.getFrame().conectedHomeLabel.setText("Conectado con el servidor de ReaSync");
                    reaSyncController.getFrame().connectServerButton.setEnabled(false);
                    reaSyncController.getFrame().disconectServerButton.setEnabled(true);
                    reaSyncController.getCliente().iniciarGestorFTP(ipServer, puerto);
                    
                } else {
                    reaSyncController.mostrarMensajeLog("Error al conectarse con el servidor de ReaSync");
                    reaSyncController.getFrame().statusConnectionServerLabel
                            .setText("Error al conectarse con el servidor de ReaSync");
                    reaSyncController.getFrame().conectedHomeLabel.setText("Error al conectarse con el servidor de ReaSync");
                    reaSyncController.getFrame().connectServerButton.setEnabled(true);
                    reaSyncController.getFrame().disconectServerButton.setEnabled(false);
                }
                break;
            }
            case "disconectServerButton": {
                int error = reaSyncController.getCliente().desconectarConServidor();
                if (error == 1) {
                    reaSyncController.mostrarMensajeLog("Desconectado del servidor de ReaSync");
                    reaSyncController.getFrame().statusConnectionServerLabel
                            .setText("No estas conectado con el servidor de ReaSync");
                    reaSyncController.getFrame().conectedHomeLabel.setText("No estas conectado con el servidor de ReaSync");
                    reaSyncController.getFrame().connectServerButton.setEnabled(true);
                    reaSyncController.getFrame().disconectServerButton.setEnabled(false);
                } else {
                    reaSyncController.mostrarMensajeLog("Error al desconectarse con el servidor de ReaSync");
                    reaSyncController.getFrame().statusConnectionServerLabel
                            .setText("Error al desconectarse con el servidor de ReaSync");
                    reaSyncController.getFrame().conectedHomeLabel.setText("Error al desconectarse con el servidor de ReaSync");
                    reaSyncController.getFrame().connectServerButton.setEnabled(false);
                    reaSyncController.getFrame().disconectServerButton.setEnabled(true);
                }
                break;
            }
            case "syncNowButton": {
                reaSyncController.mostrarMensajeLog("Se ha solicitado inicio de sincronización manual");
                reaSyncController.getCliente()
                        .getGestorSincronizacion()
                        .iniciarProcesoSincronizacion();
                break;
            }
            case "startAutoSyncButton":{
                reaSyncController.mostrarMensajeLog("Se inicia la sincronización automatica");
                reaSyncController.getCliente().iniciarSincronizacionAutomatica();
                reaSyncController.getFrame().startAutoSyncButton.setEnabled(false);
                reaSyncController.getFrame().syncNowButton.setEnabled(false);
                reaSyncController.getFrame().stopAutoSyncButton.setEnabled(true);
                reaSyncController.getFrame().syncHomeLabel.setText("La sincronización automatica esta activada");
                break;
            }
            case "stopAutoSyncButton":{
                reaSyncController.mostrarMensajeLog("Se ha detenido la sincronización automatica");
                reaSyncController.getFrame().syncHomeLabel.setText("La sincronización automatica esta desactivada");
                reaSyncController.getCliente().detenerSincronizacionAutomatica();
                reaSyncController.getFrame().startAutoSyncButton.setEnabled(true);
                reaSyncController.getFrame().syncNowButton.setEnabled(true);
                reaSyncController.getFrame().stopAutoSyncButton.setEnabled(false);
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
