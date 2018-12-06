package sk.upjs.paz1c.persistent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.entities.User;

public class MysqlTaskDAOTest {

	@Test
	void testGetAll() {
		List<Task> tasks = DAOfactory.INSTANCE.getTaskDAO().getAll();
		assertNotNull(tasks);
		assertTrue(tasks.size() == 3);
		for (Task task : tasks) {
			if (task.getItems() != null)
				for (Item item : task.getItems()) {
					System.out.println("Task " + task.getTaskID() + " ma item " + item.getItemID());
				}
		}
	}

	@Test
	void addDeleteTest() {
		User testUser = new User();
		testUser.setName("testerGetByID");
		testUser.setPassword("1234");
		testUser.setEmail("tester.testovaci@test.com");
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		userDAO.addUser(testUser);
		
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		project.setCreatedBy(testUser);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		projectDAO.addProject(project);

		Task task = new Task();
		task.setProject(project);
		task.setName("testTask");
		task.setActive(true);
		task.setDateTimeFrom(LocalDate.now());
		task.setEachItemAvailable(false);
		task.setCreatedBy(testUser);
		TaskDAO taskDAO = DAOfactory.INSTANCE.getTaskDAO();
		taskDAO.addTask(task);
		boolean succesfullyAdded = false;
		List<Task> all = taskDAO.getAll();
		for (Task t : all) {
			if (t.getTaskID().equals(task.getTaskID())) {
				succesfullyAdded = true;
			}
		}
		assertTrue(succesfullyAdded);

		taskDAO.deleteTask(task);
		all = taskDAO.getAll();
		boolean successfullyDeleted = true;
		for (Task t : all) {
			if (t.getTaskID().equals(task.getTaskID())) {
				successfullyDeleted = false;
			}
		}
		userDAO.deleteUser(testUser);
		assertTrue(successfullyDeleted);
	}

	@Test
	void testSave() {
		User testUser = new User();
		testUser.setName("testerGetByID");
		testUser.setPassword("1234");
		testUser.setEmail("tester.testovaci@test.com");
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		userDAO.addUser(testUser);
		
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		project.setCreatedBy(testUser);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		projectDAO.addProject(project);

		Task task = new Task();
		task.setProject(project);
		task.setName("testTask");
		task.setActive(true);
		task.setDateTimeFrom(LocalDate.now());
		task.setEachItemAvailable(false);
		task.setCreatedBy(testUser);
		TaskDAO taskDAO = DAOfactory.INSTANCE.getTaskDAO();
		// create
		taskDAO.saveTask(task);
		assertNotNull(task.getTaskID());
		task.setName("testovaci_task_new");
		// update
		taskDAO.saveTask(task);
		List<Task> all = taskDAO.getAll();
		for (Task t : all) {
			if (t.getTaskID().equals(task.getTaskID())) {
				assertEquals("testovaci_task_new", t.getName());
				userDAO.deleteUser(testUser);
				return;
			}
		}
		assertTrue(false, "update sa nepodaril");
	}

}
