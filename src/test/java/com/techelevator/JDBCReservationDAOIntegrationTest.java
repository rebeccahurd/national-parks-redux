package com.techelevator;

import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.jdbc.JDBCReservationDAO;

public class JDBCReservationDAOIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private JDBCReservationDAO reservationDAO;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		String sqlInsertTestReservation1 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date, create_date) "
											+"VALUES(100, 100, 'Rebecca', '2016-07-01', '2016-07-08'";
		String sqlInsertTestReservation2 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date, create_date) "
											+"VALUES(101, 100, 'Dave', '2016-07-09', '2016-07-15'";
		String sqlInsertTestReservation3 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date, create_date) "
											+"VALUES(102, 200, 'Anthony', '2016-07-09', '2016-07-15'";
		
		String sqlInsertTestSite1 = "INSERT INTO site(site_id, campground_id, site_number) "
											+"VALUES(100, 5, 10)";
		String sqlInsertTestSite2 = "INSERT INTO site(site_id, campground_id, site_number) "
											+"VALUES(101, 6, 10)";
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertTestReservation1);
		jdbcTemplate.update(sqlInsertTestReservation2);
		jdbcTemplate.update(sqlInsertTestReservation3);
		jdbcTemplate.update(sqlInsertTestSite1);
		jdbcTemplate.update(sqlInsertTestSite2);
		
		
		reservationDAO = new JDBCReservationDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	
}

