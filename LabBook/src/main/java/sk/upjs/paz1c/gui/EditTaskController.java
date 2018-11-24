package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.fxmodels.ProjectFxModel;
import sk.upjs.paz1c.fxmodels.TaskFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ProjectDAO;

public class EditTaskController {
	
//	private TaskDAO taskDao = DAOfactory.INSTANCE.getTaskDAO();
	private TaskFxModel taskModel; 
	private Task task; 

	@FXML
	private DatePicker fromDatePicker;

	@FXML
	private DatePicker untilDatePicker;

	@FXML
	private TextField nameTextField;

	@FXML
	private TableView<Item> itemsTableView;
	
	@FXML
    private SplitMenuButton laboratorySplitMenuButton;

	@FXML
    private Button addButton;

    @FXML
    private Button removeButton;

	@FXML
	private Button saveButton;
	
	 public EditTaskController(Task task) {
	    	this.task = task; 
	    	this.taskModel = new TaskFxModel(task);
	    }
	 
	 @FXML
	    void initialize() {
	    	nameTextField.textProperty().bindBidirectional(taskModel.nameProperty());
	    	fromDatePicker.valueProperty().bindBidirectional(taskModel.fromProperty());
	    	untilDatePicker.valueProperty().bindBidirectional(taskModel.untilProperty());

	    	
	    	saveButton.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					//taskDao.saveTask(taskModel.getTask());
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
}
