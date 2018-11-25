package sk.upjs.paz1c.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Laboratory;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.fxmodels.LaboratoryFxModel;
import sk.upjs.paz1c.fxmodels.ProjectFxModel;

public class EditLaboratoryController {
	@FXML
	private Button saveButton;

	@FXML
	private TableView<Item> itemsTableView;

	@FXML
	private Button addButton;

	@FXML
	private Button deleteButton;

	@FXML
	private TextField nameTextField;

	@FXML
	private TextField locationTextField;
	
	private Laboratory laboratory;
	private LaboratoryFxModel laboratoryModel;
	
	public EditLaboratoryController(Laboratory laboratory) {
    	this.laboratory = laboratory; 
    	this.laboratoryModel = new LaboratoryFxModel(laboratory);
    }
	
	@FXML
	void initialize() {
    	nameTextField.textProperty().bindBidirectional(laboratoryModel.nameProperty());
    	locationTextField.textProperty().bindBidirectional(laboratoryModel.nameProperty());

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
}
