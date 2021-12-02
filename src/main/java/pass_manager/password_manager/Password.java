package pass_manager.password_manager;

import java.io.Serializable;

public class Password implements Serializable {
    private final String site;
    private final String username;
    private final String password;

    public Password(String site, String username, String password){
        this.site = site;
        this.username = username;
        this.password = password;
    }

    public String getSite() {
        return site;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
