package controllers;

import com.google.inject.Inject;
import io.ebean.DB;
import model.Task;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;

public class TaskController extends Controller {
    private final play.db.ebean.EbeanConfig ebeanConfig;
    //constructor
    @Inject
    public TaskController(play.db.ebean.EbeanConfig ebeanConfig){
        this.ebeanConfig = ebeanConfig;
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
        return created("task successfully added");
    }
//update task
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

//delete task
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
}
