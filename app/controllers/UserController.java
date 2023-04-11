package controllers;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import io.ebean.DB;
import model.*;
import java.util.List;
import com.google.inject.Inject;

public class UserController extends Controller{

    //getusers
    public Result getUsers(){
        List<User> users = User.find.all();
        if (users.size() == 0)
            return ok("You have no users as yet");
        else
            return ok(Json.toJson(users));
    }
    public Result createUser(Http.Request request){
        //only create username when user does not exist
        User newUser = Json.fromJson(request.body().asJson(), User.class);
        User userToCreate = DB.find(User.class)
                .where().eq("username", newUser.getUsername())
                .findOne();
        if (userToCreate == null) {
            DB.save(newUser);
            return ok("User successfully added");
        } else {
            return ok("User already exists");
        }
    }


    //userlogin
    public Result userLogin(Http.Request request){
        User user = Json.fromJson(request.body().asJson(), User.class);
        User userToLogin = DB.find(User.class)
                .where().eq("username", user.getUsername())
                .findOne();
        if (userToLogin != null) {
            if (userToLogin.checkPassword(request.body().asJson().get("password").asText())) {
                return ok("User logged in");
            } else {
                return unauthorized("User password is incorrect");

            }
        } else {
            return notFound("User not found");
        }
    }
}
