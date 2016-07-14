package com.techelevator.model.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.Reservation;
import com.techelevator.model.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO{

private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCReservationDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Reservation> getReservationsBySiteId(int siteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation getReservationById(int reservationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveReservation(Reservation r) {
		// TODO Auto-generated method stub
		
	}

}
