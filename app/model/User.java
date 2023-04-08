package model;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class User extends Model{
    @Id
    public Integer id;
    public String  username;
    public String  password;

    public static final Finder<Integer, User> find = new Finder<>(User.class);

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
        this.password = password;
    }


}
