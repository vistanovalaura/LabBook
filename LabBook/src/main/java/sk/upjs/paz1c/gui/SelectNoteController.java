package sk.upjs.paz1c.gui;

import java.awt.TextArea;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sk.upjs.paz1c.entities.Note;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.fxmodels.NoteFxModel;
import sk.upjs.paz1c.fxmodels.ProjectFxModel;
import sk.upjs.paz1c.fxmodels.TaskFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.NoteDAO;
import sk.upjs.paz1c.persistent.TaskDAO;

public class SelectNoteController {
	
	private NoteDAO noteDao = DAOfactory.INSTANCE.getNoteDAO();
	private ObservableList<Note> notesModel;
	private Map<String, BooleanProperty> columnsVisibility = new LinkedHashMap<>();
	private ObjectProperty<Note> selectedNote = new SimpleObjectProperty<>();
	private TaskFxModel taskModel;
	
	 @FXML
	    private Button editButton;

	    @FXML
	    private Button deleteButton;

	    @FXML
	    private Button newNoteButton;

	    @FXML
	    private Button tasksButton;

	    @FXML
	    private TableView<Note> notesTableView;
	    
	    public SelectNoteController(Task task) {
			this.taskModel = new TaskFxModel(task);
		}
	    
	    @FXML
	    void initialize() {
	    	notesModel = FXCollections.observableArrayList(getNotes());

//			editButton.setOnAction(new EventHandler<ActionEvent>() {
//
//				@Override
//				public void handle(ActionEvent event) {
//					EditNoteController editNoteController = new EditNoteController(selectedNote.get());
//					showModalWindow(editNoteController, "editNote.fxml");
//					notesModel.setAll(getNotes());
//				}
//			});
//
//			deleteButton.setOnAction(new EventHandler<ActionEvent>() {
//
//				@Override
//				public void handle(ActionEvent event) {
//					DeleteNoteController deleteNoteController = new DeleteNoteController(selectedNote.get());
//					showModalWindow(deleteNoteController, "deleteNote.fxml");
//					notesModel.setAll(getNotes());
//				}
//			});
//			
//			newNoteButton.setOnAction(new EventHandler<ActionEvent>() {
//
//				@Override
//				public void handle(ActionEvent event) {
//					// TODO Auto-generated method stub
//					
//				}
//			});

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

	    
	    private List<Note> getNotes() {
			List<Note> notes = new ArrayList<>();
			List<Note> allNotes = noteDao.getAll();
			for (Note n : allNotes) {
				if (n.getTask().getTaskID() == taskModel.getTaskId()) {
					notes.add(n);
				}
			}
			return notes;

		}
}
