package sk.upjs.paz1c.gui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.LaboratoryDAO;
import sk.upjs.paz1c.persistent.ProjectDAO;

public class NewLaboratoryController {

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

	@FXML
	void initialize() {
		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String name = nameTextField.getText();
				String location = locationTextField.getText();
				List<Item> items = itemsTableView.getItems();
				
				if (name.isEmpty() || location.isEmpty()) {
					showWrongDataInputWindow();
				} else {
					Laboratory laboratory = new Laboratory(name, location, items);
					LaboratoryDAO laboratoryDao = DAOfactory.INSTANCE.getLaboratoryDAO();
					laboratoryDao.addLaboratory(laboratory);
					saveButton.getScene().getWindow().hide();
				}
				
			}
		});


	}

	private void showWrongDataInputWindow() {
		WrongDataInputController controller = new WrongDataInputController();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("WrongDataInput.fxml"));
			loader.setController(controller);

			Parent parentPane = loader.load();
			Scene scene = new Scene(parentPane);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setTitle("Wrong data");
			stage.show();

		} catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

}
