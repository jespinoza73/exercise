package services;

import model.User;
import java.util.List;

public interface UserService{
	
	public List<User> findAll();
	public User find(String id);
	public void update(User user);
	public void delete(String id);
	public void insert(User user);
}