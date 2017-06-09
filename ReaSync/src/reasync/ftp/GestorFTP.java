package reasync.ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import reasync.cliente.Client;
import reasync.sistema.archivos.GestorArchivosMusica;

/**
 *
 * @author jdiaz
 */
public class GestorFTP {

    private final Client cliente;
    private final String ipserver;
    private final int port = 21;
    private final String user = "reasync";
    private final String pass = "jenesais";
    private FTPClient ftpClient;

    public GestorFTP(Client cliente, String ipserver, int port) {
        this.cliente = cliente;
        this.ipserver = ipserver;
        ftpClient = new FTPClient();
        System.err.println("se inicia ftp client");
    }

    public int conectarClienteFTP() {
        try {
            ftpClient.connect(ipserver, port);
            showServerReply(ftpClient);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Operation failed. Server reply code: " + replyCode);
                return 0;
            }
            boolean success = ftpClient.login(user, pass);
            showServerReply(ftpClient);
            if (!success) {
                System.out.println("Could not login to the server");
                return 0;
            } else {
                System.out.println("LOGGED IN SERVER");
                return 1;
            }
        } catch (IOException ex) {
            Logger.getLogger(GestorFTP.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    private void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }

    public int desconectarClienteFTP() {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
                return 1;
            } catch (IOException ex) {
                Logger.getLogger(GestorFTP.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
        return 1;
    }

    public int subirArchivo(Path pathArchivo) {
        InputStream inputStream = null;
        try {
            File archivo = pathArchivo.toFile();
            //Esto hara que la path ya no tenga la ruta relativa del equipo cliente
            Path pathArchivoRemoto = new GestorArchivosMusica(cliente.getGestorConfiguracion())
                    .generalizarPathArchivoMusica(pathArchivo);
            //Esto nos devuelve la ruta sin el nombre del archivo
            String pathDirectorio = pathArchivoRemoto.getParent().toString();
            //Para que el server escriba en esa capeta, necesitamos que exista
            ftpClient.changeWorkingDirectory("/");
            ftpClient.changeWorkingDirectory(pathDirectorio);
            int returnCode = ftpClient.getReplyCode();
            if (returnCode == 550) {
                if (crearDirectorio(pathDirectorio)) {
                    ftpClient.changeWorkingDirectory(pathDirectorio);
                }
            }
            inputStream = new FileInputStream(archivo);
            boolean done = ftpClient.storeFile(pathArchivoRemoto.getFileName().toString(), inputStream);
            inputStream.close();
            if (done) {
                return 1;
            } else {
                return 0;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorFTP.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } catch (IOException ex) {
            Logger.getLogger(GestorFTP.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(GestorFTP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean eliminarArchivo(Path pathArchivo) {
        try {
            //Esto hara que la path ya no tenga la ruta relativa del equipo cliente
            Path pathArchivoRemoto = new GestorArchivosMusica(cliente.getGestorConfiguracion())
                    .generalizarPathArchivoMusica(pathArchivo);
            //Esto nos devuelve la ruta sin el nombre del archivo
            String pathDirectorio = pathArchivoRemoto.getParent().toString();
            boolean deleted = ftpClient.deleteFile(pathDirectorio);
            if (deleted) {
                System.out.println("The file was deleted successfully.");
                return true;
            } else {
                System.out.println("Could not delete the  file, it may not exist.");
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(GestorFTP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean crearDirectorio(String pathDirectorio) {
        try {
            String dirToCreate = pathDirectorio;
            Boolean success = ftpClient.makeDirectory(dirToCreate);
            showServerReply(ftpClient);
            if (success) {
                System.out.println("Successfully created directory: " + dirToCreate);
                return true;
            } else {
                System.out.println("Failed to create directory. See server's reply.");
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(GestorFTP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int descargarArchivo(Path pathArchivo) {
        OutputStream outputStream1 = null;
        try {
            String pathArchivoLocal = pathArchivo.toString();
            System.err.println(pathArchivoLocal+ "local");
            String pathArchivoRemoto = new GestorArchivosMusica(cliente.getGestorConfiguracion())
                    .generalizarPathArchivoMusica(pathArchivo).toString();
            System.err.println(pathArchivoLocal + "local");
            File archivo = new File(pathArchivoLocal);
            outputStream1 = new BufferedOutputStream(new FileOutputStream(archivo));
            boolean success = ftpClient.retrieveFile(pathArchivoRemoto, outputStream1);
            outputStream1.close();
            if (success) {
                System.out.println("File has been downloaded successfully.");
                return 1;
            } else {
                return 0;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GestorFTP.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } catch (IOException ex) {
            Logger.getLogger(GestorFTP.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            try {
                outputStream1.close();
            } catch (IOException ex) {
                Logger.getLogger(GestorFTP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
