package sk.upjs.paz1c.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.fxmodels.ProjectFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ProjectDAO;
import sk.upjs.paz1c.persistent.TaskDAO;

public class SelectTaskController {

	private TaskDAO taskDao = DAOfactory.INSTANCE.getTaskDAO();
	private ObservableList<Task> tasksModel;
	private Map<String, BooleanProperty> columnsVisibility = new LinkedHashMap<>();
	private ObjectProperty<Task> selectedTask = new SimpleObjectProperty<>();
	private ProjectFxModel projectModel;

	@FXML
	private TableView<Task> tasksTableView;

	@FXML
	private Button newTaskButton;

	@FXML
	private Button editButton;

	@FXML
	private Button openButton;

	@FXML
	private Button deleteButton;

	@FXML
	private Button projectsButton;

	public SelectTaskController(Project project) {
		this.projectModel = new ProjectFxModel(project);
	}

	@FXML
	void initialize() {

		tasksModel = FXCollections.observableArrayList(getTasks());

		editButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				EditTaskController editTaskController = new EditTaskController(selectedTask.get());
				showModalWindow(editTaskController, "editTask.fxml");
				tasksModel.setAll(getTasks());
			}
		});

		openButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SelectNoteController notesController = new SelectNoteController(selectedTask.get());
				showModalWindow(notesController, "selectNote.fxml");
			}
		});

		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				DeleteTaskController deleteTaskController = new DeleteTaskController(selectedTask.get());
				showModalWindow(deleteTaskController, "deleteTask.fxml");
				tasksModel.setAll(getTasks());
			}
		});

		newTaskButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				NewTaskController newTaskController = new NewTaskController(projectModel.getProject());
				showModalWindow(newTaskController, "newTask.fxml");
				tasksModel.setAll(getTasks());
			}
		});

		TableColumn<Task, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		tasksTableView.getColumns().add(nameCol);
		columnsVisibility.put("name", nameCol.visibleProperty());

		TableColumn<Task, String> itemsCol = new TableColumn<>("Items");
		itemsCol.setCellValueFactory(new PropertyValueFactory<>("items"));
		tasksTableView.getColumns().add(itemsCol);
		columnsVisibility.put("items", itemsCol.visibleProperty());

		tasksTableView.setItems(tasksModel);
		tasksTableView.setEditable(true);

		tasksTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Task>() {

			@Override
			public void changed(ObservableValue<? extends Task> observable, Task oldValue, Task newValue) {
				if (newValue == null) {
					editButton.setDisable(true);
				} else {
					editButton.setDisable(false);
				}
				selectedTask.set(newValue);
			}
		});

		projectsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SelectProjectController controller = new SelectProjectController();
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("selectProject.fxml"));
					loader.setController(controller);

					Parent parentPane = loader.load();
					Scene scene = new Scene(parentPane);

					Stage stage = new Stage();
					stage.setScene(scene);
					stage.setTitle("Projects");
					stage.show();
					projectsButton.getScene().getWindow().hide();

				} catch (IOException iOException) {
					iOException.printStackTrace();
				}
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

	private List<Task> getTasks() {
		List<Task> tasks = new ArrayList<>();
		List<Task> allTasks = taskDao.getAll();
		for (Task t : allTasks) {
			if (t.getProject().getProjectID() == projectModel.getProjectId()) {
				tasks.add(t);
			}
		}
		return tasks;

	}

}
