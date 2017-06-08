package reasyncserver.server.conexiones.clientes;

import archivos.ArchivoMusica;
import archivos.ArchivosMusica;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import peticion.Peticion;
import reasyncserver.bd.GestorRegistros;
import reasyncserver.server.conexiones.clientes.respuesta.GestorRespuestas;

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
    private GestorRespuestas gestorRespuestas;
    private final Path directorio;

    public Cliente(Socket conexion, int id, Path directorio) {
        this.conexion = conexion;
        this.id = id;
        int error = iniciarCanalesEntradaSalida();
        if (error == 0) {
            System.out.println("El cliente " + id + " no puede obtener "
                    + "canales de entrada salida");
        }
        gestorRegistros = new GestorRegistros();
        gestorRespuestas = new GestorRespuestas(this);
        this.directorio=directorio;
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
                if (objeto instanceof Peticion) {
                    Peticion peticion = (Peticion) objeto;
                    System.out.println("Se ha recibido una petición del cliente " + id);
                    switch (peticion.getPeticion()) {
                        case "registroArchivosMusica": {
                            ArchivosMusica archivosMusica = gestorRegistros.getArchivosMusica();
                            gestorRespuestas.enviarArchivosMusicaRegistrados(archivosMusica);
                            System.out.println("Se han enviado los registros de musica al cliente" + id);
                            break;
                        }
                        case "eliminarRegistroMusica":{
                            break;
                        }case "guardarRegistroArchivo":{
                            System.err.println("guardar archivo musica");
                            Path pathArchivoMusica = Paths.get(directorio.toString(),peticion.getInfo());
                            System.err.println(pathArchivoMusica);
                            ArchivoMusica archivoMusica = 
                                    new ArchivoMusica(pathArchivoMusica, pathArchivoMusica.getFileName().toString(), Long.toString(new File(pathArchivoMusica.toString()).length()));
                            gestorRegistros.guardarRegistroArchivoMusica(archivoMusica);
                            break;
                        }
                        default: {

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
