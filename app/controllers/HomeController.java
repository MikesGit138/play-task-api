package controllers;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import model.*;
import java.util.List;
import com.google.inject.Inject;


public class HomeController extends Controller {
    
    //constructor
    @Inject
    public HomeController(){

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
    // public Result getTasks(){
    //     // return ok(Json.toJson(Task.allTasks()));
    // }

    // //create task
    // public Result createTask(Http.Request request){
    //     // Task.add(Json.fromJson(request.body().asJson(), Task.class));
    //     // return ok(Json.toJson(Task.allTasks()));
    // }

    // //edit task
    // public Result editTask(Integer id){
    //     return ok();
    // }

    // //update task
    // public Result updateTask(){
    //     return ok();
    // }

    // //delete task
    // public Result deleteTask(Integer id){
    //     // Task.del(id);
    //     // return ok(Json.toJson(Task.allTasks()));
    // }

    
    // //complete task
    // public Result completeTask(Integer id){
    //     // Task.completeTask(id);
    //     // return ok(Json.toJson(Task.allTasks()));
    // }
}
