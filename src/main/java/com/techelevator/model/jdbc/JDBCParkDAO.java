package com.techelevator.model.jdbc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Park;
import com.techelevator.model.ParkDAO;

@Component
public class JDBCParkDAO implements ParkDAO{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCParkDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks() {
		ArrayList<Park> allParks = new ArrayList<>();
		String sqlSelectAllParks = "SELECT * "
									+"FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParks);
		
		while(results.next()) {
			Park p = new Park();
			
			p.setParkId(results.getInt("park_id"));
			p.setName(results.getString("name"));
			p.setLocation(results.getString("location"));
			p.setArea(results.getInt("area"));
			p.setVisitors(results.getInt("visitors"));
			p.setDescription(results.getString("description"));
			
			Date estDate = results.getDate("establish_date");
			LocalDate establishDate = estDate.toLocalDate();
			p.setEstablishDate(establishDate);
			
			allParks.add(p);
		}
		return allParks;
	}

	@Override
	public Park getParkById(int parkId) {
		Park p = new Park();
		String sqlSelectParkById = "SELECT * "+
									"FROM park "+
									"WHERE park_id = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlSelectParkById, parkId);
		
		if(result.next()) {
			p.setParkId(result.getInt("park_id"));
			p.setName(result.getString("name"));
			p.setLocation(result.getString("location"));
			p.setArea(result.getInt("area"));
			p.setVisitors(result.getInt("visitors"));
			p.setDescription(result.getString("description"));
			
			Date estDate = result.getDate("establish_date");
			LocalDate establishDate = estDate.toLocalDate();
			p.setEstablishDate(establishDate);
			
		}
		return p;
	}

}
