package sk.upjs.paz1c;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.User;

public class FrontPageController {

	private User user;
	private UserFxModel userModel;
	
	@FXML
	private PasswordField passwordTextField;
	
	@FXML
	private TextField loginTextField;
	
	@FXML
	private Button signInButton;
	
	@FXML
	private Button registerButton ;
	
	public FrontPageController() {
		
	}
	
	//public FrontPageController(User user) {
	//	this.user = user;
	//	this.userModel = new UserFxModel(user);
	//}
	
	@FXML 
	void initialize() {
		
		registerButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				RegistrationController registrationController = new RegistrationController();            
				showModalWindow(registrationController, "registration.fxml");
			}
		});
		
		signInButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SelectProjectController selectProjectController = new SelectProjectController();            
				showModalWindow(selectProjectController, "selectProject.fxml");				
			}
		});
		
	}
	
	private void showModalWindow(Object controller, String fxml) {
		try {
			FXMLLoader fxmlLoader = new	FXMLLoader(getClass().getResource(fxml));
			fxmlLoader.setController(controller);
			Parent rootPane	= fxmlLoader.load();
			Scene scene	= new Scene(rootPane);
			
			Stage dialog = new Stage();
			dialog.setScene(scene);
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



