package sk.upjs.paz1c.entities;

import java.time.LocalDate;

public class Note {

	private Long noteID;
	private String text;
	private LocalDate timestamp;
	private User author;
	private Task task;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Long getNoteID() {
		return noteID;
	}

	public void setNoteID(Long noteID) {
		this.noteID = noteID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}
