package reasync.sistema.archivos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jdiaz
 */
public enum FormatoMusica {
    MP3("mp3"),
    WAV("wav");

    private static class Holder {

        static Map<String, FormatoMusica> MAP = new HashMap<>();
    }

    private FormatoMusica(String formato) {
        Holder.MAP.put(formato, this);
    }

    public static boolean find(String val) {
        FormatoMusica f = Holder.MAP.get(val);
        if (f != null) {
            return true;
        }else{
           return false; 
        }
    }
}
