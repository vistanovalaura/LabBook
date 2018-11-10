package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

	@FXML
    private PasswordField confirmPasswordPasswordField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button finishButton;
    
    @FXML
    private TextField emailAdressTextField;

    @FXML 
    void initialize() {
    	finishButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
	            finishButton.getScene().getWindow().hide();
			}
		});
    }
    
}
