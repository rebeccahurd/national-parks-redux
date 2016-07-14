package com.techelevator.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Campground;
import com.techelevator.model.CampgroundDAO;

@Component
public class JDBCCampgroundDAO implements CampgroundDAO {

private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCCampgroundDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Campground> getCampgroundsByParkId(int parkId) {
		ArrayList<Campground> campgroundList = new ArrayList<>();
		String sqlSelectCampgroundsByParkId = "SELECT * "+
												"FROM campground "+
												"WHERE park_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectCampgroundsByParkId, parkId);
		
		while(results.next()) {
			Campground c = new Campground();
			c.setCampgroundId(results.getInt("campground_id"));
			c.setParkId(results.getInt("park_id"));
			c.setName(results.getString("name"));
			c.setOpenFromMM(results.getString("open_from_mm"));
			c.setOpenToMM(results.getString("open_to_mm"));
			c.setDailyFee(results.getBigDecimal("daily_fee"));
			
			campgroundList.add(c);
		}
		return campgroundList;
	}

}
