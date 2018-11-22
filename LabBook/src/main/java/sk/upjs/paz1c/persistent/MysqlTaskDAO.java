package sk.upjs.paz1c.persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.Task;

public class MysqlTaskDAO implements TaskDAO {

	private JdbcTemplate jdbcTemplate;

	public MysqlTaskDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addTask(Task task) {

		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("task");
		insert.usingGeneratedKeyColumns("id_task");
		insert.usingColumns("project_id_project", "name", "active", "date_time_from", "date_time_until", "each_item_available");

		Map<String, Object> values = new HashMap<>();
		values.put("project_id_project", task.getProjectID());
		values.put("name", task.getName());
		values.put("active", task.isActive());
		values.put("date_time_from", task.getDateTimeFrom());
		values.put("date_time_until", task.getDateTimeUntil());
		values.put("each_item_available", task.isEachItemAvailable());

		task.setTaskID(insert.executeAndReturnKey(values).longValue());
	}

	@Override
	public void saveTask(Task task) {
		if (task == null)
			return;
		if (task.getTaskID() == null) { // CREATE
			addTask(task);
		} else { // UPDATE
			String sql = "UPDATE task SET " + "project_id_project = ?, name = ?, active = ?, date_time_from = ?, date_time_until = ?, "
					+ "each_item_available = ? " + "WHERE id_task = ?";
			jdbcTemplate.update(sql, task.getProjectID(), task.getName(), task.isActive(), task.getDateTimeFrom(),
					task.getDateTimeUntil(), task.isEachItemAvailable(), task.getTaskID());
		}
	}

	@Override
	public List<Task> getAll() {
		String sql = "SELECT id_task, project_id_project, name, active, date_time_from, date_time_until, each_item_available " + "FROM task";
		return jdbcTemplate.query(sql, new RowMapper<Task>() {

			@Override
			public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
				Task task = new Task();
				task.setTaskID(rs.getLong("id_task"));
				task.setProjectID(rs.getLong("project_id_project"));
				task.setName(rs.getString("name"));
				task.setActive(rs.getBoolean("active"));
				Timestamp timestamp = rs.getTimestamp("date_time_from");
				if(timestamp != null) {
					task.setDateTimeFrom(timestamp.toLocalDateTime().toLocalDate());
				}
				timestamp = rs.getTimestamp("date_time_until");
				if (timestamp != null) {
					task.setDateTimeUntil(timestamp.toLocalDateTime().toLocalDate());
				}
				task.setEachItemAvailable(rs.getBoolean("each_item_available"));
				return task;
			}
		});
	}

	@Override
	public void deleteTask(Task task) {
		String sql = "DELETE FROM task WHERE id_task = " + task.getTaskID();
		jdbcTemplate.update(sql);
	}

}
