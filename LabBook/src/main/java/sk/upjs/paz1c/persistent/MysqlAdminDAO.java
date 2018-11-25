package sk.upjs.paz1c.persistent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import sk.upjs.paz1c.entities.Admin;

public class MysqlAdminDAO implements AdminDAO {
	
	private JdbcTemplate jdbcTemplate;

	public MysqlAdminDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addAdmin(Admin admin) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("admin");
		insert.usingGeneratedKeyColumns("id_admin");
		insert.usingColumns("name", "password");

		Map<String, Object> values = new HashMap<>();
		values.put("name", admin.getName());
		values.put("password", admin.getPassword());

		admin.setAdminID(insert.executeAndReturnKey(values).longValue());

	}

	@Override
	public List<Admin> getAll() {
		String sql = "SELECT id_admin, name, password " + "FROM admin";
		return jdbcTemplate.query(sql, new RowMapper<Admin>() {

			@Override
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
				Admin admin = new Admin();
				admin.setAdminID(rs.getLong("id_admin"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("password"));
				return admin;
			}
		});
	}

	@Override
	public void saveAdmin(Admin admin) {
		if (admin == null)
			return;
		if (admin.getAdminID() == null) { // CREATE
			addAdmin(admin);
			System.out.println("pridavam noveho admina");
		} else { // UPDATE
			String sql = "UPDATE admin SET " + "name = ?, password = ? " + "WHERE id_admin = ?";
			jdbcTemplate.update(sql, admin.getName(), admin.getPassword(), admin.getAdminID());
		}
	}

	@Override
	public void deleteAdmin(Admin admin) {
		String sql = "DELETE FROM admin WHERE id_admin = " + admin.getAdminID();
		jdbcTemplate.update(sql);
	}

}
