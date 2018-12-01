package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Laboratory;
import sk.upjs.paz1c.fxmodels.LaboratoryFxModel;

public class SelectItemTasksController {

	@FXML
	private Button selectButton;

	@FXML
	private ComboBox<Item> itemComboBox;
	
	private LaboratoryFxModel laboratoryModel;
	
	public SelectItemTasksController(Laboratory laboratory) {
		this.laboratoryModel = new LaboratoryFxModel(laboratory);
	}

	@FXML
	void initialize() {

		selectButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				
				selectButton.getScene().getWindow().hide();
				
			}
		});
	}

}
