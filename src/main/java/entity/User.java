package entity;

import java.io.Serializable;
// must be serializable to be attribute on session
public class User implements Serializable {
    private final int id;
    private String login;
    private String email;
    private String password;

    public User(int id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", login='" + login + "', email='" + email + "']";
    }
}
