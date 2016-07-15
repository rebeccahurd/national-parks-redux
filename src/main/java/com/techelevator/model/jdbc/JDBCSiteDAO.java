package com.techelevator.model.jdbc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Site;
import com.techelevator.model.SiteDAO;

@Component
public class JDBCSiteDAO implements SiteDAO{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSiteDAO(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Site> getSitesBySearchCriteria(Date fromDate, Date toDate) {
		ArrayList<Site> siteList = new ArrayList<>();
		String sqlGetSitesBySearchCriteria = "SELECT * "+
											 "FROM site "+
											 "WHERE site_id NOT IN (SELECT reservation.site_id FROM reservation WHERE ((?,?) OVERLAPS (from_date, to_date)))";
											//"WHERE site_id IN (SELECT * FROM reservation WHERE ((DATE ?, DATE ?) OVERLAPS (DATE from_date, DATE to_date)) = FALSE)";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetSitesBySearchCriteria, fromDate, toDate);
		while(results.next()) {
			Site s = new Site();
			s.setSiteId(results.getInt("site_id"));
			s.setCampgroundId(results.getInt("campground_id"));
			s.setSiteNumber(results.getInt("site_number"));
			s.setMaxOccupancy(results.getInt("max_occupancy"));
			s.setAccessible(results.getBoolean("accessible"));
			s.setMaxRvLength(results.getInt("max_rv_length"));
			s.setUtilities(results.getBoolean("utilities"));
			
			siteList.add(s);
		}
		return siteList;
	}
}