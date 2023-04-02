package model;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Task extends Model{
    public String  taskTitle;
    public Boolean completed;
    @Id
    public Integer id;

    public static final Finder<Integer, Task> find = new Finder<>(Task.class);
    //getters
    public String getTaskTitle(){
        return taskTitle;
    }
    public Integer getId(){
        return id;
    }
    public Boolean getCompleted(){
        return completed;
    }
    //setters
    public void setTitle(String taskTitle){
        this.taskTitle = taskTitle;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public void setIsComplete(){
        this.completed = !this.completed;
    }


}

