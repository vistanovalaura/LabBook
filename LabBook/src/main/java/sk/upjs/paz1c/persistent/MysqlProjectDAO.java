package sk.upjs.paz1c.persistent;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.paz1c.entities.Project;

public class MysqlProjectDAO implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;

	public MysqlProjectDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addProjectOld(Project project) {
		String sql = "INSERT INTO project(name, active, date_from, date_until, all_items_available) VALUES(?,?,?,?,?)";
		jdbcTemplate.update(sql, project.getName(), project.isActive(), project.getFrom(), project.getUntil(),
				project.isAllItemsAvailable());
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
		values.put("date_from", project.getFrom());
		values.put("date_until", project.getUntil());
		values.put("all_items_available", project.isAllItemsAvailable());

		project.setProjectID(insert.executeAndReturnKey(values).longValue());
	}

}
