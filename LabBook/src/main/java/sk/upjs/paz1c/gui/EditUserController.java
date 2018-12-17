package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.fxmodels.UserFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.UserDAO;

public class EditUserController {

	private UserDAO userDao = DAOfactory.INSTANCE.getUserDAO();
	private UserFxModel userModel;

	@FXML
	private Button saveButton;

	@FXML
	private PasswordField newPasswordPasswordField;

	@FXML
	private PasswordField confirmNewPasswordPasswordField;

	@FXML
	private TextField emailTextField;

	@FXML
	private TextField nameTextField;

	public EditUserController(User user) {
		this.userModel = new UserFxModel(user);
	}

	@FXML
	void initialize() {
		nameTextField.textProperty().bindBidirectional(userModel.nameProperty());
		emailTextField.textProperty().bindBidirectional(userModel.EmailProperty());
		newPasswordPasswordField.textProperty().bindBidirectional(userModel.passwordProperty());
		confirmNewPasswordPasswordField.textProperty().bindBidirectional(userModel.passwordProperty());

		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				userDao.saveUser(userModel.getUser());
				saveButton.getScene().getWindow().hide();
			}
		});
	}

}
