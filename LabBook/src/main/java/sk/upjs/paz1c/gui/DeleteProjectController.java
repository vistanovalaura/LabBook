package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DeleteProjectController {

	@FXML
	private Button yesButton;

	@FXML
	private Button noButton;

	@FXML
	void initialize() {
		yesButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// treba doplnit mazanie projektu

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
