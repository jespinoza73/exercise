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
    	try{
        	JsonNode json = request().body().asJson(); 
        	if ( json != null){
        		User user = Json.fromJson(json, User.class);
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
        return ok(result);
    }


}
