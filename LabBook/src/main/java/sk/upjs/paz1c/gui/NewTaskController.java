package sk.upjs.paz1c.gui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ProjectDAO;

public class NewTaskController {

	 @FXML
	    private Button addButton;

	    @FXML
	    private Button saveButton;

	    @FXML
	    private TextField nameTextField;

	    @FXML
	    private DatePicker fromDatePicker;

	    @FXML
	    private DatePicker untilDatePicker;

	    @FXML
	    private SplitMenuButton laboratorySplitMenuButton;

	    @FXML
	    private TableView<Item> itemsTableView;

	    @FXML
	    private Button removeButton;
	    
	    @FXML
	    void initialize() {
	    	
	    	saveButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					String name = nameTextField.getText();
					LocalDate from = fromDatePicker.getValue();
					LocalDate until = untilDatePicker.getValue();
					
					if (name.isEmpty()) {
						showWrongDataInputWindow();
					} else {
						Task task = new Task(name);
						//TaskDAO taskDao = DAOfactory.INSTANCE.getTaskDAO();
						//taskDao.addProject(task);
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
