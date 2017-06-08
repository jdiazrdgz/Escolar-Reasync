package peticion;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jdiaz
 */
public class Peticion implements Serializable {

    private String peticion;
    private String info;

    public Peticion(String peticion) {
        this.peticion = peticion;
    }

    public Peticion(String peticion, String info) {
        this.peticion = peticion;
        this.info = info;
    }

    public String getPeticion() {
        return peticion;
    }

    public void setPeticion(String peticion) {
        this.peticion = peticion;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
