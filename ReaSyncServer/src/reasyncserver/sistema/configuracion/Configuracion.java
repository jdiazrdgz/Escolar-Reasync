package reasyncserver.sistema.configuracion;

import java.io.Serializable;
import reasyncserver.bd.config.BDInfo;
import reasyncserver.server.config.ServerInfo;

/**
 *
 * @author jdiaz
 */
public class Configuracion implements Serializable {

    private BDInfo bdinfo;

    private ServerInfo serverInfo;

    public Configuracion(BDInfo bdinfo, ServerInfo serverInfo) {
        this.bdinfo = bdinfo;
        this.serverInfo = serverInfo;
    }

    public Configuracion() {
    }

    public BDInfo getBdinfo() {
        return bdinfo;
    }

    public void setBdinfo(BDInfo bdinfo) {
        this.bdinfo = bdinfo;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

}
