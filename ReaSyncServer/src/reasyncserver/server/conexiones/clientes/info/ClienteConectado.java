package reasyncserver.server.conexiones.clientes.info;

/**
 *
 * @author jdiaz
 */
public class ClienteConectado {
    private String nombre;
    private String ip;
    private String id;

    public ClienteConectado() {
    }

    public ClienteConectado(String id, String ip, String nombre) {
        this.nombre = nombre;
        this.ip = ip;
        this.id = id;
    }

    public ClienteConectado(String ip, String id) {
        this.ip = ip;
        this.id = id;
    }    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
}
