package sk.upjs.paz1c.gui;

import java.awt.TextArea;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.fxmodels.TaskFxModel;

public class SelectNoteController {
	
	@FXML
    private Button saveButton;

    @FXML
    private TextArea noteTextArea;
    
    private TaskFxModel taskModel;
    
    public SelectNoteController(Task task) {
    	this.taskModel = new TaskFxModel(task); 

    }

    @FXML
    void initialize() {
    	noteTextArea.setText("");
    	
    	saveButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//notesModel.setAll(noteDao.getAll());
			}
		});
    }
}
