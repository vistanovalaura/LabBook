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

    //Teacher is 1
    //Admin is 2
    //SuperAdmin is 3
    public static boolean setUser(String userName) {
        List<User> users = DAOfactory.INSTANCE.getUserDAO().getAll();
        for (User user : users) {
            if (user.getName().equals(userName)) {
                UserIdentificationManager.typeOfUser = 1;
                UserIdentificationManager.id = user.getUserID();
                return true;
            }
        }
//        List<Admin> admins = DaoFactory.INSTANCE.getAdminDao().getAll();
//        for (Admin admin : admins) {
//            if (admin.getName().equals(userName)) {
//                UserIdentificationManager.typeOfUser = 2;
//                UserIdentificationManager.id = admin.getAdminID();
//                return true;
//            }
//        }
        return false;
    }

    public static void logOut() {
        UserIdentificationManager.typeOfUser = 0;
        UserIdentificationManager.id = null;
    }

}
