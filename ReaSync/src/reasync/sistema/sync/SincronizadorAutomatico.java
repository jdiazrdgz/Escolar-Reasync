/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reasync.sistema.sync;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import reasync.cliente.Client;

/**
 *
 * @author jdiaz
 */
public class SincronizadorAutomatico implements Runnable {

    private Client cliente;

    public SincronizadorAutomatico(Client cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        while (true) {
            try {
                cliente.getReaSyncController().mostrarMensajeLog("Buscando Cambios");
                cliente.getReaSyncController().getCliente()
                        .getGestorSincronizacion()
                        .iniciarProcesoSincronizacion();
                sleep(60000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(SincronizadorAutomatico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
