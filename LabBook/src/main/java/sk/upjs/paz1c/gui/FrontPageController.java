package sk.upjs.paz1c.gui;

import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.business.UserIdentificationManager;
import sk.upjs.paz1c.entities.Admin;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.fxmodels.UserFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;

public class FrontPageController {

	private User user;
	private Admin admin;
	private UserFxModel signInFxModel = new UserFxModel();
	private sk.upjs.paz1c.business.PasswordManager passwordManager = new sk.upjs.paz1c.business.PasswordManager();

	@FXML
	private TextField loginTextField;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private Button signInButton;

	@FXML
	private Button registerButton;

	@FXML
	private Hyperlink forgottenPasswordHyperlink;

	public FrontPageController() {

	}

	// public FrontPageController(User user) {
	// this.user = user;
	// this.userModel = new UserFxModel(user);
	// }

	@FXML
	void initialize() {
		// https://www.programcreek.com/java-api-examples/?class=javafx.scene.Scene&method=setOnKeyPressed
		loginTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					String login = loginTextField.getText();
					String password = passwordTextField.getText();

					if (UserIdentificationManager.setUser(login, password) == 1) {
						user = UserIdentificationManager.getUser();
						loginUser();
					} else if (UserIdentificationManager.setUser(login, password) == 2) {
						admin = UserIdentificationManager.getAdmin();
						loginAdmin();
					} else {
						showWrongDataWindow();
					}
				}
			}
		});
		passwordTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					String login = loginTextField.getText();
					String password = passwordTextField.getText();

					if (UserIdentificationManager.setUser(login, password) == 1) {
						user = UserIdentificationManager.getUser();
						loginUser();
					} else if (UserIdentificationManager.setUser(login, password) == 2) {
						admin = UserIdentificationManager.getAdmin();
						loginAdmin();
					} else {
						showWrongDataWindow();
					}
				}
			}
		});

		registerButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				RegistrationController registrationController = new RegistrationController();
				showModalWindow(registrationController, "registration.fxml");
			}
		});

		/*
		 * signInButton.setOnAction(new EventHandler<ActionEvent>() {
		 * 
		 * @Override public void handle(ActionEvent event) { SelectProjectController
		 * selectProjectController = new SelectProjectController();
		 * showModalWindow(selectProjectController, "selectProject.fxml"); } });
		 */

		forgottenPasswordHyperlink.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ForgottenPasswordController forgottenPasswordController = new ForgottenPasswordController();
				showModalWindow(forgottenPasswordController, "forgottenPassword.fxml");
			}
		});

		signInButton.setOnAction(eh -> {

			String login = loginTextField.getText();
			String password = passwordTextField.getText();

			if (UserIdentificationManager.setUser(login, password) == 1) {
				user = UserIdentificationManager.getUser();
				loginUser();
			} else if (UserIdentificationManager.setUser(login, password) == 2) {
				admin = UserIdentificationManager.getAdmin();
				loginAdmin();
			} else {
				showWrongDataWindow();
			}
		});

	}

	private void loginAdmin() {
		EditDataAdminController controller = new EditDataAdminController(admin);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("editDataAdmin.fxml"));
			loader.setController(controller);

			Parent parentPane = loader.load();
			Scene scene = new Scene(parentPane);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Admin Page");
			stage.show();
			signInButton.getScene().getWindow().hide();

		} catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

	private void loginUser() {
		SelectProjectController controller = new SelectProjectController(user);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("selectProject.fxml"));
			loader.setController(controller);

			Parent parentPane = loader.load();
			Scene scene = new Scene(parentPane);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Projects");
			stage.show();
			signInButton.getScene().getWindow().hide();

		} catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

	private void showModalWindow(Object controller, String fxml) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
			fxmlLoader.setController(controller);
			Parent rootPane = fxmlLoader.load();
			Scene scene = new Scene(rootPane);

			Stage dialog = new Stage();
			dialog.setScene(scene);
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showWrongDataWindow() {
		AlertBoxFailToSignInController controller = new AlertBoxFailToSignInController();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("alertBoxFailToSignIn.fxml"));
			loader.setController(controller);

			Parent parentPane = loader.load();
			Scene scene = new Scene(parentPane);

			Stage stage = new Stage();
			stage.setScene(scene);
			// nikde sa neda v aplikacii kliknut kym je toto okno aktivne
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setTitle("Fail to sign in");
			stage.show();

		} catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

	public Admin findByName(String name) {
		List<Admin> admins = DAOfactory.INSTANCE.getAdminDAO().getAll();
		for (Admin a : admins) {
			if (a.getName().equals(name)) {
				return a;
			}
		}
		return null;
	}

	public User findByName(String name, String password) {
		List<User> users = DAOfactory.INSTANCE.getUserDAO().getAll();
		for (User user : users) {
			if (user.getName().equals(name) && user.getPassword().equals(password)) {
				return user;
			}

		}
		return null;
	}
}
