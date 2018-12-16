package sk.upjs.paz1c.business;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Laboratory;
import sk.upjs.paz1c.entities.Note;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ItemDAO;
import sk.upjs.paz1c.persistent.LaboratoryDAO;
import sk.upjs.paz1c.persistent.NoteDAO;
import sk.upjs.paz1c.persistent.ProjectDAO;
import sk.upjs.paz1c.persistent.TaskDAO;
import sk.upjs.paz1c.persistent.UserDAO;

public class test {

	public static void main(String[] args) {
		// USER
		User testUser = new User();
		testUser.setName("tester");
		testUser.setPassword("1234");
		testUser.setEmail("tester.testovaci@test.com");
		UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
		userDAO.addUser(testUser);

		// PROJECT
		Project project = new Project();
		project.setName("testovaci_projekt");
		project.setActive(true);
		project.setDateFrom(LocalDate.now());
		project.setEachItemAvailable(false);
		project.setCreatedBy(testUser);
		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
		projectDAO.addProject(project);

		// TASK 1 
		Laboratory testLaboratory = new Laboratory();
		testLaboratory.setName("tester");
		testLaboratory.setLocation("testovacia");
		LaboratoryDAO laboratoryDAO = DAOfactory.INSTANCE.getLaboratoryDAO();
		laboratoryDAO.addLaboratory(testLaboratory);

		Item testItem = new Item();
		testItem.setName("test_item1");
		testItem.setQuantity(10);
		testItem.setAvailable(true);
		testItem.setLaboratory(testLaboratory);

		Item testItem2 = new Item();
		testItem2.setName("test_item2");
		testItem2.setQuantity(10);
		testItem2.setAvailable(true);

		Item testItem3 = new Item();
		testItem3.setName("test_item3");
		testItem3.setQuantity(10);
		testItem3.setAvailable(true);

		ItemDAO itemDAO = DAOfactory.INSTANCE.getItemDAO();
		itemDAO.addItem(testItem);
		itemDAO.addItem(testItem2);
		itemDAO.addItem(testItem3);

		Task task = new Task();
		task.setProject(project);
		task.setName("testTask");
		task.setActive(true);
		task.setDateTimeFrom(LocalDate.now());
		task.setEachItemAvailable(false);
		task.setCreatedBy(testUser);
		task.setItems(Arrays.asList(testItem, testItem2, testItem3));
		TaskDAO taskDAO = DAOfactory.INSTANCE.getTaskDAO();
		taskDAO.addTask(task);
		
		Task task1 = new Task();
		task1.setProject(project);
		task1.setName("testTask");
		task1.setActive(true);
		task1.setDateTimeFrom(LocalDate.now());
		task1.setEachItemAvailable(false);
		task1.setCreatedBy(testUser);
		task1.setItems(Arrays.asList(testItem, testItem2, testItem3));
		taskDAO.addTask(task1);


		// NOTE
		Note note = new Note();
		note.setText("testovaci text");
		note.setTimestamp(LocalDateTime.now());
		note.setAuthor(testUser);
		note.setProject(project);
		NoteDAO noteDAO = DAOfactory.INSTANCE.getNoteDAO();
		noteDAO.addNote(note);

		// test
		try {
			ExportUserDataToExcelManager.exportUserData(testUser);

			// https://stackoverflow.com/questions/6896435/count-number-of-worksheets-in-excel-file
			// String fileName = "userData.xlsx";
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("userData.xlsx"));
			// assertTrue(workbook.getNumberOfSheets() == 2);
			// assertTrue(true);
			System.out.println(workbook.getNumberOfSheets());
			System.out.println(workbook.getSheetAt(0).getPhysicalNumberOfRows());
			System.out.println(workbook.getSheetAt(1).getPhysicalNumberOfRows());
			System.out.println(workbook.getSheetAt(2).getPhysicalNumberOfRows());

			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// delete
		itemDAO.deleteItem(testItem);
		itemDAO.deleteItem(testItem2);
		itemDAO.deleteItem(testItem3);
		laboratoryDAO.deleteLaboratory(testLaboratory);

		noteDAO.deleteNote(note);
		projectDAO.deleteProject(project);

		taskDAO.deleteTask(task);
		taskDAO.deleteTask(task1);
		userDAO.deleteUser(testUser);
		userDAO.deleteUser(testUser);
	}
}
