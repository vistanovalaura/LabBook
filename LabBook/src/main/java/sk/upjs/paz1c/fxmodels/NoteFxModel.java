package sk.upjs.paz1c.fxmodels;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Note;
import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.Task;
import sk.upjs.paz1c.entities.User;

public class NoteFxModel {

	private Long noteID;
	private StringProperty text = new SimpleStringProperty();
	private LocalDateTime timestamp;
	private User author;
	private Task task;
	private Project project;
	private Item item;

	public NoteFxModel(Note note) {
		setNoteID(note.getNoteID());
		setText(note.getText());
		setTimestamp(note.getTimestamp());
		setAuthor(note.getAuthor());
		setProject(note.getProject());
		setItem(note.getItem());
		setTask(note.getTask());
	}

	public Note getNote() {
		Note n = new Note();
		n.setNoteID(getNoteID());
		n.setText(getText());
		n.setTimestamp(getTimestamp());
		n.setAuthor(getAuthor());
		n.setProject(getProject());
		n.setItem(getItem());
		n.setTask(getTask());
		return n;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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
