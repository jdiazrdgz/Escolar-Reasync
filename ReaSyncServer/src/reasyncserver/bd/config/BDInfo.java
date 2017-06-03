package reasyncserver.bd.config;

/**
 *
 * @author jdiaz
 */
public class BDInfo {

    private String userName;
    private String password;
    private String urlbd;

    public BDInfo(String userName, String password, String urlbd) {
        this.userName = userName;
        this.password = password;
        this.urlbd = urlbd;
    }

    public BDInfo() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlbd() {
        return urlbd;
    }

    public void setUrlbd(String urlbd) {
        this.urlbd = urlbd;
    }

}
