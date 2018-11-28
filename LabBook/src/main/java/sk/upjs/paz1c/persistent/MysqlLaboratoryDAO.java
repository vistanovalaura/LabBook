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

import sk.upjs.paz1c.entities.Item;
import sk.upjs.paz1c.entities.Laboratory;
import sk.upjs.paz1c.entities.Project;

public class MysqlLaboratoryDAO implements LaboratoryDAO {

	private JdbcTemplate jdbcTemplate;

	public MysqlLaboratoryDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void addLaboratory(Laboratory laboratory) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("laboratory");
		insert.usingGeneratedKeyColumns("id_laboratory");
		insert.usingColumns("name", "location");

		Map<String, Object> values = new HashMap<>();
		values.put("name", laboratory.getName());
		values.put("location", laboratory.getLocation());

		laboratory.setLaboratoryID(insert.executeAndReturnKey(values).longValue());
	}

	@Override
	public List<Laboratory> getAll() {
		String sql = "SELECT id_laboratory, name, location " + "FROM laboratory";
		return jdbcTemplate.query(sql, new RowMapper<Laboratory>() {

			@Override
			public Laboratory mapRow(ResultSet rs, int rowNum) throws SQLException {
				Laboratory laboratory = new Laboratory();
				laboratory.setLaboratoryID(rs.getLong("id_laboratory"));
				laboratory.setName(rs.getString("name"));
				laboratory.setLocation(rs.getString("location"));
				return laboratory;
			}
		});
	}

	@Override
	public void saveLaboratory(Laboratory laboratory) {
		if (laboratory == null)
			return;
		if (laboratory.getLaboratoryID() == null) { // CREATE
			addLaboratory(laboratory);
		} else { // UPDATE
			String sql = "UPDATE laboratory SET " + "name = ?, location = ? " + "WHERE id_laboratory = ?";
			jdbcTemplate.update(sql, laboratory.getName(), laboratory.getLocation(), laboratory.getLaboratoryID());
		}
	}

	@Override
	public void deleteLaboratory(Laboratory laboratory) {
		// updatuje vsetky itemy, ktore patrili danemu labaku, aby nepatrili ziadnemu
		ItemDAO itemDao = DAOfactory.INSTANCE.getItemDAO();
		List<Item> items = itemDao.getAll();
		for (Item item : items) {
			if (item.getLaboratory().getLaboratoryID() == laboratory.getLaboratoryID()) {
				item.setLaboratory(null);
				itemDao.saveItem(item);
			}
		}
		// vymaze labak
		String sql = "DELETE FROM laboratory WHERE id_laboratory = " + laboratory.getLaboratoryID();
		jdbcTemplate.update(sql);
	}

	@Override
	public Laboratory getLaboratoryByID(Long id) {
		List<Laboratory> laboratories = DAOfactory.INSTANCE.getLaboratoryDAO().getAll();
		for(Laboratory laboratory: laboratories) {
			if(laboratory.getLaboratoryID() == id) {
				return laboratory;
			}
		}
		return null;
	}

}
