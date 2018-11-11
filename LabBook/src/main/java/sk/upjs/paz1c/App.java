package sk.upjs.paz1c;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.UserDAO;
import sk.upjs.paz1c.gui.FrontPageController;

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
//    	User oliver = new User();
//    	oliver.setName("Oliver");
//    	oliver.setPassword("1234");
//    	oliver.setEmail("oliver.vahovsky@gmail.com");
//      UserDAO userDAO = DAOfactory.INSTANCE.getUserDAO();
//       userDAO.addUser(oliver);
//        System.out.println(userDAO.getAll());
	}

}
