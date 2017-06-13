package controllers;

import play.mvc.*;

import views.html.*;

import model.User;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

import services.UserService;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * This controller contains actions to handle HTTP requests
 * coming from user management ui module
 */
public class UserController extends Controller {

    private static final String OKAY = "ok";
    private static final String ERROR = "err";

    private UserService service;

    @Inject
    public UserController(UserService service){
    	this.service = service;
    }

    /**
     * An action that renders and send the main/index page
     
     */
	public Result index() {
		return ok(views.html.index.render());
	}
    /**
     * An action that returns all users saved in db
     
     */
    public Result list() {
    	List<User> all = service.findAll();
        return ok(Json.toJson(all));
    }

    /**
     * An action that returns a user given an Id
     
     */
    public Result getUser(String userId) {
    	User user = service.find(userId);
        return ok(Json.toJson(user));
    }

    /**
     * An action that insert or update a user.
     
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result save() {
        String result = OKAY;
        String msg = "";
    	try{
        	JsonNode json = request().body().asJson(); 
        	if ( json != null){
        		User user = Json.fromJson(json, User.class);
                //** We can have something more generic or Use a framework validator.
                msg = validate(user);
        		if(user.getId() == null){
    	    		service.insert(user);
        		}
        		else{
        			service.update(user);
        		}
        	}
            else{
                result = ERROR;
            }
        }
        catch(Exception ex){
            //TODO replace with Logger later
            ex.printStackTrace();
            result = ERROR;
        }
        return ok(message(result,msg));
    }

  /**
     * An action that deletes a user given an Id
     
     */
    public Result delete(String userId) {
        String result = OKAY;
        try{
            service.delete(userId);
        }
        catch(Exception ex){
            result = ERROR;
        }
        return ok(message(result));
    }



    // All this below can be moved to another package.
    private static String validate(User user){
        String msg = "";
        //TODO we can plug another validator framework.
        if ( user.getEmailAddress().indexOf("@") < 0){ 
            msg = "Wrong Email Address";
        }
        return msg;
    }

    private static JsonNode message(String result, String msg){
        Map<String, String> map = new HashMap<String,String>();
        map.put("result", result);
        map.put("msg",msg);
        return Json.toJson(map);
    } 

    private static JsonNode message(String result){
      return message(result,"");
    } 
}
