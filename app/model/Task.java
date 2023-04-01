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
   
    
    // public Task (Integer id, String taskTitle, Boolean completed){
    //     this.id = id;
    //     this.taskTitle = taskTitle;
    //     this.completed = completed;
    // }

    //private static Set<Task> tasks;
    public static final Finder<Integer, Task> find = new Finder<>(Task.class);
    public void setTitle(String title){
        this.taskTitle = title;
    }
    public void isComplete(){
        this.completed = !this.completed;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public void getTasks(Task task){
        this.
    }
    // static {
    //     tasks = new HashSet<Task>();
    //     tasks.add(new Task(1, "go running", false));
    //     tasks.add(new Task(2, "do work", false));
    //     tasks.add(new Task(3, "do more work", false));
    //     tasks.add(new Task(4, "go jogging", false));
    //     tasks.add(new Task(5, "go walking", false));
    //     tasks.add(new Task(6, "lift weights", false));
    //     tasks.add(new Task(7, "cook", false));
    // }

//     public static Set<Task> allTasks(){
//         return tasks;
//     }
//     public void setTitle(String tasks){
//         this.taskTitle = tasks;
//    }
//    public static Task findById(Integer id){
//         for (Task task: tasks){
//            if(id.equals(task.id)){
//                return task;
//            }
//         }
//         return null;
//    }

//    public static void add(Task task){
//        tasks.add(task);
//    }

//    public static void completeTask(Integer id){
//        findById(id).completed = !findById(id).completed;
//    }

//    public static void del(Integer id){
//        tasks.remove(findById(id));
//    }

}

