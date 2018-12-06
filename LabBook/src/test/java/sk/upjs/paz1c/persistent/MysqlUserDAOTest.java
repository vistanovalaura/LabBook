package sk.upjs.paz1c.persistent;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.paz1c.entities.Note;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.entities.User;

class MysqlUserDAOTest {

	@Test
	void testGetAll() {
		List<User> users = DAOfactory.INSTANCE.getUserDAO().getAll();
		assertNotNull(users);
		assertTrue(users.size() > 0);
	}
	
	@Test
	void addDeleteTest() {
		User testUser = new User();
		testUser.setName("tester");
		testUser.setPassword("1234");
		testUser.setEmail("tester.testovaci@test.com");
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		
		boolean notThere = true;
		List<User> all = userDAO.getAll();
		for (User u : all) {
			if (u.getUserID().equals(testUser.getUserID())) {
				notThere = false;
			}
		}
		assertTrue(notThere);

		userDAO.addUser(testUser);
		
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		project.setCreatedBy(testUser);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		projectDAO.addProject(project);
		
		Note note = new Note();
		note.setText("testovaci text");
		note.setTimestamp(LocalDateTime.now());
		note.setAuthor(testUser);
		note.setProject(project);
		NoteDAO noteDAO = DAOfactory.INSTANCE.getNoteDAO();
		noteDAO.addNote(note);
		
		Task task = new Task();
		task.setProject(project);
		task.setName("task taskovity");
		task.setActive(true);
		task.setEachItemAvailable(true);
		task.setCreatedBy(testUser);
		TaskDAO taskDao = DAOfactory.INSTANCE.getTaskDAO();
		taskDao.addTask(task);
		
		all = userDAO.getAll();
		boolean succesfullyAdded = false;
		for (User u : all) {
			if (u.getUserID().equals(testUser.getUserID())) {
				succesfullyAdded = true;
			}
		}
		assertTrue(succesfullyAdded);

		userDAO.deleteUser(testUser);
		all = userDAO.getAll();
		boolean successfullyDeleted = true;
		for (User u : all) {
			if (u.getUserID().equals(testUser.getUserID())) {
				successfullyDeleted = false;
			}
		}
		assertTrue(successfullyDeleted);
	}
	
	@Test
	void testSave() {
		User testUser = new User();
		testUser.setName("tester");
		testUser.setPassword("1234");
		testUser.setEmail("tester.testovaci@test.com");
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		// create
		userDAO.saveUser(testUser);
		assertNotNull(testUser.getUserID());
		// update
		testUser.setName("tester_new");
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		project.setCreatedBy(testUser);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		projectDAO.addProject(project);
		
		userDAO.saveUser(testUser);
		List<User> all = userDAO.getAll();
		for (User u : all) {
			if (u.getUserID().equals(testUser.getUserID())) {
				assertEquals("tester_new", u.getName());
				assertTrue(project.getCreatedBy().getName().equals(u.getName()));
				userDAO.deleteUser(u);
				return;
			}
		}
		assertTrue(false, "update sa nepodaril");
	}
	
	@Test
	void testGetByID() {
		User testUser = new User();
		testUser.setName("testerGetByID");
		testUser.setPassword("1234");
		testUser.setEmail("tester.testovaci@test.com");
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		userDAO.addUser(testUser);
	
		long id = testUser.getUserID();
		assertTrue(id == userDAO.getByID(id).getUserID());
		userDAO.deleteUser(testUser);
	}
}
