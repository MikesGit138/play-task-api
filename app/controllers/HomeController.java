package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;


public class HomeController extends Controller {

    public Result index() {
        
        return ok("This is the index page").as("text/html");
    }

    public Result checker(Http.Request request){
        
        return ok("Your request path is " + request);
    }

    public Result handler(String myName){
        return ok(myName + " went to this page");
    }


}
