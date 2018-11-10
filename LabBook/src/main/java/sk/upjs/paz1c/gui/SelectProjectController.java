package sk.upjs.paz1c.gui;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.Project;

public class SelectProjectController {

	//private ProjectDao projectDao = DaoFactory.INSTANCE.getProjectDao();
	private ObservableList<Project> projectsModel;
	private Map<String, BooleanProperty> columnsVisibility = new LinkedHashMap<>();
	private ObjectProperty<Project> selectedProject = new SimpleObjectProperty<>();

	@FXML
	private TableView<?> projectsTableView;

	@FXML
	private Button openButton;

	@FXML
	private Button editButton;
	
	@FXML
    private Button newProjectButton;

	@FXML 
	void initialize() {
    	//projectsModel = FXCollections.observableArrayList(projectDao.getAll());
		
		editButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//treba dorobit project edit 
				
				//ProjectEditController editController = new ProjectEditController();            
				//showModalWindow(editController, "ProjectEdit.fxml");
				
			}
		});
		
		openButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//NotesController noteController = new NoteController();            
				//showModalWindow(noteController, "selectNotes.fxml");				
			}
		});
		
		TableColumn<Project, String> nameCol = new TableColumn<>("Name");
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    	//projectsTableView.getColumns().add(nameCol);
    	columnsVisibility.put("ID", nameCol.visibleProperty());
    	
    	TableColumn<Project, String> createdByCol = new TableColumn<>("Created by");
    	createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
    	//projectsTableView.getColumns().add(nameCol);
    	columnsVisibility.put("createdBy", createdByCol.visibleProperty());
    	
    	TableColumn<Project, Boolean> activeCol = new TableColumn<>("Active");
    	activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));
    	//projectsTableView.getColumns().add(nameCol);
    	columnsVisibility.put("active", activeCol.visibleProperty());
	}
	
	private void showModalWindow(Object controller, String fxml) {
		try {
			FXMLLoader fxmlLoader = new	FXMLLoader(getClass().getResource(fxml));
			fxmlLoader.setController(controller);
			Parent rootPane	= fxmlLoader.load();
			Scene scene	= new Scene(rootPane);
			
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
