package model;

import io.ebean.Finder;
import io.ebean.Model;
import org.checkerframework.common.aliasing.qual.Unique;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Constraint;

@Entity
public class User extends Model{
    @Id
    public Integer id;
    @Column(unique = true)
    public String  username;
    public String  password;

    public static final Finder<Integer, User> find = new Finder<>(User.class);


    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //getters
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public Integer getId(){
        return id;
    }

    //setters
    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = hashPassword(password);
    }

    public boolean checkPassword(String password) {
        return BCrypt.checkpw(password, this.password);
    }


}
