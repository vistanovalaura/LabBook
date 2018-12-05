package sk.upjs.paz1c.fxmodels;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz1c.entities.Note;
import sk.upjs.paz1c.entities.User;

public class NoteFxModel {

	private Long noteID;
	private StringProperty text = new SimpleStringProperty();
	private LocalDateTime timestamp;
	private User author;

	public NoteFxModel(Note note) {
		setNoteID(note.getNoteID());
		setText(note.getText());
		setTimestamp(note.getTimestamp());
		setAuthor(note.getAuthor());
	}

	public Note getNote() {
		Note n = new Note();
		n.setNoteID(getNoteID());
		n.setText(getText());
		n.setTimestamp(getTimestamp());
		n.setAuthor(n.getAuthor());
		return n;
	}
	
	

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
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
