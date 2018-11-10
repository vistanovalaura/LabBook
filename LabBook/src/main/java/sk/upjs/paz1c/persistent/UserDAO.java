package sk.upjs.paz1c.persistent;

import java.util.List;

import sk.upjs.paz1c.entities.User;

public interface UserDAO {
	
	void addUser(User user);
	
	List<User> getAll();

}
