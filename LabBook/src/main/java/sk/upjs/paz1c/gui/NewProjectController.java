package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class NewProjectController {

	@FXML
    private Button saveButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker untilDatePicker;

    @FXML
    void initialize() {
    	
    	saveButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				saveButton.getScene().getWindow().hide();	
			}
		});
    }
}
