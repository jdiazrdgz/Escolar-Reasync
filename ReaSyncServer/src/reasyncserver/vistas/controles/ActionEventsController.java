/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasyncserver.vistas.controles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
