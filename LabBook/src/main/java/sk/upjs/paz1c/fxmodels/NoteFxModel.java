package sk.upjs.paz1c.fxmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz1c.entities.Note;

public class NoteFxModel {

	private Long noteID;
	private StringProperty text = new SimpleStringProperty();

	public NoteFxModel(Note note) {
		setNoteID(note.getNoteID());
		setText(note.getText());
	}

	public Note getNote() {
		Note n = new Note();
		n.setNoteID(getNoteID());
		n.setText(getText());
		return n;
	}

	public Long getNoteID() {
		return noteID;
	}

	public void setNoteID(Long noteID) {
		this.noteID = noteID;
	}

	public String getText() {
		return text.get();
	}

	public void setText(String text) {
		this.text.set(text);
	}

	public StringProperty textProperty() {
		return text;
	}

}
