package sk.upjs.paz1c.persistent;

import java.util.List;

import sk.upjs.paz1c.entities.Admin;

public interface AdminDAO {

	// pridanie admina do databazy
	void addAdmin(Admin admin);

	// vrati vsetkych adminov z databazy
	List<Admin> getAll();

	// zmena admina v databaze
	void saveAdmin(Admin admin);

	// vymazanie admina z databazy
	void deleteAdmin(Admin admin);

}
