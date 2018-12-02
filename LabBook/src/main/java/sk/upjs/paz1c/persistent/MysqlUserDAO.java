package sk.upjs.paz1c.persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.paz1c.entities.Project;
import sk.upjs.paz1c.entities.User;

public class MysqlUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;

	public MysqlUserDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addUser(User user) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("user");
		insert.usingGeneratedKeyColumns("id_user");
		insert.usingColumns("name", "password", "email");

		Map<String, Object> values = new HashMap<>();
		values.put("name", user.getName());
		values.put("password", user.getPassword());
		values.put("email", user.getEmail());

		user.setUserID(insert.executeAndReturnKey(values).longValue());
	}

	@Override
	public List<User> getAll() {
		String sql = "SELECT id_user, name, password, email " + "FROM lab_book.user";
		return jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserID(rs.getLong("id_user"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				return user;
			}
		});
	}

	@Override
	public void saveUser(User user) {
		if (user == null)
			return;
		if (user.getUserID() == null) { // CREATE
			addUser(user);
		} else { // UPDATE
			String sql = "UPDATE user SET " + "name = ?, password = ?, email = ? " + "WHERE id_user = ?";
			jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getEmail(), user.getUserID());
		}
	}

	// dobuducna - nech aj po odstraneni usera ostanu jeho projekty, tasky,
	// komenty... foreign key na null, alebo odstranenie stlpca s foreign key,
	// pridanie stlpca so stringom s menom byvaleho usera...
	@Override
	public void deleteUser(User user) {
		// vymaze vsetky note, ktore patrili k danemu userovi
		jdbcTemplate.update("DELETE FROM note WHERE user_id_user = ?", user.getUserID());
		// vymaze vsetky tasky, ktore patrili k danemu userovi
		jdbcTemplate.update("DELETE FROM task WHERE user_id_user = ?", user.getUserID());
		// vymaze vsetky projekty, ktore patrili k danemu userovi
		jdbcTemplate.update("DELETE FROM project WHERE user_id_user = ?", user.getUserID());
		// vymaze usera
		String sql = "DELETE FROM user WHERE id_user = " + user.getUserID();
		jdbcTemplate.update(sql);
	}

	// FIXME - urobit test
	@Override
	public User getByID(Long id) {
		String sql = "SELECT id_user AS userID, name, password, email " + "FROM lab_book.user " + "WHERE id_user = "
				+ id;
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class));
	}

}
