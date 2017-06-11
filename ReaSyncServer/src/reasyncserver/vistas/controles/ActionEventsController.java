/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasyncserver.vistas.controles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import reasyncserver.bd.GestorConexionBD;
import reasyncserver.sistema.configuracion.ServerInfo;

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
        if (comando.equals("reasyncServerStartButton")) {
            if (reaSyncController.ftp && reaSyncController.mysql) {
                if (!reaSyncController.getFrame().portReaSyncServerField.getText().isEmpty()) {
                    if (isNumeric(reaSyncController.getFrame().portReaSyncServerField.getText())) {
                        int puerto = Integer.parseInt(reaSyncController.getFrame().portReaSyncServerField.getText());
                        int error = reaSyncController.getServer().iniciarServidor(puerto);
                        if (error == 1) {
                            reaSyncController.mostrarEstadoReaSyncServer("iniciado");
                            reaSyncController.reasync=true;
                        } else {
                            reaSyncController.mostrarError("Puerto", "Hubo un error al intentar montar el servidor en el puerto especificado");
                            reaSyncController.reasync=true;
                        }
                        if(reaSyncController.ftp && reaSyncController.mysql && reaSyncController.reasync){
                            reaSyncController.getFrame().servicesHomeLabel.setText("Todos los servicios estan iniciados");
                        }else{
                            reaSyncController.getFrame().servicesHomeLabel.setText("Inicie correctamente todos los servicios");
                        }
                    }
                }
            } else {
                reaSyncController.mostrarAviso("Iniciar Servicios", "Inicie los servicios Mysql y FTP antes");
            }
        }
        if (comando.equals("reasyncServerStopButton")) {
            int error = reaSyncController.getServer().cerrarServidor();
            if (error == 1) {
                reaSyncController.mostrarEstadoReaSyncServer("detenido");
            } else {
                reaSyncController.mostrarError("Servidor", "Ocurrio un error al cerrar el servidor");
            }
        }
        if (comando.equals("conexionBDButton")) {
            try {
                GestorConexionBD gbd = new GestorConexionBD();
                gbd.conectar();
                gbd.desconectar();
                reaSyncController.mostrarMensajeLog("El servicio MYSQL esta activo");
                reaSyncController.getFrame().mysqlServicesServiceLabel.setText("El servicio MYSQL esta activo");
                reaSyncController.getFrame().mysqlHomeLabel.setText("El servicio MYSQL esta activo");
                reaSyncController.getFrame().conexionBDButton.setEnabled(false);
                reaSyncController.mysql = true;
            } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
                //Logger.getLogger(ActionEventsController.class.getName()).log(Level.SEVERE, null, ex);
                reaSyncController.mostrarMensajeLog("El servicio MYSQL esta detenido");
                reaSyncController.getFrame().mysqlHomeLabel.setText("El servicio MYSQL esta detenido");
                reaSyncController.getFrame().mysqlServicesServiceLabel.setText("El servicio MYSQL esta detenido");
                reaSyncController.mostrarError("Base de datos", "Error al conectarse al servicio");
                reaSyncController.getFrame().conexionBDButton.setEnabled(true);
                reaSyncController.mysql = false;
            }
        }
        if (comando.equals("ftpServiceButton")) {
            if (reaSyncController.getServer().isLocalPortInUse(14147)) {
                reaSyncController.getFrame().ftpServicesServiceLabel.setText("El servicio FTP esta activo");
                reaSyncController.mostrarMensajeLog("El servicio FTP esta activo");
                reaSyncController.getFrame().ftpHomeLabel.setText("El servicio FTP esta activo");
                reaSyncController.getFrame().ftpServiceButton.setEnabled(false);
                reaSyncController.ftp=true;
            } else {
                reaSyncController.getFrame().ftpServicesServiceLabel.setText("Error, Verifique el servidor FTP");
                reaSyncController.mostrarMensajeLog("El servicio FTP no esta activado");
                reaSyncController.getFrame().ftpHomeLabel.setText("El servicio FTP no esta activado");
                reaSyncController.getFrame().ftpServiceButton.setEnabled(true);
                reaSyncController.ftp=false;
            }
        }
        if (comando.equals("updatePathSync")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Carpeta de sincronización");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                Path ruta = chooser.getSelectedFile().toPath();
                reaSyncController.getFrame().pathDirectorioSync.setText(ruta.toString());
                reaSyncController.getFrame().updatePathSync.setEnabled(false);
                String directorio = ruta.toString();
                String nombreDirectorio = ruta.getFileName().toString();
                reaSyncController.getServer()
                        .getGestorConfigurcion()
                        .actualizarServerInfo(new ServerInfo(directorio, nombreDirectorio));
            } else {
                reaSyncController.getFrame().pathDirectorioSync
                        .setText("Elija una ruta para hacer la sincronizacion");
            }
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
