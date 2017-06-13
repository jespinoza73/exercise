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

/**
 * This controller contains actions to handle HTTP requests
 * coming from user management ui module
 */
public class UserController extends Controller {

    private Jongo jongo;

    @Inject
    public UserController(Jongo jongo){
    	this.jongo = jongo;
    }

	public Result index() {
		return ok(views.html.index.render());
	}
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result list() {
    	MongoCollection users = jongo.getCollection("users");
		MongoCursor<User> all = users.find("{}").as(User.class);
        return ok(Json.toJson(all));
    }

    public Result getUser(String userId) {
    	MongoCollection users = jongo.getCollection("users");
		User user = users.findOne(new ObjectId(userId)).as(User.class);
        return ok(Json.toJson(user));
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result save() {
    	JsonNode json = request().body().asJson(); 
    	if ( json != null){
    		User user = Json.fromJson(json, User.class);
    		MongoCollection users = jongo.getCollection("users");
    		if(user.getId() == null){
	    		users.insert(user);
    		}
    		else{
    			users.update(new ObjectId(user.getId())).with(user);
    		}
    	}

        return ok("ok");
    }


}
