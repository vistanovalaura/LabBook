package sk.upjs.paz1c.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WrongDataInputController {

	@FXML
	private Button backButton;

	@FXML
	void initialize() {
		backButton.setOnAction(eh -> {
			backButton.getScene().getWindow().hide();
		});
		
	}
}
