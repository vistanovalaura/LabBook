package sk.upjs.paz1c.gui;

import java.io.IOException;
import java.util.List;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.Admin;
import sk.upjs.paz1c.entities.Laboratory;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.User;
import sk.upjs.paz1c.fxmodels.UserFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.UserDAO;

public class EditDataAdminController {

	private Admin admin;
	
	@FXML
	private ComboBox<User> userComboBox;

	@FXML
	private Button deleteButton;

	@FXML
	private ComboBox<Laboratory> laboratoriesComboBox;

	@FXML
	private Button editButton;

	@FXML
	private Button createButton;

	@FXML
	private Button signOutButton;

	@FXML
	private Button editAdminButton;
	
	private User user;
	private UserDAO userDao;
	private UserFxModel selectedUserModel;
	private ObservableList<User> userModel;


	public EditDataAdminController(Admin admin) {
		this.admin = admin;
		userDao = DAOfactory.INSTANCE.getUserDAO();
		selectedUserModel = new UserFxModel();
	}

	@FXML
	void initialize() {
		userModel = FXCollections.observableArrayList(userDao.getAll());

		List<User> users = userDao.getAll();
    	userComboBox.setItems(FXCollections.observableList(users));
    	userComboBox.getSelectionModel().selectedItemProperty()
    		.addListener(new ChangeListener<User>() {

				@Override
				public void changed(ObservableValue<? extends User> observable, 
						User oldValue, User newValue) {
					if (newValue != null) {
						System.out.println(newValue.getName());
						selectedUserModel.setUser(newValue);
					}
				}
			});
    	
		signOutButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				signOut();
			}
		});

		editAdminButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				EditAdminController editController = new EditAdminController(admin);
				showModalWindow(editController, "editAdmin.fxml");
				userModel.setAll(userDao.getAll());
			}
		});
		
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent event) {
				DeleteUserAdminController deleteController = new DeleteUserAdminController(selectedUserModel.getUser());
				showModalWindow(deleteController, "deleteUserAdmin.fxml");
				List<User> users = userDao.getAll();
		    	userComboBox.setItems(FXCollections.observableList(users));
				//userModel.setAll(userDao.getAll());
			}
		});
		
		createButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				NewLaboratoryController laboratoryController = new NewLaboratoryController();
				showModalWindow(laboratoryController, "newLaboratory.fxml");
				
			}
		});

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
