package sk.upjs.paz1c.business;

import org.junit.jupiter.api.Test;

import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.UserDAO;

public class UserIdentificationManagerTest {
	
	@Test
	void getUserTest() {
		User testUser = new User();
		testUser.setName("tester");
		testUser.setPassword("1234");
		testUser.setEmail("tester.testovaci@test.com");
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		userDAO.addUser(testUser);
		
		
	}

}
