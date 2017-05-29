package peticion;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author jdiaz
 */
public class Peticion implements Serializable {

    private String peticion;
    private ArrayList<Object> informacion;

    public Peticion(String peticion) {
        this.peticion = peticion;
    }

    public String getPeticion() {
        return peticion;
    }

    public void setPeticion(String peticion) {
        this.peticion = peticion;
    }

    public ArrayList<Object> getInformacion() {
        return informacion;
    }

    public void setInformacion(ArrayList<Object> informacion) {
        this.informacion = informacion;
    }
}
