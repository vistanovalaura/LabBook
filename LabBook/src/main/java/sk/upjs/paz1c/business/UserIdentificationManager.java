package sk.upjs.paz1c.business;

import java.util.List;

import sk.upjs.paz1c.entities.Admin;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.persistent.DAOfactory;

public class UserIdentificationManager {

	private static int typeOfUser;
    private static Long id;

    public static int getTypeOfUser() {
        return typeOfUser;
    }

    public static Long getId() {
        return id;
    }

  
    public static int setUser(String userName, String password) {
        List<User> users = DAOfactory.INSTANCE.getUserDAO().getAll();
        for (User user : users) {
            if (user.getName().equals(userName) && user.getPassword().equals(password)) {
                UserIdentificationManager.typeOfUser = 1;
                UserIdentificationManager.id = user.getUserID();
                return 1;
            }
        }
        List<Admin> admins = DAOfactory.INSTANCE.getAdminDAO().getAll();
        for (Admin admin : admins) {
            if (admin.getName().equals(userName) && admin.getPassword().equals(password)) {
                UserIdentificationManager.typeOfUser = 2;
                UserIdentificationManager.id = admin.getAdminID();
                return 2;
            }
        }
        return -1;
    }

    public static void logOut() {
        UserIdentificationManager.typeOfUser = 0;
        UserIdentificationManager.id = null;
    }

}
