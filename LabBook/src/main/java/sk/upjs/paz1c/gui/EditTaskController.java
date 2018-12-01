package sk.upjs.paz1c.gui;

import java.io.IOException;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Laboratory;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.fxmodels.LaboratoryFxModel;
import sk.upjs.paz1c.fxmodels.ProjectFxModel;
import sk.upjs.paz1c.fxmodels.TaskFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.LaboratoryDAO;
import sk.upjs.paz1c.persistent.ProjectDAO;
import sk.upjs.paz1c.persistent.TaskDAO;

public class EditTaskController {
	
	private TaskDAO taskDao = DAOfactory.INSTANCE.getTaskDAO();
	private TaskFxModel taskModel; 
	private Task task; 

	@FXML
    private Button saveButton;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private ComboBox<Laboratory> laboratoryComboBox;

    @FXML
    private DatePicker untilDatePicker;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private TableView<Item> itemsTableView;

    @FXML
    private TextField nameTextField;
    
    private Laboratory laboratory;
	private LaboratoryDAO laboratoryDao;
	private LaboratoryFxModel selectedLaboratoryModel;
	private ObservableList<Laboratory> laboratoryModel;
    	
	 public EditTaskController(Task task) {
	    	this.task = task; 
	    	this.taskModel = new TaskFxModel(task);
	    	laboratoryDao = DAOfactory.INSTANCE.getLaboratoryDAO();
			selectedLaboratoryModel = new LaboratoryFxModel();
	    }
	 
	 @FXML
	    void initialize() {
			laboratoryModel = FXCollections.observableArrayList(laboratoryDao.getAll());

	    	nameTextField.textProperty().bindBidirectional(taskModel.nameProperty());
	    	fromDatePicker.valueProperty().bindBidirectional(taskModel.fromProperty());
	    	untilDatePicker.valueProperty().bindBidirectional(taskModel.untilProperty());
	    	
	    	
	    	List<Laboratory> laboratories = laboratoryDao.getAll();
			laboratoryComboBox.setItems(FXCollections.observableList(laboratories));
			laboratoryComboBox.getSelectionModel().select(taskModel.getLaboratory());
			laboratoryComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Laboratory>() {

				@Override
				public void changed(ObservableValue<? extends Laboratory> observable, Laboratory oldValue,
						Laboratory newValue) {
					if (newValue != null) {
						//System.out.println(newValue.getName());
						selectedLaboratoryModel.setLaboratory(newValue);
					}
				}
			});
	    	saveButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					taskDao.saveTask(taskModel.getTask());
					saveButton.getScene().getWindow().hide();
					
				}
			});
	    	
//			removeButton.setOnAction(new EventHandler<ActionEvent>() {
//	    	
//	    				@Override
//	    				public void handle(ActionEvent event) {
//	    					RemoveItemController removeItemController = new RemoveItemController(selectedItem.get());
//	    					showModalWindow(removeItemController, "removeItem.fxml");
//	    					itemsModel.setAll(itemDao.getAll());
//	    				}
//	    			});
	    	
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
