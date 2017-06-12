/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasyncserver.vistas.controles;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reasyncserver.server.Server;
import reasyncserver.server.conexiones.clientes.info.ClienteConectado;
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
    public boolean mysql;
    public boolean ftp;
    public boolean reasync;

    public ReaSyncController(ReaSyncServer frame) {
        this.frame = frame;
        this.server = new Server(this);
        actionController = new ActionEventsController(this);
        mouseEventsController = new MouseEventsController(this);
        this.mysql=false;
        this.ftp=false;
        this.reasync=false;
        cargarConfiguracion();
    }

    public void cargarConfiguracion() {
        iniciarTablaClientes();
        frame.pathDirectorioSync.setText(server.getGestorConfigurcion().getConfiguracion().getServerInfo().getRutaDirectorioSync());
    }
    public void iniciarTablaClientes(){
        DefaultTableModel datos = new DefaultTableModel();
        datos.addColumn("ID");
        datos.addColumn("Dirección IP");
        datos.addColumn("Nombre");
        frame.clientsTable.setModel(datos);
    }
    public void actualizarClienteTabla(ClienteConectado clienteConectado){
        DefaultTableModel yourModel = (DefaultTableModel)  frame.clientsTable.getModel();
        yourModel.removeRow(Integer.parseInt(clienteConectado.getId()));
        yourModel.insertRow(Integer.parseInt(clienteConectado.getId()), new Object[]{clienteConectado.getId(), clienteConectado.getIp(),clienteConectado.getNombre()});
    }
    public void eliminarClienteTabla(ClienteConectado clienteConectado){
        DefaultTableModel yourModel = (DefaultTableModel)  frame.clientsTable.getModel();
        try{
            yourModel.removeRow(Integer.parseInt(clienteConectado.getId()));
        }catch(ArrayIndexOutOfBoundsException e){
            System.err.println("El cliente ya no existe");
        }
    }
    public void mostrarClienteTabla(ClienteConectado clienteConectado){
        DefaultTableModel yourModel = (DefaultTableModel)  frame.clientsTable.getModel();
        yourModel.insertRow(Integer.parseInt(clienteConectado.getId()), new Object[]{clienteConectado.getId(), clienteConectado.getIp(),clienteConectado.getNombre()});
    }
    public void mostrarMensajeLog(String mensaje) {
        LocalDateTime ahora = LocalDateTime.now();
        frame.logTextArea.append(ahora + " " + mensaje + "\n");
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
                mostrarMensajeLog("El servicio ReaSync ha sido activado");
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
                mostrarMensajeLog("El servicio ReaSync ha desactivado");
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
