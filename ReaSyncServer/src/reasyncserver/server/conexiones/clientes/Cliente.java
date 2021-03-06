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
import respuesta.Respuesta;

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
    private final GestorRegistros gestorRegistros;
    private final GestorRespuestas gestorRespuestas;
    private final GestorArchivosCliente gestorArchivosCliente;
    private final Path directorio;
    private String nombreUsuario;
    private String ip;
    private boolean alive;

    public Cliente(Socket conexion, int id, Path directorio, String ip) {
        this.conexion = conexion;
        this.id = id;
        int error = iniciarCanalesEntradaSalida();
        if (error == 0) {
            System.out.println("El cliente " + id + " no puede obtener "
                    + "canales de entrada salida");
        }
        gestorRegistros = new GestorRegistros(this);
        gestorRespuestas = new GestorRespuestas(this);
        gestorArchivosCliente = new GestorArchivosCliente(this);
        this.directorio = directorio;
        nombreUsuario = "identificando";
        this.ip=ip;
        alive=true;
    }

    @Override
    public void run() {
        System.out.println("El cliente " + id + " Se ha conectado");
        solicitarNombreUsuario();
        iniciarEsperaPeticiones();
    }
    private void solicitarNombreUsuario(){
        gestorRespuestas.enviarRespuesta(new Respuesta("dameUsuario"));
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
                        case "eliminarRegistroMusica": {
                            Path pathArchivoMusica = Paths.get(directorio.toString(), peticion.getInfo());
                            //System.err.println(pathArchivoMusica);
                            ArchivoMusica archivoMusica
                                    = new ArchivoMusica(pathArchivoMusica.toString(), pathArchivoMusica.getFileName().toString(), Long.toString(new File(pathArchivoMusica.toString()).length()));
                            if (gestorRegistros.existeRegistro(archivoMusica)) {
                                //System.err.println("existe" + archivoMusica.getRutaArchivo());
                                gestorRegistros.eliminarRegistroArchivoMusica(archivoMusica);
                            }
                            break;
                        }
                        case "guardarRegistroArchivo": {
                            //System.err.println("guardar archivo musica");
                            Path pathArchivoMusica = Paths.get(directorio.toString(), peticion.getInfo());
                            //System.err.println(pathArchivoMusica);
                            ArchivoMusica archivoMusica
                                    = new ArchivoMusica(pathArchivoMusica.toString(), pathArchivoMusica.getFileName().toString(), Long.toString(new File(pathArchivoMusica.toString()).length()));
                            if (!gestorRegistros.existeRegistro(archivoMusica)) {
                                gestorRegistros.guardarRegistroArchivoMusica(archivoMusica);
                            }
                            break;
                        }
                        case "guardarNombreUsuario": {
                            nombreUsuario = peticion.getInfo();
                            System.err.println("EL nombre del usuario es: "+nombreUsuario);
                        }
                        default: {

                        }
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            //Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            //El cliente se ha desconectado
            alive=false;
        }
    }

    public InputStream getIn() {
        return in;
    }

    public OutputStream getOut() {
        return out;
    }

    public GestorArchivosCliente getGestorArchivosCliente() {
        return gestorArchivosCliente;
    }

    public Path getDirectorio() {
        return directorio;
    }

    public int getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getIp() {
        return ip;
    }

    public Socket getConexion() {
        return conexion;
    }

    public boolean isAlive() {
        return alive;
    }
    
}
