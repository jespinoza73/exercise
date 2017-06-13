package services;

import services.UserService;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.*;

import model.User;

public class UserServiceImpl implements UserService{
	
	private Jongo jongo;

    @Inject
    public UserServiceImpl(Jongo jongo){
    	this.jongo = jongo;
    }
	
	public List<User> findAll(){

		MongoCursor<User> all = null;
		List<User> list = new ArrayList<User>();
		try{
			MongoCollection users = jongo.getCollection("users");
			all = users.find("{}").as(User.class);
			Iterator<User> iterator = all.iterator();
			while(iterator.hasNext()){
				list.add(iterator.next());
			}
		}
		catch(Exception ex){
			//Need a logger
			ex.printStackTrace();
		}
		finally{
			if ( all != null) { 
				try{ all.close(); }	catch(Exception e){}
			}
		}
        return list;
	}
	public User find(String id){
		MongoCollection users = jongo.getCollection("users");
		User user = users.findOne(new ObjectId(id)).as(User.class);
        return user;
	}
	public void update(User user){
		MongoCollection users = jongo.getCollection("users");
    	users.update(new ObjectId(user.getId())).with(user);
	}
	public void delete(String id){
		MongoCollection users = jongo.getCollection("users");
    	users.remove(new ObjectId(id));
	}
	public void insert(User user){
		MongoCollection users = jongo.getCollection("users");
	    users.insert(user);
	}

}