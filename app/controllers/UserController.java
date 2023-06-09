package controllers;

//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
import io.ebean.DB;
import io.jsonwebtoken.*;
import model.User;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Date;
import java.util.List;


public class UserController extends Controller {

    //getusers
    public Result getUsers() {
        List<User> users = User.find.all();
        if (users.size() == 0)
            return notFound("You have no users as yet");
        else
            return ok(Json.toJson(users));
    }

    public Result createUser(Http.Request request) {
        //only create username when user does not exist
        User newUser = Json.fromJson(request.body().asJson(), User.class);
        User userToCreate = DB.find(User.class)
                .where().eq("username", newUser.getUsername())
                .findOne();
        if (userToCreate == null) {
            DB.save(newUser);
            return created("User successfully added");
        } else {
            return badRequest("User already exists");
        }
    }


    //userlogin
    public Result userLogin(Http.Request request) {
        User user = Json.fromJson(request.body().asJson(), User.class);
        User userToLogin = DB.find(User.class)
                .where().eq("username", user.getUsername())
                .findOne();
        if (userToLogin != null) {
            if (userToLogin.checkPassword(request.body().asJson().get("password").asText())) {
                // Create a token
                String token = Jwts.builder()
                        .setIssuer("artuvic-tasks")
                        .setSubject("user")
                        .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                        .claim("username", userToLogin.getUsername())
                        .claim("id", userToLogin.getId())
                        .signWith(SignatureAlgorithm.HS256, "secret")
                        .compact();
                // Verify the token
                try {
                    Jws<Claims> claims = Jwts.parser()
                            .setSigningKey("secret")
                            .parseClaimsJws(token);
                    String issuer = claims.getBody().getIssuer();
                    String subject = claims.getBody().getSubject();
                    Date expiration = claims.getBody().getExpiration();
                    if (!"artuvic-tasks".equals(issuer))
                        return unauthorized("Invalid token issuer");
                    if (!"user".equals(subject))
                        return unauthorized("Invalid token subject");
                    if (expiration.before(new Date()))
                        return unauthorized("Token has expired");
                } catch (JwtException e) {
                    return unauthorized("Invalid token");
                }
                if (verifyToken(token, "secret"))
                    // Return the token as a response
                    return ok(Json.toJson(token));
                else
                    //token signature not recognized
                    return unauthorized("Token unknown");
            } else {
                return unauthorized("User password is incorrect");
            }
        } else {
            return notFound("User not found");
        }
    }


    //check token signature
    public static boolean verifyToken(String token, String secret) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}
