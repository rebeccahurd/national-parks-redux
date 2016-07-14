package com.techelevator.model.jdbc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Reservation;
import com.techelevator.model.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO{

private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCReservationDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Reservation getReservationById(int reservationId) {
		Reservation r = new Reservation();
		String sqlGetReservationById = "SELECT * " +
									   "FROM reservation " +
									   "WHERE reservation_id = ?";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetReservationById, reservationId);
		if (result.next()) {
			r.setReservationId(result.getInt("reservation_id"));
			r.setSiteId(result.getInt("site_id"));
			r.setName(result.getString("name"));
			
			Date fd = result.getDate("from_date");
			LocalDate fromDate = fd.toLocalDate();
			r.setFromDate(fromDate);
			
			Date td = result.getDate("to_date");
			LocalDate toDate = td.toLocalDate();
			r.setToDate(toDate);
			
			Date cd = result.getDate("create_date");
			LocalDate createDate = cd.toLocalDate();
			r.setCreateDate(createDate);
		}
		return r;
	}
	
	@Override
	public List<Reservation> getReservationsBySiteId(int siteId) {
		ArrayList<Reservation> reservationList = new ArrayList<>();
		String sqlGetReservationsBySiteId = "SELECT * " +
									   "FROM reservation " +
									   "WHERE site_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetReservationsBySiteId, siteId);
		while (results.next()) {
			Reservation r = new Reservation();
			r.setReservationId(results.getInt("reservation_id"));
			r.setSiteId(results.getInt("site_id"));
			r.setName(results.getString("name"));
			
			Date fd = results.getDate("from_date");
			LocalDate fromDate = fd.toLocalDate();
			r.setFromDate(fromDate);
			
			Date td = results.getDate("to_date");
			LocalDate toDate = td.toLocalDate();
			r.setToDate(toDate);
			
			Date cd = results.getDate("create_date");
			LocalDate createDate = cd.toLocalDate();
			r.setCreateDate(createDate);
			
			reservationList.add(r);
		}
		return reservationList;
	}

	@Override
	public void saveReservation(Reservation r) {
		String sqlInsertReservation = "INSERT INTO reservation(reservation_id, site_id, name, from_date, to_date) " +
									  "VALUES(?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlInsertReservation, r.getReservationId(),
													r.getSiteId(),
													r.getName(),
													r.getFromDate(),
													r.getToDate());
		
	}
	
	private Reservation mapRowToReservation(SqlRowSet results) {
		Reservation r;
		
		r = new Reservation();
		r.setReservationId(results.getInt("reservation_id"));
		r.setSiteId(results.getInt("site_id"));
		r.setName(results.getString("name"));
		
		Date fd = results.getDate("from_date");
		LocalDate fromDate = fd.toLocalDate();
		r.setFromDate(fromDate);
		
		Date td = results.getDate("to_date");
		LocalDate toDate = td.toLocalDate();
		r.setToDate(toDate);
		
		Date cd = results.getDate("create_date");
		LocalDate createDate = cd.toLocalDate();
		r.setCreateDate(createDate);
		
		return r;
	}
}
