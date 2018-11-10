package sk.upjs.paz1c.persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.paz1c.entities.User;

public class MysqlUserDAO implements UserDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public MysqlUserDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void addUserOld(User user) {
		String sql = "INSERT INTO user(login, password, email) VALUES(?,?,?)";
		jdbcTemplate.update(sql, user.getName(), user.getPassword(), user.getEmail());
	}

	@Override
	public void addUser(User user) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("user");
		insert.usingGeneratedKeyColumns("id_user");
		insert.usingColumns("login", "password", "email");

		Map<String, Object> values = new HashMap<>();
		values.put("login", user.getName());
		values.put("password", user.getPassword());
		values.put("email", user.getEmail());

		user.setUserID(insert.executeAndReturnKey(values).longValue());
	}

	@Override
	public List<User> getAll() {
		String sql = "SELECT id_user, login, password, email "
				+ "FROM user";
		return jdbcTemplate.query(sql, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserID(rs.getLong("id_user"));
				user.setName(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				return user;
			}
		});
	}

}
