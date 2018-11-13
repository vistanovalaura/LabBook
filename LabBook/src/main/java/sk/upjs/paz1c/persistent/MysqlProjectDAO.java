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

public class MysqlProjectDAO implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;

	public MysqlProjectDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addProject(Project project) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("project");
		insert.usingGeneratedKeyColumns("id_project");
		insert.usingColumns("name", "active", "date_from", "date_until", "all_items_available");

		Map<String, Object> values = new HashMap<>();
		values.put("name", project.getName());
		values.put("active", project.isActive());
		values.put("date_from", project.getDateFrom());
		values.put("date_until", project.getDateUntil());
		values.put("all_items_available", project.isAllItemsAvailable());

		project.setProjectID(insert.executeAndReturnKey(values).longValue());
	}

	@Override
	public List<Project> getAll() {
		String sql = "SELECT id_project, name, active, date_from, date_until, all_items_available " + "FROM project";
		return jdbcTemplate.query(sql, new RowMapper<Project>() {

			@Override
			public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
				Project project = new Project();
				project.setProjectID(rs.getLong("id_project"));
				project.setName(rs.getString("name"));
				project.setActive(rs.getBoolean("active"));
				Timestamp timestamp = rs.getTimestamp("date_from");
				project.setDateFrom(timestamp.toLocalDateTime().toLocalDate());
				timestamp = rs.getTimestamp("date_until");
				if (timestamp != null) {
					project.setDateUntil(timestamp.toLocalDateTime().toLocalDate());
				}
				project.setAllItemsAvailable(rs.getBoolean("all_items_available"));
				return project;
			}
		});
	}

}
