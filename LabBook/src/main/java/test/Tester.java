package test;

import java.util.List;

import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.UserDAO;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		List<User> users = userDAO.getAll();
		System.out.println(users.size());
	}

}
