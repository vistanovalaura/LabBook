package sk.upjs.paz1c.gui;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ProjectDAO;
import sk.upjs.paz1c.persistent.UserDAO;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FrontPageController mainController = new FrontPageController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("frontPage.fxml"));
		fxmlLoader.setController(mainController);
		Parent rootPane = fxmlLoader.load();

		Scene scene = new Scene(rootPane);
		primaryStage.setTitle("LabBook login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
//		Project project = DAOfactory.INSTANCE.getProjectDAO().getByName("urobenie save");
//		project.setName("urobenie_save");
//		DAOfactory.INSTANCE.getProjectDAO().saveProject(project);
//		System.out.println(DAOfactory.INSTANCE.getProjectDAO().getByName("urobenie_save"));
		
//    	User oliver = new User();
//    	oliver.setName("Laura");
//    	oliver.setPassword("2357");
//    	oliver.setEmail("laura.vistanova@gmail.com");
//    	UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
//    	userDAO.addUser(oliver);
//    	System.out.println(userDAO.getAll());
//
//
//		Project project = new Project();
//		project.setName("spravenie save");
//		project.setActive(true);
//		project.setDateFrom(LocalDate.now());
//		project.setDateUntil(LocalDate.now());
//		project.setEachItemAvailable(true);
//		ProjectDAO projectDAO = DAOfactory.INSTANCE.getProjectDAO();
//		projectDAO.saveProject(project);
//		project.setName("urobenie save");
//		projectDAO.saveProject(project);
//		
//		project = new Project();
//		project.setName("nezaspat");
//		project.setActive(true);
//		project.setDateFrom(LocalDate.now());
//		project.setEachItemAvailable(false);
//		projectDAO.addProject(project);
//		System.out.println(projectDAO.getAll());
	}

}
