package sk.upjs.paz1c.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.business.ExportUserDataToExcelManager;
import sk.upjs.paz1c.business.UserIdentificationManager;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.fxmodels.UserFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.ProjectDAO;

public class SelectProjectController {

	private ProjectDAO projectDao = DAOfactory.INSTANCE.getProjectDAO();
	private ObservableList<Project> projectsModel;
	private Map<String, BooleanProperty> columnsVisibility = new LinkedHashMap<>();
	private ObjectProperty<Project> selectedProject = new SimpleObjectProperty<>();
	private UserFxModel userModel;

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
	private Button editAccount;

	@FXML
	private Button exportDataButton;

	public SelectProjectController(User user) {
		this.userModel = new UserFxModel(user);
	}

	public SelectProjectController() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	void initialize() {
		projectsModel = FXCollections.observableArrayList(getProjects());

		//https://stackoverflow.com/questions/26563390/detect-doubleclick-on-row-of-tableview-javafx
		projectsTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						openTasks();
					}
				}
			}
		});

		exportDataButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					ExportUserDataToExcelManager.exportUserData(UserIdentificationManager.getUser());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		editButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				EditProjectController editController = new EditProjectController(selectedProject.get());
				showModalWindow(editController, "editProject.fxml");
				projectsModel.setAll(getProjects());
			}
		});

		openButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				openTasks();
			}
		});

		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				DeleteProjectController deleteProjectController = new DeleteProjectController(selectedProject.get());
				showModalWindow(deleteProjectController, "deleteProject.fxml");
				projectsModel.setAll(getProjects());
			}
		});

		signOutButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				signOut();
			}
		});
		newProjectButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				NewProjectController newProjectController = new NewProjectController(
						UserIdentificationManager.getUser());
				showModalWindow(newProjectController, "newProject.fxml");
				projectsModel.setAll(getProjects());
			}
		});

		editAccount.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				EditUserController editUserController = new EditUserController(UserIdentificationManager.getUser());
				showModalWindow(editUserController, "editUser.fxml");

			}
		});

		TableColumn<Project, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		projectsTableView.getColumns().add(nameCol);
		columnsVisibility.put("ID", nameCol.visibleProperty());

		// TableColumn<Project, LocalDate> fromCol = new TableColumn<>("date_from");
		// fromCol.setCellValueFactory(new PropertyValueFactory<>("from"));
		// projectsTableView.getColumns().add(fromCol);
		// columnsVisibility.put("from", fromCol.visibleProperty());

		// TableColumn<Project, Boolean> activeCol = new TableColumn<>("Active");
		// activeCol.setCellValueFactory(new PropertyValueFactory<>("active"));
		// projectsTableView.getColumns().add(activeCol);
		// columnsVisibility.put("active", activeCol.visibleProperty());

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
					deleteButton.setDisable(true);
				} else {
					editButton.setDisable(false);
					deleteButton.setDisable(false);
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

	public void openTasks() {
		SelectTaskController controller = new SelectTaskController(selectedProject.get());
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("selectTask.fxml"));
			loader.setController(controller);

			Parent parentPane = loader.load();
			Scene scene = new Scene(parentPane);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Tasks");
			stage.show();
			openButton.getScene().getWindow().hide();
		} catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

	public void signOut() {
		FrontPageController controller = new FrontPageController();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("frontPage.fxml"));
			loader.setController(controller);

			Parent parentPane = loader.load();
			Scene scene = new Scene(parentPane);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("LabBook login");
			stage.show();
			signOutButton.getScene().getWindow().hide();

		} catch (IOException iOException) {
			iOException.printStackTrace();
		}
	}

	private List<Project> getProjects() {
		List<Project> projects = new ArrayList<>();
		List<Project> allProjects = projectDao.getAll();
		for (Project p : allProjects) {
			if (p.getCreatedBy().getUserID().equals(UserIdentificationManager.getUser().getUserID())) {
				projects.add(p);
			}
		}
		return projects;

	}
}
