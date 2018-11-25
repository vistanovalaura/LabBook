package sk.upjs.paz1c.persistent;

import java.util.List;

import sk.upjs.paz1c.entities.Note;

public interface NoteDAO {
	
	// pridanie admina do databazy
		void addNote(Note note);

		// vrati vsetkych adminov z databazy
		List<Note> getAll();

		// zmena admina v databaze
		void saveNote(Note note);

		// vymazanie admina z databazy
		void deleteNote(Note note);

}
