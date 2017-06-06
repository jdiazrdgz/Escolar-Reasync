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
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import reasync.cliente.Client;
import reasync.sistema.archivos.GestorArchivosMusica;

/**
 *
 * @author jdiaz
 */
public class GestorFTP {

    private final Client cliente;
    private final String ipserver;
    private final int port;
    private final String user = "reasync";
    private final String pass = "jenesais53";
    private final FTPClient ftpClient;

    public GestorFTP(Client cliente, String ipserver, int port) {
        this.cliente = cliente;
        this.ipserver = ipserver;
        this.port = port;
        ftpClient = new FTPClient();
    }

    public int conectarClienteFTP() {
        try {
            ftpClient.connect(ipserver, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return 1;
        } catch (IOException ex) {
            Logger.getLogger(GestorFTP.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int desconectarClienteFTP() {
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
                return 1;
            }
            return 1;
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public int subirArchivo(Path pathArchivo) {
        InputStream inputStream = null;
        try {
            File archivo = pathArchivo.toFile();
            String pathArchivoRemoto = new GestorArchivosMusica(cliente.getGestorConfiguracion())
                    .generalizarPathArchivoMusica(pathArchivo).toString();
            inputStream = new FileInputStream(archivo);
            System.out.println("Start uploading the file");
            boolean done = ftpClient.storeFile(pathArchivoRemoto, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
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

    public int descargarArchivo(Path pathArchivo) {
        OutputStream outputStream1 = null;
        try {
            String pathArchivoRemoto = pathArchivo.toString();
            String pathArchivoLocal = new GestorArchivosMusica(cliente.getGestorConfiguracion())
                    .especificarPathArchivoMusica(pathArchivo).toString();
            File archivo = new File(pathArchivoLocal);
            outputStream1 = new BufferedOutputStream(new FileOutputStream(archivo));
            boolean success = ftpClient.retrieveFile(pathArchivoRemoto, outputStream1);
            outputStream1.close();
            if (success) {
                System.out.println("File has been downloaded successfully.");
                return 1;
            }else{
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
