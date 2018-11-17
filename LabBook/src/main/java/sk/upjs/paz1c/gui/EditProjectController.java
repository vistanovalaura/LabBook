package sk.upjs.paz1c.gui;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.fxmodels.ProjectFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ProjectDAO;

public class EditProjectController {
	
	private ProjectDAO projectDao = DAOfactory.INSTANCE.getProjectDAO();
	private ProjectFxModel projectModel; 
	private Project project; 

    @FXML
    private TextField nameTextField;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker untilDatePicker;

    @FXML
    private TableView<User> completedByTableView;

    @FXML
    private Button editButton;
    
    @FXML
    private Button saveButton;
    
    public EditProjectController(Project project) {
    	this.project = project; 
    	this.projectModel = new ProjectFxModel(project);
    }

    @FXML
    void initialize() {
		//projectModel = FXCollections.observableArrayList(projectDao.getAll());
    	nameTextField.textProperty().bindBidirectional(projectModel.nameProperty());
    	fromDatePicker.valueProperty().bindBidirectional(projectModel.fromProperty());
    	untilDatePicker.valueProperty().bindBidirectional(projectModel.untilProperty());

    	
    	saveButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				projectDao.saveProject(projectModel.getProject());
				saveButton.getScene().getWindow().hide();
				
			}
		});
    	
    	
    }
}
