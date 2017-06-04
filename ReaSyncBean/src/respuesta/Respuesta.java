package respuesta;

import java.io.Serializable;

public class Respuesta implements Serializable{
    private String respuesta;

    public Respuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }
}
