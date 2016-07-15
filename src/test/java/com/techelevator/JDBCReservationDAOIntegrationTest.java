package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.Reservation;
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
		String sqlInsertTestPark = "INSERT INTO park (park_id, name, location, establish_date, area, visitors, description) "
									+ "VALUES (300, 'Tech Elevator Peaks', 'Ohio', '1933-02-26', 12345, 55000, "
									+ "'Very pretty park.')";
		
		String sqlInsertTestCampground1 = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) "
											+ "VALUES (500, 300, 'SampleCampground1', '01', '12', 35.00)";

		String sqlInsertTestSite1 = "INSERT INTO site(site_id, campground_id, site_number) "
									+"VALUES(1000, 500, 10)";
		String sqlInsertTestSite2 = "INSERT INTO site(site_id, campground_id, site_number) "
									+"VALUES(2000, 500, 10)";

		String sqlInsertTestReservation1 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date) "
											+"VALUES(100, 1000, 'Rebecca', '2016-07-01', '2016-07-08')";
		String sqlInsertTestReservation2 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date) "
											+"VALUES(101, 1000, 'Dave', '2016-07-09', '2016-07-15')";
		String sqlInsertTestReservation3 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date) "
											+"VALUES(102, 2000, 'Anthony', '2016-07-09', '2016-07-15')";
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertTestPark);
		jdbcTemplate.update(sqlInsertTestCampground1);
		jdbcTemplate.update(sqlInsertTestSite1);
		jdbcTemplate.update(sqlInsertTestSite2);
		jdbcTemplate.update(sqlInsertTestReservation1);
		jdbcTemplate.update(sqlInsertTestReservation2);
		jdbcTemplate.update(sqlInsertTestReservation3);
		
		reservationDAO = new JDBCReservationDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test 
	public void returns_reservation_by_id() throws SQLException {
		Reservation r1 = reservationDAO.getReservationById(100);
		Reservation r2 = reservationDAO.getReservationById(101);
		
		assertNotNull(r1);
		assertEquals("Dave",r2.getName());
	}
	
	@Test 
	public void returns_reservations_by_site_id() throws SQLException {
		List<Reservation> testReservations1 = reservationDAO.getReservationsBySiteId(2000);
		assertNotNull(testReservations1);
		assertEquals(1, testReservations1.size());
		
		List<Reservation> testReservations2 = reservationDAO.getReservationsBySiteId(1000);
		assertEquals(2, testReservations2.size());
	}
	
	@Test 
	public void save_reservation_and_read_it_back() throws SQLException {
		Reservation r = new Reservation();
		
		r.setSiteId(1000);
		r.setName("David");
		
		Date sqlFromDate = Date.valueOf("2016-07-01");
		r.setFromDate(sqlFromDate);
		
		Date sqlToDate = Date.valueOf("2016-07-05");
		r.setToDate(sqlToDate);
		
		reservationDAO.saveReservation(r);
		
		assertEquals("David", reservationDAO.getReservationById(12).getName());
		// assertEquals(reservationDAO.getNextReservationId(), reservationDAO.getReservationById(11).getReservationId());
	}
	
}






