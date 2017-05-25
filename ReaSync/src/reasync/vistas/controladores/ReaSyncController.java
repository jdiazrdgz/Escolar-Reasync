/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasync.vistas.controladores;

import javax.swing.JOptionPane;
import reasync.cliente.Client;
import reasync.vistas.ReaSync;

/**
 *
 * @author jdiaz
 */
public class ReaSyncController {
    private final ReaSync frame;
    private final Client cliente;
    private final ActionEventsController actionController;
    private final MouseEventsController mouseEventsController;

    public ReaSyncController(ReaSync frame) {
        this.frame = frame;
        cliente = new Client(this);
        actionController = new ActionEventsController(this);
        mouseEventsController = new MouseEventsController(this);
    }

    public void mostrarError(String titulo, String error) {
        JOptionPane.showMessageDialog(frame, error, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarAviso(String titulo, String aviso) {
        JOptionPane.showMessageDialog(frame, aviso, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void cambiarEstadoConexion(String estado){
        switch(estado){
            case "conectado":{
                frame.statusConnectionServerLabel.setText("Estas conectado con el servidor de ReaSync");
                frame.connectServerButton.setEnabled(false);
                frame.disconectServerButton.setEnabled(true);
            }
            case "desconectado":{
                frame.statusConnectionServerLabel.setText("No estas conectado con el servidor de ReaSync");
                frame.connectServerButton.setEnabled(true);
                frame.disconectServerButton.setEnabled(false);
            }
        }
    }

    public ReaSync getFrame() {
        return frame;
    }

    public Client getCliente() {
        return cliente;
    }

    public ActionEventsController getActionController() {
        return actionController;
    }

    public MouseEventsController getMouseEventsController() {
        return mouseEventsController;
    }
    
    
}
