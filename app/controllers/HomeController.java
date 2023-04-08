package controllers;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
//import io.ebean.Ebean;
import io.ebean.DB;
import model.*;
import java.util.List;
import com.google.inject.Inject;


public class HomeController extends Controller {

    private final play.db.ebean.EbeanConfig ebeanConfig;
    //constructor
    @Inject
    public HomeController(play.db.ebean.EbeanConfig ebeanConfig){
        this.ebeanConfig = ebeanConfig;
    }

    public Result index() {
        
        return ok("This is the index page").as("text/html");
    }

    public Result checker(Http.Request request){
        
        return ok("Your request path is " + request);
    }

    public Result handler(String myName){
        return ok(myName + " went to this page");
    }

    //get tasks
     public Result getTasks(){
         List<Task> tasks = Task.find.all();
         if (tasks.size() == 0)
            return ok("You have no tasks as yet");
         else
             return ok(Json.toJson(tasks));
     }

    //create task
     public Result createTask(Http.Request request){
         Task newtask = Json.fromJson(request.body().asJson(), Task.class);
         if (ebeanConfig == null) {
             return internalServerError("Ebean configuration is missing");
         }
         DB.save(newtask);
         return ok("task successfully added");
     }

    public Result updateTask(Http.Request request, Integer id) {
        Task updatedTask = Json.fromJson(request.body().asJson(), Task.class);
        Task taskToUpdate = DB.find(Task.class, id);
        if (taskToUpdate != null) {
            taskToUpdate.setId(id);
            taskToUpdate.setTitle(updatedTask.getTaskTitle());
            taskToUpdate.update();
            return ok("Task of id " + id + " updated");
        } else {
            return notFound("Task not found");
        }
    }


    public Result deleteTask(Integer id) {
        Task taskToDelete = DB.find(Task.class, id);
        if (taskToDelete != null) {
            taskToDelete.delete();
            return ok("Task of id: " + id +" deleted");
        } else {
            return notFound("Task of id: " +id + " not found");
        }
    }



    //complete task
     public Result completeTask(Integer id) {
         Task taskToComplete = DB.find(Task.class, id);
         if (taskToComplete != null) {
             taskToComplete.setIsComplete();
             taskToComplete.update();
             return ok("Task of id: " + id + " completed is changed");
         } else {
             return notFound("Task of id: " + id + " not found");
         }
     }
    //getusers
     public Result getUsers(){
         List<User> users = User.find.all();
         if (users.size() == 0)
             return ok("You have no users as yet");
         else
             return ok(Json.toJson(users));
     }
     public Result createUser(Http.Request request){
         User newUser = Json.fromJson(request.body().asJson(), User.class);
         if (ebeanConfig == null) {
             return internalServerError("Ebean configuration is missing");
         }
         DB.save(newUser);
         return ok("user successfully added");
     }

     //userlogin
        public Result userLogin(Http.Request request){
            User user = Json.fromJson(request.body().asJson(), User.class);
            User userToLogin = DB.find(User.class)
                    .where().eq("username", user.getUsername())
                    .findOne();
            if (userToLogin != null) {
                if (userToLogin.getPassword().equals(user.getPassword())) {
                    return ok("User logged in");
                } else {
                    return ok("User password incorrect");
                }
            } else {
                return notFound("User not found");
            }
        }
}
