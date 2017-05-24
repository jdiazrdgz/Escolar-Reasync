/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasyncserver.vistas.controles;

import javax.swing.JOptionPane;
import reasyncserver.server.Server;
import reasyncserver.vistas.ReaSyncServer;

/**
 *
 * @author Bryan Ramos
 */
public class ReaSyncController {

    private final Server server;
    private final ReaSyncServer frame;
    private final ActionEventsController actionController;
    private final MouseEventsController mouseEventsController;

    public ReaSyncController(ReaSyncServer frame) {
        this.frame = frame;
        this.server = new Server(this);
        actionController = new ActionEventsController(this);
        mouseEventsController = new MouseEventsController(this);
    }

    public void mostrarError(String titulo, String error) {
        JOptionPane.showMessageDialog(frame, error, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarAviso(String titulo, String aviso) {
        JOptionPane.showMessageDialog(frame, aviso, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public ActionEventsController getActionController() {
        return actionController;
    }

    public MouseEventsController getMouseEventsController() {
        return mouseEventsController;
    }

    public ReaSyncServer getFrame() {
        return frame;
    }

    public Server getServer() {
        return server;
    }

}
