package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sk.upjs.paz1c.entities.Admin;
import sk.upjs.paz1c.fxmodels.AdminFxModel;
import sk.upjs.paz1c.persistent.AdminDAO;
import sk.upjs.paz1c.persistent.DAOfactory;

public class EditAdminController {

	private AdminDAO adminDao = DAOfactory.INSTANCE.getAdminDAO();
	private AdminFxModel adminModel;
	private Admin admin;

	@FXML
	private Button saveButton;

	@FXML
	private PasswordField passwordPasswordField;

	@FXML
	private PasswordField confirmPasswordPasswordField;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField emailTextField;

	public EditAdminController(Admin admin) {
		this.admin = admin;
		this.adminModel = new AdminFxModel(admin);
	}

	@FXML
	void initialize() {
		nameTextField.textProperty().bindBidirectional(adminModel.nameProperty());
		emailTextField.textProperty().bindBidirectional(adminModel.EmailProperty());
		passwordPasswordField.textProperty().bindBidirectional(adminModel.passwordProperty());
		confirmPasswordPasswordField.textProperty().bindBidirectional(adminModel.passwordProperty());

		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				adminDao.saveAdmin(adminModel.getAdmin());
				saveButton.getScene().getWindow().hide();
			}
			
		});
	}
}
