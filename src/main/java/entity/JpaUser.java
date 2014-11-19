package entity;



import javax.persistence.*;
import java.io.Serializable;

// must be serializable to be attribute on session
//@NamedQuery(
//        name = "findAllCustomersWithEmailLike",
//        query = "SELECT u FROM JpaUser u WHERE email LIKE :email"
//)
//@Cacheable
@Entity
@Table( name = "jpausers")
public class JpaUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 6, nullable = false)
    private int id;
    @Column(name = "login", length = 32)
    private String login;
    @Column(name = "email", length = 32)
    private String email;
    @Column(name = "password")
    private String password;


    public JpaUser(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
    public JpaUser(){

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
    public User toUser(){
        return new User(id, login, password, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpaUser jpaUser = (JpaUser) o;

        if (id != jpaUser.id) return false;
        if (email != null ? !email.equals(jpaUser.email) : jpaUser.email != null) return false;
        if (login != null ? !login.equals(jpaUser.login) : jpaUser.login != null) return false;
        if (password != null ? !password.equals(jpaUser.password) : jpaUser.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "JpaUser[id=" + id + ", login='" + login + "', email='" + email + "']";
    }
}
