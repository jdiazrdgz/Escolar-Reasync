/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasyncserver.vistas.controles;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reasyncserver.server.Server;
import reasyncserver.server.conexiones.clientes.info.ClienteConectado;
import reasyncserver.sistema.configuracion.Configuracion;
import reasyncserver.sistema.configuracion.GestorConfiguracion;
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
    private final GestorConfiguracion gestorConfiguracion;

    public ReaSyncController(ReaSyncServer frame) {
        this.frame = frame;
        this.server = new Server(this);
        actionController = new ActionEventsController(this);
        mouseEventsController = new MouseEventsController(this);
        gestorConfiguracion = new GestorConfiguracion();
        cargarConfiguracion();
    }

    public void cargarConfiguracion() {
        iniciarTablaClientes();
    }
    
    public void iniciarTablaClientes(){
        DefaultTableModel datos = new DefaultTableModel();
        datos.addColumn("ID");
        datos.addColumn("Dirección IP");
        datos.addColumn("Nombre");
        frame.clientsTable.setModel(datos);
    }
    public void mostrarClienteTabla(ClienteConectado clienteConectado){
        DefaultTableModel yourModel = (DefaultTableModel)  frame.clientsTable.getModel();
        yourModel.addRow(new Object[]{clienteConectado.getId(), clienteConectado.getIp(),clienteConectado.getNombre()});
    }
    public void mostrarError(String titulo, String error) {
        JOptionPane.showMessageDialog(frame, error, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarAviso(String titulo, String aviso) {
        JOptionPane.showMessageDialog(frame, aviso, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarEstadoReaSyncServer(String estado) {
        switch (estado) {
            case "iniciado": {
                frame.reasyncServicesServerLabel.setText("El servicio ReaSync esta activo");
                frame.reasyncServicesServerLabel.setText("El servicio ReaSync esta activo");
                String ip = server.getPublicIP();
                if (ip != null) {
                    frame.reaSyncServerPortLabel.setText("La ip del equipo es: " + ip);
                } else {
                    frame.reaSyncServerPortLabel.setText("Error al obtener la ip del equipo");
                }
                frame.reasyncServerStopButton.setEnabled(true);
                frame.reasyncServerStartButton.setEnabled(false);
            }
            break;
            case "detenido": {
                frame.reasyncServerLabel.setText("El servicio ReaSync esta detenido");
                frame.reasyncServicesServerLabel.setText("El servicio ReaSync esta detenido");
                frame.reasyncServerStopButton.setEnabled(false);
                frame.reasyncServerStartButton.setEnabled(true);
            }
        }
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
