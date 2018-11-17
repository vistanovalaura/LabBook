package sk.upjs.paz1c.persistent;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import sk.upjs.paz1c.entities.Project;

public class MysqlProjectDAOTest {

	@Test
	void testGetAll() {
		List<Project> projects = DAOfactory.INSTANCE.getProjectDAO().getAll();
		assertNotNull(projects);
		assertTrue(projects.size() > 0);
	}

	@Test
	void addDeleteTest() {
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		boolean notThere = true;
		List<Project> all = projectDAO.getAll();
		for (Project p : all) {
			if (p.getProjectID() == project.getProjectID()) {
				notThere = false;
			}
		}
		assertTrue(notThere);

		projectDAO.addProject(project);
		all = projectDAO.getAll();
		boolean succesfullyAdded = false;
		for (Project p : all) {
			if (p.getProjectID() == project.getProjectID()) {
				succesfullyAdded = true;
			}
		}
		assertTrue(succesfullyAdded);

		projectDAO.deleteProject(project);
		all = projectDAO.getAll();
		boolean successfullyDeleted = true;
		for (Project p : all) {
			if (p.getProjectID() == project.getProjectID()) {
				successfullyDeleted = false;
			}
		}
		assertTrue(successfullyDeleted);
	}

	@Test
	void testGetByName() {
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		projectDAO.addProject(project);
		assertNotNull(projectDAO.getByName("testovaci_projekt"));

		projectDAO.deleteProject(project);
		assertNull(projectDAO.getByName("testovaci_projekt"));
	}

	@Test
	void testSave() {
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		// create
		projectDAO.saveProject(project);
		assertNotNull(project.getProjectID());
		project.setName("testovaci_projekt_new");
		// update
		projectDAO.saveProject(project);
		List<Project> all = projectDAO.getAll();
		for (Project p : all) {
			if (p.getProjectID() == project.getProjectID()) {
				assertEquals("testovaci_projekt_new", p.getName());
				projectDAO.deleteProject(p);
				return;
			}
		}
		assertTrue(false, "update sa nepodaril");
	}

}
