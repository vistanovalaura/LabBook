package sk.upjs.paz1c.persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.paz1c.entities.Note;

//FIXME urobit testy ku vsetkym metodam
public class MysqlNoteDAO implements NoteDAO {

	private JdbcTemplate jdbcTemplate;

	public MysqlNoteDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addNote(Note note) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("note");
		insert.usingGeneratedKeyColumns("id_note");
		insert.usingColumns("text", "timestamp", "user_id_user", "task_id_task", "project_id_project", "item_id_item");

		Map<String, Object> values = new HashMap<>();
		values.put("text", note.getText());
		values.put("timestamp", note.getTimestamp());
		values.put("user_id_user", note.getAuthor().getUserID());
		if (note.getTask() != null)
			values.put("task_id_task", note.getTask().getTaskID());
		else
			values.put("task_id_task", null);
		if (note.getProject() != null)
			values.put("project_id_project", note.getProject().getProjectID());
		else
			values.put("project_id_project", null);
		if (note.getItem() != null)
			values.put("item_id_item", note.getItem().getItemID());
		else
			values.put("item_id_item", null);

		note.setNoteID(insert.executeAndReturnKey(values).longValue());
	}

	@Override
	public List<Note> getAll() {
		String sql = "SELECT id_note, text, timestamp, user_id_user, task_id_task, project_id_project, item_id_item "
				+ "FROM note";
		return jdbcTemplate.query(sql, new RowMapper<Note>() {

			@Override
			public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
				Note note = new Note();
				note.setNoteID(rs.getLong("id_note"));
				note.setText(rs.getString("text"));
				note.setTimestamp(rs.getTimestamp("timestamp").toLocalDateTime().toLocalDate());
				note.setAuthor(DAOfactory.INSTANCE.getUserDAO().getByID(rs.getLong("user_id_user")));
				note.setTask(DAOfactory.INSTANCE.getTaskDAO().getByID(rs.getLong("task_id_task")));
				note.setProject(DAOfactory.INSTANCE.getProjectDAO().getByID(rs.getLong("project_id_project")));
				note.setItem(DAOfactory.INSTANCE.getItemDAO().getByID(rs.getLong("item_id_item")));
				return note;
			}
		});
	}

	@Override
	public void saveNote(Note note) {
		if (note == null)
			return;
		if (note.getNoteID() == null) { // CREATE
			addNote(note);
		} else { // UPDATE
			String sql = "UPDATE note SET " + "text = ?, timestamp = ?, user_id_user = ?, "
					+ "task_id_task = ?, project_id_project = ?, item_id_item = ? " + "WHERE id_note = ?";
			jdbcTemplate.update(sql, note.getText(), note.getTimestamp(), note.getAuthor().getUserID(),
					note.getTask().getTaskID(), note.getProject().getProjectID(), note.getItem().getItemID(),
					note.getNoteID());
		}
	}

	@Override
	public void deleteNote(Note note) {
		String sql = "DELETE FROM note WHERE id_note = " + note.getNoteID();
		jdbcTemplate.update(sql);
	}

}
