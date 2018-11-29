package sk.upjs.paz1c.persistent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.Task;

public class MysqlTaskDAOTest {
	
	@Test
	void testGetAll() {
		List<Task> tasks = DAOfactory.INSTANCE.getTaskDAO().getAll();
		assertNotNull(tasks);
		assertTrue(tasks.size() > 0);
	}
	
	@Test
	void addDeleteTest() {
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		projectDAO.addProject(project);
		
		Task task = new Task();
		task.setProject(project);
		task.setName("testTask");
		task.setActive(true);
		task.setDateTimeFrom(LocalDate.now());
		task.setEachItemAvailable(false);
		TaskDAO taskDAO = DAOfactory.INSTANCE.getTaskDAO();
		taskDAO.addTask(task);
		boolean succesfullyAdded = false;
		List<Task> all = taskDAO.getAll();
		for (Task t : all) {
			if (t.getTaskID() == task.getTaskID()) {
				succesfullyAdded = true;
			}
		}
		assertTrue(succesfullyAdded);

		taskDAO.deleteTask(task);
		all = taskDAO.getAll();
		boolean successfullyDeleted = true;
		for (Task t : all) {
			if (t.getTaskID() == task.getTaskID()) {
				successfullyDeleted = false;
			}
		}
		assertTrue(successfullyDeleted);
	}
	
	@Test
	void testSave() {
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		projectDAO.addProject(project);
		
		Task task = new Task();
		task.setProject(project);
		task.setName("testTask");
		task.setActive(true);
		task.setDateTimeFrom(LocalDate.now());
		task.setEachItemAvailable(false);
		TaskDAO taskDAO = DAOfactory.INSTANCE.getTaskDAO();
		// create
		taskDAO.saveTask(task);
		assertNotNull(task.getTaskID());
		task.setName("testovaci_task_new");
		// update
		taskDAO.saveTask(task);
		List<Task> all = taskDAO.getAll();
		for (Task t : all) {
			if (t.getTaskID() == task.getTaskID()) {
				assertEquals("testovaci_task_new", t.getName());
				taskDAO.deleteTask(t);
				return;
			}
		}
		assertTrue(false, "update sa nepodaril");
	}

}
