package sk.upjs.paz1c.gui;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.beans.property.BooleanProperty;
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
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ProjectDAO;

public class SelectProjectController {

	private ProjectDAO projectDao = DAOfactory.INSTANCE.getProjectDAO();
	private ObservableList<Project> projectsModel;
	private Map<String, BooleanProperty> columnsVisibility = new LinkedHashMap<>();
	private ObjectProperty<Project> selectedProject = new SimpleObjectProperty<>();

	@FXML
	private TableView<Project> projectsTableView;

	@FXML
	private Button openButton;

	@FXML
	private Button editButton;

	@FXML
	private Button newProjectButton;

	@FXML
	private Button deleteButton;

	@FXML
	private Button signOutButton;

	@FXML
	void initialize() {
		projectsModel = FXCollections.observableArrayList(projectDao.getAll());

		editButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				EditProjectController editController = new EditProjectController(selectedProject.get());
				showModalWindow(editController, "editProject.fxml");
				projectsModel.setAll(projectDao.getAll());
			}
		});

		openButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// NotesController noteController = new NoteController();
				// showModalWindow(noteController, "selectNotes.fxml");
			}
		});

		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				DeleteProjectController deleteProjectController = new DeleteProjectController();
				showModalWindow(deleteProjectController, "deleteProject.fxml");

			}
		});

		signOutButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				signOutButton.getScene().getWindow().hide();				
			}
		});
		newProjectButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				NewProjectController newProjectController = new NewProjectController();
				showModalWindow(newProjectController, "newProject.fxml");
				projectsModel.setAll(projectDao.getAll());
			}
		});

		TableColumn<Project, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		projectsTableView.getColumns().add(nameCol);
		columnsVisibility.put("ID", nameCol.visibleProperty());

//		TableColumn<Project, String> createdByCol = new TableColumn<>("Created by");
//		createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
//		projectsTableView.getColumns().add(createdByCol);
//		columnsVisibility.put("createdBy", createdByCol.visibleProperty());

		TableColumn<Project, Boolean> activeCol = new TableColumn<>("Active");
		activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));
		projectsTableView.getColumns().add(activeCol);
		columnsVisibility.put("active", activeCol.visibleProperty());

		projectsTableView.setItems(projectsModel);
		projectsTableView.setEditable(true);

		ContextMenu contextMenu = new ContextMenu();
		for (Entry<String, BooleanProperty> entry : columnsVisibility.entrySet()) {
			CheckMenuItem menuItem = new CheckMenuItem(entry.getKey());
			menuItem.selectedProperty().bindBidirectional(entry.getValue());
			contextMenu.getItems().add(menuItem);
		}
		projectsTableView.setContextMenu(contextMenu);

		projectsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Project>() {

			@Override
			public void changed(ObservableValue<? extends Project> observable, Project oldValue, Project newValue) {
				if (newValue == null) {
					editButton.setDisable(true);
				} else {
					editButton.setDisable(false);
				}
				selectedProject.set(newValue);
			}
		});
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
