package com.techelevator;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.Site;
import com.techelevator.model.jdbc.JDBCSiteDAO;

public class JDBCSiteDAOIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private JDBCSiteDAO siteDAO;

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
				+ "VALUES (300, 'Tech Elevator Peaks', 'Ohio', '1933-02-26', 12345, 55000, " + "'Very pretty park.')";

		String sqlInsertTestCampground1 = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) "
				+ "VALUES (7000, 300, 'SampleCampground1', '01', '12', 35.00)";
		String sqlInsertTestCampground2 = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) "
				+ "VALUES (8000, 300, 'SampleCampground2', '01', '12', 35.00)";

		String sqlInsertTestSite1 = "INSERT INTO site(site_id, campground_id, site_number) " + "VALUES(1000, 7000, 10)";
		String sqlInsertTestSite2 = "INSERT INTO site(site_id, campground_id, site_number) " + "VALUES(2000, 7000, 15)";		
		String sqlInsertTestSite3 = "INSERT INTO site(site_id, campground_id, site_number) " + "VALUES(3000, 7000, 20)";
		String sqlInsertTestSite4 = "INSERT INTO site(site_id, campground_id, site_number) " + "VALUES(4000, 8000, 25)";
		String sqlInsertTestSite5 = "INSERT INTO site(site_id, campground_id, site_number) " + "VALUES(5000, 8000, 30)";
		String sqlInsertTestSite6 = "INSERT INTO site(site_id, campground_id, site_number) " + "VALUES(6000, 8000, 35)";

		String sqlInsertTestReservation1 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date) "
				+ "VALUES(100, 1000, 'Rebecca', '2016-07-01', '2016-07-08')";
		String sqlInsertTestReservation2 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date) "
				+ "VALUES(101, 1000, 'Dave', '2016-07-09', '2016-07-15')";
		String sqlInsertTestReservation3 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date) "
				+ "VALUES(102, 2000, 'Anthony', '2016-07-09', '2016-07-15')";
		String sqlInsertTestReservation4 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date) "
				+ "VALUES(103, 3000, 'Sam', '2016-07-17', '2016-07-25')";
		String sqlInsertTestReservation5 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date) "
				+ "VALUES(104, 4000, 'Jim', '2016-07-26', '2016-07-30')";
		String sqlInsertTestReservation6 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date) "
				+ "VALUES(105, 4000, 'Aaron', '2016-08-02', '2016-08-07')";
		String sqlInsertTestReservation7 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date) "
				+ "VALUES(106, 5000, 'Ray', '2016-08-10', '2016-08-17')";
		String sqlInsertTestReservation8 = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date) "
				+ "VALUES(107, 6000, 'Matt', '2016-08-20', '2016-08-24')";

		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertTestPark);
		jdbcTemplate.update(sqlInsertTestCampground1);
		jdbcTemplate.update(sqlInsertTestCampground2);
		jdbcTemplate.update(sqlInsertTestSite1);
		jdbcTemplate.update(sqlInsertTestSite2);
		jdbcTemplate.update(sqlInsertTestSite3);
		jdbcTemplate.update(sqlInsertTestSite4);
		jdbcTemplate.update(sqlInsertTestSite5);
		jdbcTemplate.update(sqlInsertTestSite6);
		jdbcTemplate.update(sqlInsertTestReservation1);
		jdbcTemplate.update(sqlInsertTestReservation2);
		jdbcTemplate.update(sqlInsertTestReservation3);
		jdbcTemplate.update(sqlInsertTestReservation4);
		jdbcTemplate.update(sqlInsertTestReservation5);
		jdbcTemplate.update(sqlInsertTestReservation6);
		jdbcTemplate.update(sqlInsertTestReservation7);
		jdbcTemplate.update(sqlInsertTestReservation8);

		siteDAO = new JDBCSiteDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	
	@Test
	public void return_site_list_when_no_date_collision_on_search_page_criteria() throws SQLException {
		int testCampgroundId = 7000;
		String fromDate = "2016-06-01";
		String toDate = "2016-06-03";
		
		java.util.Date fd = null;
		try {
			fd = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
		} catch (ParseException e) {
			System.out.println("From Date could not be parsed");
			e.printStackTrace();
		}
		java.util.Date td = null;
		try {
			td = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		} catch (ParseException e) {
			System.out.println("To Date could not be parsed");
			e.printStackTrace();
		}
		
		java.sql.Date sqlFD = new java.sql.Date(fd.getTime());
		java.sql.Date sqlTD = new java.sql.Date(td.getTime());
		
		List<Site> testSiteList = siteDAO.getSitesBySearchCriteria(testCampgroundId, sqlFD, sqlTD);
		
		assertEquals(3, testSiteList.size());
		assertEquals(15, testSiteList.get(1).getSiteNumber());
		assertEquals(20, testSiteList.get(2).getSiteNumber());
	}
	
	@Test
	public void return_site_list_when_partial_date_collision_for_one_reservation_on_search_page_criteria() throws SQLException {
		int testCampgroundId = 7000;
		String fromDate = "2016-06-30";
		String toDate = "2016-07-05";
		
		java.util.Date fd = null;
		try {
			fd = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
		} catch (ParseException e) {
			System.out.println("From Date could not be parsed");
			e.printStackTrace();
		}
		java.util.Date td = null;
		try {
			td = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		} catch (ParseException e) {
			System.out.println("To Date could not be parsed");
			e.printStackTrace();
		}
		
		java.sql.Date sqlFD = new java.sql.Date(fd.getTime());
		java.sql.Date sqlTD = new java.sql.Date(td.getTime());
		
		List<Site> testSiteList = siteDAO.getSitesBySearchCriteria(testCampgroundId, sqlFD, sqlTD);
		
		assertEquals(2, testSiteList.size());
	}
	
	@Test
	public void return_site_list_when_total_date_collision_on_search_page_criteria() throws SQLException {
		int testCampgroundId = 7000;
		String fromDate = "2016-07-18";
		String toDate = "2016-07-24";
		
		java.util.Date fd = null;
		try {
			fd = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
		} catch (ParseException e) {
			System.out.println("From Date could not be parsed");
			e.printStackTrace();
		}
		java.util.Date td = null;
		try {
			td = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		} catch (ParseException e) {
			System.out.println("To Date could not be parsed");
			e.printStackTrace();
		}
		
		java.sql.Date sqlFD = new java.sql.Date(fd.getTime());
		java.sql.Date sqlTD = new java.sql.Date(td.getTime());
		
		List<Site> testSiteList = siteDAO.getSitesBySearchCriteria(testCampgroundId, sqlFD, sqlTD);
		
		assertEquals(2, testSiteList.size());
	}
	
	@Test
	public void return_site_list_when_partial_date_collision_for_two_reservations_on_search_page_criteria() throws SQLException {
		int testCampgroundId = 8000;
		String fromDate = "2016-08-05";
		String toDate = "2016-08-15";
		
		java.util.Date fd = null;
		try {
			fd = new SimpleDateFormat("yyyy-MM-dd").parse(fromDate);
		} catch (ParseException e) {
			System.out.println("From Date could not be parsed");
			e.printStackTrace();
		}
		java.util.Date td = null;
		try {
			td = new SimpleDateFormat("yyyy-MM-dd").parse(toDate);
		} catch (ParseException e) {
			System.out.println("To Date could not be parsed");
			e.printStackTrace();
		}
		
		java.sql.Date sqlFD = new java.sql.Date(fd.getTime());
		java.sql.Date sqlTD = new java.sql.Date(td.getTime());
		
		List<Site> testSiteList = siteDAO.getSitesBySearchCriteria(testCampgroundId, sqlFD, sqlTD);
		
		assertEquals(1, testSiteList.size());
		assertEquals(35, testSiteList.get(0).getSiteNumber());
	}
	
	@Test 
	public void return_site_by_site_id() throws SQLException {
		Site testSite = siteDAO.getSiteById(1000);
		assertEquals(7000, testSite.getCampgroundId());
	}
}
