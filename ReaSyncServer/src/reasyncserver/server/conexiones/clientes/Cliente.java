package reasyncserver.server.conexiones.clientes;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import peticion.Peticion;
import reasyncserver.bd.GestorRegistros;


/**
 *
 * @author jdiaz
 */
public class Cliente implements Runnable {

    //private Server server;
    private final Socket conexion;
    private final int id;
    private InputStream in;
    private OutputStream out;
    private GestorRegistros gestorRegistros;

    public Cliente(Socket conexion, int id) {
        this.conexion = conexion;
        this.id = id;
        int error = iniciarCanalesEntradaSalida();
        if (error == 0) {
            System.out.println("El cliente " + id + " no puede obtener "
                    + "canales de entrada salida");
        }
    }

    @Override
    public void run() {
        System.out.println("El cliente " + id + " Se ha conectado");
        iniciarEsperaPeticiones();
    }

    private int iniciarCanalesEntradaSalida() {
        try {
            in = conexion.getInputStream();
            out = conexion.getOutputStream();
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    private void iniciarEsperaPeticiones() {
        ObjectInputStream oin = null;
        try {
            Object objeto;
            oin = new ObjectInputStream(in);
            while (true) {
                objeto = (Object) oin.readObject();
                if(objeto instanceof Peticion){
                    Peticion peticion=(Peticion)objeto;
                    System.out.println("Era peticion");
                    switch(peticion.getPeticion()){
                        case"registroArchivosMusica":{
                            
                        }
                        default:{
                            
                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    public InputStream getIn() {
        return in;
    }

    public OutputStream getOut() {
        return out;
    }

}
