package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sk.upjs.paz1c.business.UserIdentificationManager;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.fxmodels.ProjectFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ProjectDAO;

public class DeleteProjectController {

	private ProjectDAO projectDao = DAOfactory.INSTANCE.getProjectDAO();
	private ProjectFxModel projectModel;
	private Project project;

	@FXML
	private Button yesButton;

	@FXML
	private Button noButton;

	public DeleteProjectController(Project project) {
		this.project = project;
		this.projectModel = new ProjectFxModel(project);
	}

	@FXML
	void initialize() {

		yesButton.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override

			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					projectDao.deleteProject(projectModel.getProject());
					yesButton.getScene().getWindow().hide();
				}

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
