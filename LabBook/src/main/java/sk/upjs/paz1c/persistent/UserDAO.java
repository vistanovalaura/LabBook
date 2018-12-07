package sk.upjs.paz1c.persistent;

import java.util.List;

import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.entities.User;

public interface UserDAO {

	// pridanie usera do databazy
	void addUser(User user);

	// vrati vsetkych userov z databazy
	List<User> getAll();

	// zmena usera v databaze
	void saveUser(User user);

	// vymazanie usera z databazy
	void deleteUser(User user);
	
	// vrati usera podla id
	User getByID(Long id);
	
	// vrati tasky daneho usera
	List<Task> getTasks(User user);

}
