/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasyncserver.vistas.controles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import reasyncserver.bd.GestorConexionBD;

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
            if (!reaSyncController.getFrame().portReaSyncServerField.getText().isEmpty()) {
                if (isNumeric(reaSyncController.getFrame().portReaSyncServerField.getText())) {
                    int puerto = Integer.parseInt(reaSyncController.getFrame().portReaSyncServerField.getText());
                    int error = reaSyncController.getServer().iniciarServidor(puerto);
                    if (error == 1) {
                        reaSyncController.mostrarEstadoReaSyncServer("iniciado");
                    } else {
                        reaSyncController.mostrarError("Puerto", "Hubo un error al intentar montar el servidor en el puerto especificado");
                    }
                }
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
        if(comando.equals("conexionBDButton")){
            try {
                GestorConexionBD gbd = new GestorConexionBD();
                gbd.conectar();
                gbd.desconectar();
                reaSyncController.mostrarMensajeLog("El servicio MYSQL esta activo");
                reaSyncController.getFrame().mysqlServicesServiceLabel.setText("El servicio MYSQL esta activo");
                reaSyncController.getFrame().conexionBDButton.setEnabled(false);
            } catch (SQLException | ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
                //Logger.getLogger(ActionEventsController.class.getName()).log(Level.SEVERE, null, ex);
                reaSyncController.mostrarMensajeLog("El servicio MYSQL esta detenido");
                reaSyncController.getFrame().mysqlServicesServiceLabel.setText("El servicio MYSQL esta detenido");
                reaSyncController.mostrarError("Base de datos", "Error al conectarse al servicio");
                reaSyncController.getFrame().conexionBDButton.setEnabled(true);
            }
        }
        if(comando.equals("ftpServiceButton")){
            if(reaSyncController.getServer().isLocalPortInUse(14147)){
                reaSyncController.getFrame().ftpServicesServiceLabel.setText("El servicio FTP esta activo");
                reaSyncController.mostrarMensajeLog("El servicio FTP esta activo");
                reaSyncController.getFrame().ftpServiceButton.setEnabled(false);
            }else{
                reaSyncController.getFrame().ftpServicesServiceLabel.setText("Error, Verifique el servidor FTP");
                reaSyncController.mostrarMensajeLog("El servicio FTP no esta activado");
                reaSyncController.getFrame().ftpServiceButton.setEnabled(true);
            }
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
