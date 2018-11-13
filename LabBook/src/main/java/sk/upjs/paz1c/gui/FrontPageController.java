package sk.upjs.paz1c.gui;

import java.io.IOException;

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
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.User;

public class FrontPageController {

	private User user;
	private SignInFxModel userModel;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private TextField loginTextField;

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
			loginUser();
			//showWrongDataWindow();
			/*if (!UserIdentificationManager.setUser(loginFxModel.getEmail())) {
				showWrongDataWindow();
			}
			int typeOfUser = UserIdentificationManager.getTypeOfUser();
			if (passwordManager.isCorrectPassword(loginFxModel.getPassword(), typeOfUser,
					UserIdentificationManager.getId())) {
				if (typeOfUser == 1) {
					loginTeacher();
				}
				if (typeOfUser == 2) {
					loginAdmin();
				}
				if (typeOfUser == 3) {
					loginSuperAdmin();
				}
			} else {
				showWrongDataWindow();
			}*/

		});

	}

	private void loginUser() {
		SelectProjectController controller = new SelectProjectController();
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
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("alertBoxFailToSignIn.fxml"));
            loader.setController(controller);

            Parent parentPane = loader.load();
            Scene scene = new Scene(parentPane);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setTitle("Fail to sign in");
            stage.show();

        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }
}
