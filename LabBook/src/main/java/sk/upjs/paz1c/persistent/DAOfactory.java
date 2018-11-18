package sk.upjs.paz1c.persistent;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

public enum DAOfactory {

	INSTANCE;

	private JdbcTemplate jdbcTemplate;
	private UserDAO userDAO;
	private ProjectDAO projectDAO;
	private AdminDAO adminDAO;

	public UserDAO getUserDAO() {
		if (userDAO == null) {
			userDAO = new MysqlUserDAO(getJbdcTemplate());
		}
		return userDAO;
	}
	
	public ProjectDAO getProjectDAO() {
		if (projectDAO == null) {
			projectDAO = new MysqlProjectDAO(getJbdcTemplate());
		}
		return projectDAO;
	}
	
	public AdminDAO getAdminDAO() {
		if (adminDAO == null) {
			adminDAO = new MysqlAdminDAO(getJbdcTemplate());
		}
		return adminDAO;
	}
	
	private JdbcTemplate getJbdcTemplate() {
		if (jdbcTemplate == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser("paz1c");
			dataSource.setPassword("paz1cJeSuper");
			//dataSource.setDatabaseName("lab_book"); // nazov schemy
			dataSource.setUrl("jdbc:mysql://localhost/lab_book?serverTimezone=Europe/Bratislava");
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}
}
