/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasyncserver.vistas.controles;

import javax.swing.JOptionPane;
import reasyncserver.vistas.ReaSyncServer;

/**
 *
 * @author Bryan Ramos
 */
public class ReaSyncController {

    private ReaSyncServer frame;

    public void mostrarError(String titulo, String error) {
        JOptionPane.showMessageDialog(frame, error, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarAviso(String titulo, String aviso) {
        JOptionPane.showMessageDialog(frame, aviso, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
