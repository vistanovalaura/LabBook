package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.fxmodels.ProjectFxModel;
import sk.upjs.paz1c.fxmodels.UserFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ProjectDAO;
import sk.upjs.paz1c.persistent.UserDAO;

public class DeleteUserAdminController {

	private UserDAO userDao = DAOfactory.INSTANCE.getUserDAO();
	private User user;

	public DeleteUserAdminController(User user) {
		System.out.println(user.getName());
		this.user = user;
	}
	

	@FXML
	private Button yesButton;

	@FXML
	private Button noButton;

	@FXML
	void initialize() {
		yesButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				userDao.deleteUser(user);
				//userDao.saveUser(userModel.getUser());
				yesButton.getScene().getWindow().hide();
			}
		});

		noButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				noButton.getScene().getWindow().hide();
			}
		});
	}
}
