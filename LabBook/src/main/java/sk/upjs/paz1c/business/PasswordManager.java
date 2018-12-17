package sk.upjs.paz1c.business;

import java.util.List;

import sk.upjs.paz1c.entities.Admin;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.persistent.DAOfactory;

public class PasswordManager {

	public boolean isCorrectPassword(String password, Long id, int UserType) {
		if (UserType == 1) {
			List<User> users = DAOfactory.INSTANCE.getUserDAO().getAll();
			for (User user : users) {
				if (user.getUserID().equals(id)) {
					return password.equals(user.getPassword());
				}
			}
		} else {
			List<Admin> admins = DAOfactory.INSTANCE.getAdminDAO().getAll();
			for (Admin admin : admins) {
				if (admin.getAdminID().equals(id)) {
					return password.equals(admin.getPassword());
				}
			}
		}
		return false;
	}
}
