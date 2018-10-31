package sk.upjs.paz1c;

import java.time.LocalDate;

public class Note {

	private Long noteID;
	private String text;
	private LocalDate timestamp;
	private User author;
	
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
