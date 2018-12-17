package sk.upjs.paz1c.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import sk.upjs.paz1c.business.UserIdentificationManager;
import sk.upjs.paz1c.entities.Note;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.fxmodels.NoteFxModel;
import sk.upjs.paz1c.fxmodels.TaskFxModel;
import sk.upjs.paz1c.persistent.DAOfactory;
import sk.upjs.paz1c.persistent.NoteDAO;

public class EditNoteController {

	@FXML
	private Button saveButton;

	@FXML
	private TextArea noteTextArea;

	private TaskFxModel taskModel;

	private NoteFxModel notesModel;

	private NoteDAO noteDao;

	public EditNoteController(Note note) {
		this.notesModel = new NoteFxModel(note);
		this.noteDao = DAOfactory.INSTANCE.getNoteDAO();
	}

	@FXML
	void initialize() {
		noteTextArea.setText(notesModel.getText());

		saveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				notesModel.setAuthor(UserIdentificationManager.getUser());
				notesModel.setText(noteTextArea.getText());
				noteDao.saveNote(notesModel.getNote());
				saveButton.getScene().getWindow().hide();
			}
		});
	}
}
