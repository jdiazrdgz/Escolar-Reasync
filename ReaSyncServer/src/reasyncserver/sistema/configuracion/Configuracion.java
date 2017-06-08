package reasyncserver.sistema.configuracion;

import java.io.Serializable;

/**
 *
 * @author jdiaz
 */
public class Configuracion implements Serializable {

    private ServerInfo serverInfo;

    public Configuracion(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    public Configuracion() {
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

}
