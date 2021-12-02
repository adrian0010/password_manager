package pass_manager.password_manager;

import java.io.Serializable;

public class Account implements Serializable {

    private final String username;
    private final String password;

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }
    public boolean login(Account account){
        return (this.username.equals(account.username) && this.password.equals(account.password));
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
