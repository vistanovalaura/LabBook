package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.UserDAO;

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
				/*String name = nameTextField.getText();
				String email = emailAdressTextField.getText();
				String password1 = passwordPasswordField.getText();
				String password2 = confirmPasswordPasswordField.getText();
				if (password1 == password2) {
					User user = new User(name, password1, email);
					// UserDAO userDao = new DAOfactory.INSTANCE.getUserDAO();
					// userDao.addUser(user);
					finishButton.getScene().getWindow().hide();
				}*/
				finishButton.getScene().getWindow().hide();

			}
		});
	}

}
